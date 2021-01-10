package com.example.demo.Converters;

import com.example.demo.Command.ViewerCommand;
import com.example.demo.model.Movie;
import com.example.demo.model.Viewer;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ViewerCommandToViewer implements Converter<ViewerCommand, Viewer> {

    private final MovieCommandToMovie movieConverter;

    public ViewerCommandToViewer(MovieCommandToMovie movieConverter) {
        this.movieConverter = movieConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Viewer convert(ViewerCommand source) {

        if (source == null){
            return null;
        }

        final Viewer viewer =new Viewer();
        viewer.setId(source.getId());
        viewer.setUsername(source.getUsername());
        viewer.setEmail(source.getEmail());
        if (source.getMovies() != null && source.getMovies().size() > 0){
            source.getMovies()
                    .forEach(movie -> viewer.getMovies().add(movieConverter.convert(movie)));
        }

        return viewer;
    }
}
