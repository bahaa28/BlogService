package com.example.demo.reposetories;

import com.example.demo.model.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blogs, Long> {
}
