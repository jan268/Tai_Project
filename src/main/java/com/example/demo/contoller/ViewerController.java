package com.example.demo.contoller;

import com.example.demo.Services.ViewerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewerController {

    private final ViewerService viewerService;

    public ViewerController(ViewerService viewerService) {
        this.viewerService = viewerService;
    }

    @GetMapping("/")
    public String viewerList(Model model){

        model.addAttribute("viewers", viewerService.getViewers());

        return "viewer/viewerList";
    }

    @GetMapping("/viewer/{id}/show")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("viewer", viewerService.findById(Long.valueOf(id)));

        return "viewer/movie/movieList";
    }
}
