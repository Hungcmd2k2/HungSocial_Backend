package com.HungSocial.Server.Repository.Posts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HungSocial.Server.Entity.Posts.Post;


@Repository
public interface  PostsRepository extends JpaRepository<Post,Integer>{
    List<Post> findByUserId(Integer userId);
}
