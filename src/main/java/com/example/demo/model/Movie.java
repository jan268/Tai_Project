package com.example.demo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"viewer"})
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @NotNull
    private String director;

    @Min(10)
    @Max(240)
    private Long duration;
    private String source;

    @ManyToOne
    private Viewer viewer;

    public Movie() {
    }
}
