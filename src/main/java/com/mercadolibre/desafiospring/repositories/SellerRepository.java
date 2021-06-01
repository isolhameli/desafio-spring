package com.mercadolibre.desafiospring.repositories;

import com.mercadolibre.desafiospring.models.Seller;
import com.mercadolibre.desafiospring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller,Integer> {

    List<Seller> findByFollowersIdOrderByUserNameDesc(Integer id);
    List<Seller> findByFollowersIdOrderByUserNameAsc(Integer id);
}
