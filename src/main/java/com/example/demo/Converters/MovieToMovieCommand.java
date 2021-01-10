package com.example.demo.Converters;

import com.example.demo.Command.MovieCommand;
import com.example.demo.model.Movie;
import org.checkerframework.checker.units.qual.C;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class MovieToMovieCommand implements Converter<Movie, MovieCommand> {

    @Override
    public MovieCommand convert(Movie source) {

        if(source == null){
            return null;
        }

        final MovieCommand movie =new MovieCommand();
        if(source.getViewer() != null) {
            movie.setViewerId(source.getViewer().getId());
        }
        movie.setId(source.getId());
        movie.setTitle(source.getTitle());
        movie.setDirector(source.getDirector());
        movie.setDuration(source.getDuration());
        movie.setSource(source.getSource());
        movie.setGenre(source.getGenre());

        return movie;
    }
}
