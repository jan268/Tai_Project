package com.example.demo.contoller;

import com.example.demo.Command.MovieCommand;
import com.example.demo.Command.ViewerCommand;
import com.example.demo.Services.MovieService;
import com.example.demo.Services.ViewerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class MovieController {

    private final ViewerService viewerService;
    private final MovieService movieService;

    public MovieController(ViewerService viewerService, MovieService movieService) {
        this.viewerService = viewerService;
        this.movieService = movieService;
    }

    @GetMapping("/viewer/{viewerId}/{movieId}/show")
    public String showMovie(@PathVariable Long viewerId, @PathVariable Long movieId, Model model){

        model.addAttribute("movie", movieService.findByViewerIdAndMovieId(viewerId, movieId));

        return "viewer/movie/show";
    }

    @GetMapping("/viewer/{viewerId}/{movieId}/update")
    public String updateMovie(@PathVariable Long viewerId, @PathVariable Long movieId, Model model){

        model.addAttribute("movie", movieService.findByViewerIdAndMovieId(viewerId, movieId));

        return "/viewer/movie/movieform";
    }

    @GetMapping("/viewer/{viewerId}/movie/new")
    public String newMovie(@PathVariable Long viewerId, Model model){

        //need to return back parent id for hidden form property
        MovieCommand movieCommand = new MovieCommand();
        movieCommand.setViewerId(viewerId);

        model.addAttribute("movie", movieCommand);

        System.out.println(movieCommand.getId());
        System.out.println(movieCommand.getViewerId());

        return "/viewer/movie/movieform";
    }

    @PostMapping("/viewer/{viewerId}/movieUpdate")
    public String saveOrUpdate(@Valid @ModelAttribute("movie") MovieCommand command, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "/viewer/movie/movieform";
        }
        System.out.println(command.getId());
        System.out.println(command.getViewerId());

        MovieCommand savedCommand = movieService.saveMovieCommand(command);

        log.debug("Saved Viewer id: " + command.getViewerId());
        log.debug("Saved Movie id: " + command.getId());

        return "redirect:/viewer/" + savedCommand.getViewerId() + "/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/viewer/{viewerId}/{movieId}/delete")
    public String deleteMovie(@PathVariable Long viewerId, @PathVariable Long movieId){

        log.debug("Deleted movie: " + movieId);
        movieService.deleteById(viewerId, movieId);

        return "redirect:/viewer/" + viewerId + "/show";
    }

}
