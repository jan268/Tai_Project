package com.example.demo.contoller;

import com.example.demo.Services.MovieService;
import com.example.demo.Services.ViewerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
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

}
