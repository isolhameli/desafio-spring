package com.mercadolibre.desafiospring.services;

import com.mercadolibre.desafiospring.exceptions.users.*;
import com.mercadolibre.desafiospring.models.Seller;
import com.mercadolibre.desafiospring.models.User;
import com.mercadolibre.desafiospring.repositories.SellerRepository;
import com.mercadolibre.desafiospring.repositories.UserRepository;
import com.mercadolibre.desafiospring.requests.UserRequest;
import com.mercadolibre.desafiospring.responses.users.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final UtilService utilService;

    public UserServiceImpl(UserRepository userRepository, SellerRepository sellerRepository, UtilService utilService) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
        this.utilService = utilService;
    }

    @Override
    public UserCreateResponse create(UserRequest userRequest) {
        User user;
        if (userRequest.isSeller()){
            user = new Seller(userRequest.getUserName(), LocalDate.now());
        }
        else{
            user = new User(userRequest.getUserName(), LocalDate.now());
        }
        if (!(userRepository.findByUserName(userRequest.getUserName())==null)){
            throw new UserAlreadyExistsException("Username already taken");
        }
        user = userRepository.save(user);
        UserCreateResponse userCreateResponse = new UserCreateResponse(user.getId(), user.getUserName(),userRequest.isSeller());
        return userCreateResponse;
    }

    @Override
    public void follow(Integer userId, Integer userIdToFollow) {
        checkAndHandleSameUser(userId, userIdToFollow);
        List<User> users = findAllById(Arrays.asList(userId,userIdToFollow));
        User user = users.stream().filter(el -> el.getId() == userId).findFirst().get();
        User userToFollow = users.stream().filter(el -> el.getId() == userIdToFollow).findFirst().get();
        if (user.getFollowed().contains(userToFollow)){
            throw new UserAlreadyFollowsSellerException("User already followers Seller");
        }
        user.getFollowed().add((Seller) userToFollow);
        update(user);

    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public Seller getSeller(Integer userId) {
        User user = getUser(userId);
        if (user.getClass() != Seller.class){
            throw new UserTypeNotValidException("User "+userId+ " is not a Seller");
        }
        return (Seller) user;
    }

    @Override
    public SellerFollowersCount getFollowerCountResponse(Integer userId) {
        User user = getSeller(userId);
        Integer followerCount = getFollowerCount(userId);
        return new SellerFollowersCount(userId,user.getUserName(),followerCount);
    }

    private Integer getFollowerCount(Integer userId){
        return userRepository.countByFollowedId(userId);
    }

    @Override
    public User getUser(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found. ID: "+userId));
    }

    private List<User> findAllById(List<Integer> idList){
        List<User> userList = userRepository.findAllById(idList);
        if (userList.size() == idList.size()){
            if (userList.stream().filter(elem -> elem.getId() == idList.get(1)).findFirst().get().getClass() != Seller.class){
                throw new UserTypeNotValidException("User "+idList.get(1)+ " is not a Seller");
            };
            return userList;
        }
        String error = "User IDs not found: ";
        List<Integer> idsFound = userList.stream().map(User::getId).collect(Collectors.toList());
        List<Integer> idsNotFound = new ArrayList<>();
        for (Integer id: idList){
            if (!idsFound.contains(id)){
                error += id+", ";
            }
        }
        error = error.substring(0,error.length()-2);
        throw new UserNotFoundException(error);

    }

    public boolean checkUserExistence(Integer userId){
        return userRepository.existsById(userId);
    }

    @Override
    public void unfollow(Integer userId, Integer userIdToUnfollow) {
        checkAndHandleSameUser(userId, userIdToUnfollow);
        List<User> users = findAllById(Arrays.asList(userId, userIdToUnfollow));
        User user = users.stream().filter(el -> el.getId() == userId).findFirst().get();
        Seller userToUnfollow = (Seller) users.stream().filter(el -> el.getId() == userIdToUnfollow).findFirst().get();
        if (!user.removeFollowed(userToUnfollow)){
            throw new UserDoesNotFollowSellerException("User does not follow seller");
        };
        userRepository.save(user);
    }

    @Override
    public FollowedList getFollowers(Integer userId, String order) {
        Seller seller = getSeller(userId);
        List<User> followers = userRepository.findByFollowedId(userId, utilService.getSort(order, UserResponse.class, "userName"));
        List<UserResponse> followersResponse = followers.stream().map(el -> UserResponse.fromModel(el))
                .collect(Collectors.toList());
        return new FollowedList(userId,seller.getUserName(),followersResponse);
    }

    @Override
    public FollowList getFollowed(Integer userId, String order) {
        User user = getUser(userId);
        List<Seller> followed = sellerRepository.findByFollowersId(userId, utilService.getSort(order, UserResponse.class, "userName"));
        List<UserResponse> followedResponse = followed.stream().
                map(el -> new UserResponse(el.getId(),el.getUserName()))
                .collect(Collectors.toList());
        FollowList followList = new FollowedList(userId,user.getUserName(),followedResponse);
        return followList;
    }

    private void checkAndHandleSameUser(Integer firstUser, Integer secondUser){
        if (firstUser == secondUser){
            if (!userRepository.existsById(firstUser)){
                throw new UserNotFoundException("User not found. ID: "+firstUser);
            }
            throw new UserCycliclReferenceException("User cannot unfollow themself");
        }


    }
}
