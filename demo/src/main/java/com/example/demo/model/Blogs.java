package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    private Long id;

    @NotNull(message = "title must not be null")
    @NotEmpty(message = "title must not be empty")
    @Column(name = "title")
    private String title;

    @NotNull(message = "text must not be null")
    @NotEmpty(message = "text must not be empty")
    @Column(name = "text")
    private StringBuilder text;

    @NotNull(message = "user id must not be null")
    @Column(name = "user_id")
    private Long user_id;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comments> comments = new ArrayList<Comments>();

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Favorites> favorates = new ArrayList<Favorites>();


}
