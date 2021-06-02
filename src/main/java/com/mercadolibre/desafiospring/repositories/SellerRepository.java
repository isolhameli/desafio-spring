package com.mercadolibre.desafiospring.repositories;

import com.mercadolibre.desafiospring.models.Seller;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller,Integer> {

    List<Seller> findByFollowersId(Integer id, Sort sort);
}
