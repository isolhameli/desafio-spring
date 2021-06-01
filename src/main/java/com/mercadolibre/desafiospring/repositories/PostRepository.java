package com.mercadolibre.desafiospring.repositories;

import com.mercadolibre.desafiospring.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    @Query(value = "SELECT p.* FROM POST as p INNER JOIN SELLER as s ON s.ID = p.SELLER_ID INNER JOIN FOLLOWERS as f ON f.SELLER_ID = p.SELLER_ID " +
            "WHERE f.USER_ID = :userId AND p.DATE >= DATEADD(day,-14, CURRENT_TIMESTAMP()) ORDER BY p.DATE DESC;",nativeQuery = true)
    List<Post> findFollowedPostsLast14Days(Integer userId);
}
