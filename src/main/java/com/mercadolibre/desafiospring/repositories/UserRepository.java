package com.mercadolibre.desafiospring.repositories;

import com.mercadolibre.desafiospring.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    int countByFollowedId(Integer id);

    boolean existsById(Integer id);

    List<User> findByFollowedId(Integer id, Sort sort);

    User findByUserName(String userName);
}
