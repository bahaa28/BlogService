package com.example.demo.controllers;

import com.example.demo.model.Blogs;
import com.example.demo.model.UserEntity;
import com.example.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<Blogs> getAl(){
        return blogService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Blogs> getById(@PathVariable long id){
        return blogService.getById(id);
    }

    @PostMapping
    public Blogs add(@RequestBody Blogs blogs){
        return blogService.add(blogs);
    }

    @PutMapping("{id}")
    public ResponseEntity<Blogs> update(
            @PathVariable long id,
            @RequestBody Blogs blogs
    ){
        return blogService.update(blogs, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Blogs> delete(@PathVariable long id){
        return blogService.delete(id);
    }


    @GetMapping("{id}/user")
    public ResponseEntity<UserEntity> getTheAuthorOfTheBlog(@PathVariable long id){
        return blogService.getTheAuthorOfTheBlog(id);
    }


}
