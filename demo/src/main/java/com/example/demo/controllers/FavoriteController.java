package com.example.demo.controllers;

import com.example.demo.model.Favorites;
import com.example.demo.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping
    public List<Favorites> getAll(){
        return favoriteService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Favorites> getById(@PathVariable long id){
        return favoriteService.getById(id);
    }

    @PostMapping
    public Favorites add(@RequestBody Favorites favorites){
        return favoriteService.add(favorites);
    }

    @PutMapping("{id}")
    public ResponseEntity<Favorites> update(
            @PathVariable long id,
            @RequestBody Favorites favorites
    ){
        return favoriteService.update(favorites, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Favorites> delete(@PathVariable long id){
        return favoriteService.delete(id);
    }
}
