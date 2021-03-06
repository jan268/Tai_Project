package com.example.demo.Services;

import com.example.demo.Command.MovieCommand;

public interface MovieService {

    MovieCommand findByViewerIdAndMovieId(Long viewerId, Long movieId);

    MovieCommand saveMovieCommand(MovieCommand command);

    void deleteById(Long recipeId, Long idToDelete);
}
