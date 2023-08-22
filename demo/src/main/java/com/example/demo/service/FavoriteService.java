package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Favorites;
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

    public List<Favorites> getAll(){
        return favorateReposetories.findAll();
    }

    public ResponseEntity<Favorites> getById(long id){
        Favorites favorate = favorateReposetories.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("favorate does not exists with id: " + id));

        return ResponseEntity.ok(favorate);
    }

    public Favorites add(@Valid Favorites favorites){
        return favorateReposetories.save(favorites);
    }

    public ResponseEntity<Favorites> update(@Valid Favorites favorites, long id){
        Favorites updated = favorateReposetories.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("favorate does not exists with id: " + id));

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
}
