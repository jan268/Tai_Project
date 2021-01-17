package com.example.demo.Services;

import com.example.demo.Command.MovieCommand;
import com.example.demo.Converters.MovieCommandToMovie;
import com.example.demo.Converters.MovieToMovieCommand;
import com.example.demo.model.Movie;
import com.example.demo.model.Viewer;
import com.example.demo.repositories.MovieRepository;
import com.example.demo.repositories.ViewerRepository;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    private final ViewerRepository viewerRepository;
    private final MovieToMovieCommand movieToMovieCommand;
    private final MovieCommandToMovie movieCommandToMovie;

    public MovieServiceImpl(ViewerRepository viewerRepository, MovieToMovieCommand movieToMovieCommand, MovieCommandToMovie movieCommandToMovie) {
        this.viewerRepository = viewerRepository;
        this.movieToMovieCommand = movieToMovieCommand;
        this.movieCommandToMovie = movieCommandToMovie;
    }

    @Override
    public MovieCommand findByViewerIdAndMovieId(Long viewerId, Long movieId) {

        Optional<Viewer>viewerOptional = viewerRepository.findById(viewerId);

        if (!viewerOptional.isPresent()){
            //todo impl error handling
            log.error("Viewer id not found. Id: " + viewerId);
        }

        Viewer viewer = viewerOptional.get();

        Optional<MovieCommand> movieCommandOptional =viewer.getMovies().stream()
                .filter(movie -> movie.getId().equals(movieId))
                .map(movie -> movieToMovieCommand.convert(movie)).findFirst();

        if(!movieCommandOptional.isPresent()){
            //todo impl error handling
            log.error("Movie id not found: " + movieId);
        }

        return movieCommandOptional.get();
    }

    @Transactional
    @Override
    public MovieCommand saveMovieCommand(MovieCommand command) {

        Optional<Viewer> viewerOptional = viewerRepository.findById(command.getViewerId());

        if(!viewerOptional.isPresent()){

            //todo toss error if not found!
            log.error("Viewer not found for id: " + command.getViewerId());
            return new MovieCommand();
        }

        else {
            Viewer viewer = viewerOptional.get();

            Optional<Movie> movieOptional = viewer
                    .getMovies()
                    .stream()
                    .filter(movie -> movie.getId().equals(command.getId()))
                    .findFirst();

            if(movieOptional.isPresent()){
                Movie movieFound = movieOptional.get();
                movieFound.setDirector(command.getDirector());
                movieFound.setDuration(command.getDuration());
                movieFound.setGenre(command.getGenre());
                movieFound.setSource(command.getSource());
                movieFound.setTitle(command.getTitle());


            } else {
                //add new Movie
                Movie movie = movieCommandToMovie.convert(command);
                movie.setViewer(viewer);
                //ingredient.setId(Long.valueOf(recipe.getIngredients().size()+1)); //Added that so we get normal ingredient ID todo: add error handling
                viewer.addMovie(movie);
            }

            Viewer savedViewer = viewerRepository.save(viewer);

            Optional<Movie> savedMovieOptional = savedViewer.getMovies().stream()
                    .filter(viewerMovie -> viewerMovie.getId().equals(command.getId()))
                    .findFirst();

            //check by description
            if(!savedMovieOptional.isPresent()){
                //not totally safe... But best guess
                savedMovieOptional = savedViewer.getMovies().stream()
                        .filter(ViewerMovie -> ViewerMovie.getTitle().equals(command.getTitle()))
                        .filter(ViewerMovie -> ViewerMovie.getDirector().equals(command.getDirector()))
                        .filter(ViewerMovie -> ViewerMovie.getViewer().getId().equals(command.getViewerId()))
                        .findFirst();
            }

            //to do check for fail
            return movieToMovieCommand.convert(savedMovieOptional.get());
        }
    }

    @Override
    public void deleteById(Long viewerId, Long idToDelete) {
        log.debug("Deleting movie: " + viewerId + ":" + idToDelete);

        Optional<Viewer> viewerOptional = viewerRepository.findById(viewerId);

        if(viewerOptional.isPresent()){
            Viewer viewer = viewerOptional.get();
            log.debug("found viewer");

            Optional<Movie> movieOptional = viewer
                    .getMovies()
                    .stream()
                    .filter(movie -> movie.getId().equals(idToDelete))
                    .findFirst();

            if(movieOptional.isPresent()){
                log.debug("found movie");
                //Ingredient ingredientToDelete = movieOptional.get();
                //ingredientToDelete.setRecipe(null);
                //This sets connection between movie and viewer to none.
                movieOptional.get().setViewer(null);
                viewer.getMovies().remove(movieOptional.get());
                viewerRepository.save(viewer);
            }
        } else {
            log.debug("Viewer Id Not found. Id:" + viewerId);
        }
    }
}
