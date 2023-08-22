package com.example.demo.reposetories;

import com.example.demo.model.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavorateReposetories extends JpaRepository<Favorites, Long> {
}
