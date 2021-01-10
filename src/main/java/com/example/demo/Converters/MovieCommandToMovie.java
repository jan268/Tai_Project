package com.example.demo.Converters;

import com.example.demo.Command.MovieCommand;
import com.example.demo.Command.ViewerCommand;
import com.example.demo.model.Movie;
import com.example.demo.model.Viewer;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MovieCommandToMovie implements Converter<MovieCommand, Movie> {

    @Synchronized
    @Nullable
    @Override
    public Movie convert(MovieCommand source) {

        if (source == null){
            return null;
        }

        final Movie movie =new Movie();
        movie.setId(source.getId());
        movie.setTitle(source.getTitle());
        movie.setDirector(source.getDirector());
        movie.setDuration(source.getDuration());
        movie.setSource(source.getSource());
        movie.setGenre(source.getGenre());

        return movie;
    }
}
