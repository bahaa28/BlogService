package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Blogs")
public class Blogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private StringBuilder text;

    @Column(name = "user_id")
    private long user_id;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comments> comments = new ArrayList<Comments>();

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Favorates> favorates = new ArrayList<Favorates>();


}
