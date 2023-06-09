package com.ewok.springbootproject.domain.posts;

import com.ewok.springbootproject.web.dto.PostsListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p where p.login = ?1 ORDER BY p.good DESC")
    List<Posts> findByLoginGoodDesc(String login);

    @Query("SELECT p FROM Posts p where p.login = ?1 ORDER BY p.good DESC")
    Page<Posts> findByLoginGoodDesc2(String login, Pageable pageable);

}