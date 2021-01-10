package com.example.demo.Services;

import com.example.demo.Command.MovieCommand;
import com.example.demo.repositories.MovieRepository;
import com.example.demo.repositories.ViewerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    private final ViewerRepository viewerRepository;
    private final MovieRepository movieRepository;

    public MovieServiceImpl(ViewerRepository viewerRepository, MovieRepository movieRepository) {
        this.viewerRepository = viewerRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieCommand findByViewerIdAndMovieId(Long viewerId, Long movieId) {
        return null;
    }

    @Override
    public MovieCommand saveIngredientCommand(MovieCommand command) {
        return null;
    }

    @Override
    public void deleteById(Long recipeId, Long idToDelete) {

    }
}
