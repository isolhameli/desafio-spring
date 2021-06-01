package com.mercadolibre.desafiospring.repositories;

import com.mercadolibre.desafiospring.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
}
