package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.validator.constraints.UniqueElements;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        this.movies.add(movie);
        return this;
    }
}
