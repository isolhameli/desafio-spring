package com.mercadolibre.desafiospring.services;

import com.mercadolibre.desafiospring.exceptions.user.UserAlreadyFollowsSellerException;
import com.mercadolibre.desafiospring.exceptions.user.UserNotFoundException;
import com.mercadolibre.desafiospring.exceptions.user.UserTypeNotValidException;
import com.mercadolibre.desafiospring.models.Seller;
import com.mercadolibre.desafiospring.models.User;
import com.mercadolibre.desafiospring.repositories.UserRepository;
import com.mercadolibre.desafiospring.requests.CreateUserRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int create(CreateUserRequest createUserRequest) {
        User user;
        if (createUserRequest.isSeller()){
            user = new Seller(createUserRequest.getUserName(), LocalDate.now());
        }
        else{
            user = new User(createUserRequest.getUserName(), LocalDate.now());
        }
        return userRepository.save(user).getId();
    }

    @Override
    public void follow(Integer userId, Integer userIdToFollow) {
        List<User> users = findAllId(Arrays.asList(userId,userIdToFollow));
        User user = users.stream().filter(el -> el.getId() == userId).findFirst().get();
        User userToFollow = users.stream().filter(el -> el.getId() == userIdToFollow).findFirst().get();
        if (user.getFollowingList().contains(userToFollow)){
            throw new UserAlreadyFollowsSellerException("Usuário já segue o Vendedor");
        }
        user.getFollowingList().add((Seller) userToFollow);
        user = update(user);
        int i = 0;

    }

    @Override
    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User getFollowers(Integer userId) {
        User user = findById(userId);
        if (user.getClass() != Seller.class){
            throw new UserTypeNotValidException("Usuário "+userId+ " não é um Vendedor");
        }
        return user;
    }

    private User findById(Integer userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado. ID: "+userId));
    }

    private List<User> findAllId(List<Integer> idList){
        List<User> userList = userRepository.findAllById(idList);
        if (userList.size() == idList.size()){
            if (userList.stream().filter(elem -> elem.getId() == idList.get(1)).findFirst().get().getClass() != Seller.class){
                throw new UserTypeNotValidException("Usuário "+idList.get(1)+ " não é um Vendedor");
            };
            return userList;
        }
        String error = "ID dos Usuários não encontrados: ";
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
}
