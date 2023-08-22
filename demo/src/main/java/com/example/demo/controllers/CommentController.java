package com.example.demo.controllers;

import com.example.demo.model.Comments;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comments> getAll(){
        return commentService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Comments> getById(@PathVariable long id){
        return commentService.getById(id);
    }

    @PostMapping
    public Comments add(@RequestBody Comments comments){
        return commentService.add(comments);
    }

    @PutMapping("{id}")
    public ResponseEntity<Comments> update(
            @PathVariable long id,
            @RequestBody Comments comments
    ){
        return commentService.update(comments, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Comments> delete(@PathVariable long id){
        return commentService.delete(id);
    }
}
