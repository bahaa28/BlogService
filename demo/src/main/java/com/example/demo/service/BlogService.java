package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Blogs;
import com.example.demo.reposetories.BlogRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.UserEntity;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Blogs> getAll(){
        return blogRepository.findAll();
    }

    public ResponseEntity<Blogs> getById(long id){
        Blogs blogs = blogRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("blog does not exists with id:" + id));

        return ResponseEntity.ok(blogs);
    }

    public Blogs add(@Valid Blogs blog){
        return blogRepository.save(blog);
    }

    public ResponseEntity<Blogs> update(@Valid Blogs blog, long id){
        Blogs updated = blogRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("blog does not exists with id: " + id));

        updated.setTitle(blog.getTitle());
        updated.setText(blog.getText());
        updated.setUser_id(blog.getUser_id());
        updated.setComments(blog.getComments());
        updated.setFavorates(blog.getFavorates());

        blogRepository.save(updated);

        return ResponseEntity.ok(updated);
    }

    public ResponseEntity<Blogs> delete(long id){
        Blogs deleted = blogRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("blog does not exists with id: " + id));

        blogRepository.delete(deleted);

        return new ResponseEntity<Blogs>(HttpStatus.NO_CONTENT);

    }


     public ResponseEntity<UserEntity> getTheAuthorOfTheBlog(long blog_id) throws ResourceNotFoundException{
        Blogs blog = blogRepository.findById(blog_id).orElseThrow(() ->
                new ResourceNotFoundException("blog does not exists with id: " + blog_id));

        long user_id = blog.getUser_id();

        String url = "http://localhost:8080/api/v1/users/id/" + user_id;
        try{
            ResponseEntity<UserEntity> response = restTemplate.getForEntity(url, UserEntity.class);
            return response;
        }catch (Exception ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }


}
