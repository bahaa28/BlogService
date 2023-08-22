package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Comments;
import com.example.demo.reposetories.CommentReposetories;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentReposetories commentReposetories;

    public List<Comments> getAll(){
        return commentReposetories.findAll();
    }

    public ResponseEntity<Comments> getById(long id){
        Comments comment = commentReposetories.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("comment does not exists with id: " + id));


        return ResponseEntity.ok(comment);
    }

    public Comments add(@Valid Comments comments){
        return commentReposetories.save(comments);
    }

    public ResponseEntity<Comments> update(@Valid Comments comment, long id){
        Comments updated = commentReposetories.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("comment does not exists with id: " + id));

        updated.setText(comment.getText());
        updated.setUser_id(comment.getUser_id());
        updated.setBlog(comment.getBlog());

        commentReposetories.save(updated);

        return ResponseEntity.ok(updated);

    }

    public ResponseEntity<Comments> delete(long id){
        Comments deleted = commentReposetories.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("comment does not exists with id: " + id));

        commentReposetories.delete(deleted);

        return new ResponseEntity<Comments>(HttpStatus.NO_CONTENT);
    }
}
