package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.Blogs;
import com.example.demo.model.Comments;
import com.example.demo.reposetories.BlogRepository;
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

    @Autowired
    private BlogRepository blogRepository;

    public List<Comments> getAll(){
        return commentReposetories.findAll();
    }

    public ResponseEntity<Comments> getById(long id){
        Comments comment = commentReposetories.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("comment does not exists with id: " + id));


        return ResponseEntity.ok(comment);
    }

    public Comments add(@Valid Comments comments){
        validateBlog(comments.getBlog());

        if(comments.getUser_id() == null){
            throw new ValidationException("user id must not be null");
        }

        return commentReposetories.save(comments);
    }

    public ResponseEntity<Comments> update(@Valid Comments comment, long id){
        Comments updated = commentReposetories.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("comment does not exists with id: " + id));

        validateBlog(comment.getBlog());

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

    public ResponseEntity<Comments> commentOnBlog(long blogId, long commentId){
        Comments comment = commentReposetories.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("comment does not exists with id: " + commentId));
        Blogs blog = blogRepository.findById(blogId).orElseThrow(() ->
                new ResourceNotFoundException("Blog does not exists with id: " + blogId));

        comment.setBlog(blog);

        commentReposetories.save(comment);

        return ResponseEntity.ok(comment);
    }

    private void validateBlog(Blogs blogs){
        if(blogs != null && blogs.getId() != null){
            Blogs blogSearch = blogRepository.findById(blogs.getId()).orElseThrow(() ->
                    new ResourceNotFoundException("blog does not exists with id: " + blogs.getId()));

            if(!blogSearch.equals(blogs)){
                throw new ResourceNotFoundException("blog does not the same of exist one with id: " + blogs.getId());
            }

        }else if(blogs != null){
            throw new ResourceNotFoundException("blog id should not be null");
        }
    }
}
