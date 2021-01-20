package com.example.demo.Services;

import com.example.demo.Command.ViewerCommand;
import com.example.demo.model.Viewer;
import javassist.NotFoundException;

import java.security.Principal;
import java.util.Set;

public interface ViewerService {

    Set<Viewer> getViewers();

    Viewer findById(Long id);

    Viewer findById(Long id, Principal principal);

    ViewerCommand findCommandById(Long l);

    ViewerCommand findCommandById(Long l, Principal principal);

    ViewerCommand saveViewerCommand(ViewerCommand command);

    void deleteById(Long idToDelete);

    void deleteById(Long idToDelete, Principal principal);
}
