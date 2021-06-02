package com.mercadolibre.desafiospring.repositories;

import com.mercadolibre.desafiospring.models.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findBySellerFollowersIdAndDateBetween(Integer sellerId, LocalDate twoWeeksAgo, LocalDate today, Sort sort);
}
