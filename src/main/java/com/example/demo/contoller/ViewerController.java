package com.example.demo.contoller;

import com.example.demo.Command.ViewerCommand;
import com.example.demo.Services.ViewerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@Slf4j
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
    public String showById(@PathVariable String id, Model model, Principal principal){

        model.addAttribute("viewer", viewerService.findById(Long.valueOf(id), principal));

        return "viewer/movie/movieList";
    }

    @GetMapping("/viewer/{id}/update")
    public String initUpdateViewer(@PathVariable Long id, Model model, Principal principal){

        model.addAttribute("viewer", viewerService.findCommandById(id, principal));

        return "viewer/viewerform";
    }

//    @GetMapping("/viewer/new")
//    public String initNewViewer(Model model){
//
//        model.addAttribute("viewer", new ViewerCommand());
//
//        return "viewer/viewerform";
//    }

    @GetMapping("/viewer/{viewerId}//delete")
    public String deleteMovie(@PathVariable Long viewerId, Principal principal){

        log.debug("Deleted viewer: " + viewerId);
        viewerService.deleteById(viewerId, principal);

        return "redirect:/";
    }

    @PostMapping("/viewer/new")
    public String processUpdateViewer(@Valid @ModelAttribute("viewer")ViewerCommand command, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "viewer/viewerform";
        }else {
            viewerService.saveViewerCommand(command);

            return "redirect:/";
        }
    }

    @GetMapping("/register")
    public String initNewViewer(Model model, Principal principal){

        if (principal == null){

        model.addAttribute("viewer", new ViewerCommand());

        return "register";

        }

        else return "redirect:/";
    }

    @PostMapping("/register")
    public String processSaveViewer(@Valid @ModelAttribute("viewer")ViewerCommand command, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "register";
        }else {
            viewerService.saveViewerCommand(command);

            return "redirect:/login";
        }
    }
}
