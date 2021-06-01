package com.mercadolibre.desafiospring.repositories;

import com.mercadolibre.desafiospring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select count(o) from Seller s join s.followers o where s.id = :id")
    int countFollowers(Integer id);

    boolean existsById(Integer id);

    List<User> findByFollowingIdOrderByUserNameDesc(Integer id);

    List<User> findByFollowingIdOrderByUserNameAsc(Integer id);
}
