package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.Blogs;
import com.example.demo.model.Favorites;
import com.example.demo.reposetories.BlogRepository;
import com.example.demo.reposetories.FavorateReposetories;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private FavorateReposetories favorateReposetories;

    @Autowired
    private BlogRepository blogRepository;

    public List<Favorites> getAll(){
        return favorateReposetories.findAll();
    }

    public ResponseEntity<Favorites> getById(long id){
        Favorites favorite = favorateReposetories.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("favorite does not exists with id: " + id));

        return ResponseEntity.ok(favorite);
    }

    public Favorites add(@Valid Favorites favorites){
        validateBlog(favorites.getBlog());

        if(favorites.getUser_id() == null){
            throw new ValidationException("user id must not be null");
        }

        return favorateReposetories.save(favorites);
    }

    public ResponseEntity<Favorites> update(@Valid Favorites favorites, long id){
        Favorites updated = favorateReposetories.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("favorate does not exists with id: " + id));

        validateBlog(favorites.getBlog());

        updated.setUser_id(favorites.getUser_id());
        updated.setBlog(favorites.getBlog());

        favorateReposetories.save(updated);

        return ResponseEntity.ok(updated);
    }

    public ResponseEntity<Favorites> delete(long id){
        Favorites deleted = favorateReposetories.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("favorate does not exists with id: " + id));

        favorateReposetories.delete(deleted);

        return new ResponseEntity<Favorites>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Favorites> favoriteOnBlog(long favoriteId, long blogId){
        Favorites favorite = favorateReposetories.findById(favoriteId).orElseThrow(() ->
                new ResourceNotFoundException("favorite does not exists with id: " + favoriteId));
        Blogs blog = blogRepository.findById(blogId).orElseThrow(() ->
                new ResourceNotFoundException("Blog does not exists with id: " + blogId));

        favorite.setBlog(blog);

        favorateReposetories.save(favorite);

        return ResponseEntity.ok(favorite);
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
