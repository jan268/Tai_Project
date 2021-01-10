package com.example.demo.Converters;

import com.example.demo.Command.ViewerCommand;
import com.example.demo.model.Movie;
import com.example.demo.model.Viewer;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ViewerToViewerCommand implements Converter<Viewer, ViewerCommand> {

    private final MovieToMovieCommand movieConverter;

    public ViewerToViewerCommand(MovieToMovieCommand movieConverter) {
        this.movieConverter = movieConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public ViewerCommand convert(Viewer source) {

        if (source == null){
            return null;
        }

        final ViewerCommand viewer =new ViewerCommand();
        viewer.setId(source.getId());
        viewer.setUsername(source.getUsername());
        viewer.setEmail(source.getEmail());
        if (source.getMovies() != null && source.getMovies().size() > 0){
            source.getMovies()
                    .forEach((Movie movie) -> viewer.getMovies().add(movieConverter.convert(movie)));
        }

        return viewer;
    }
}
