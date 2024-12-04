package com.HungSocial.Server.Service.Posts;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HungSocial.Server.Entity.Posts.Post;
import com.HungSocial.Server.Repository.Posts.PostsRepository;

@Service
public class PostService {
    @Autowired
    private PostsRepository postsRepo;

    public Post createPost(Post postRequest) {
        Post newPost = new Post();
        newPost.setUserId(postRequest.getUserId());
        newPost.setContent(postRequest.getContent());
        newPost.setPrivacy(postRequest.getPrivacy());
        newPost.setTags(postRequest.getTags());
        newPost.setCreatedAt(LocalDateTime.now().withSecond(0).withNano(0));
        newPost.setUpdatedAt(LocalDateTime.now().withSecond(0).withNano(0));
        return postsRepo.save(newPost);
    }

    public List<Post> getPostbyUserid(Integer userId) {
        return postsRepo.findByUserId(userId);
    }
}
