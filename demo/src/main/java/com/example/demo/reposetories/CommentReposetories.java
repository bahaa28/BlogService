package com.example.demo.reposetories;

import com.example.demo.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReposetories extends JpaRepository<Comments, Long> {
}
