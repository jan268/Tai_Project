package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Viewer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @NotNull
    private String username;

    @NotNull
    @Email
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "viewer")
    private Set<Movie> movies = new HashSet<>();

    public Viewer addMovie(Movie movie){
        movie.setViewer(this);
        this.addMovie(movie);
        return this;
    }
}
