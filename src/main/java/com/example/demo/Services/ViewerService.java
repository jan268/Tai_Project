package com.example.demo.Services;

import com.example.demo.Command.ViewerCommand;
import com.example.demo.model.Viewer;
import javassist.NotFoundException;

import java.util.Set;

public interface ViewerService {

    Set<Viewer> getViewers();

    Viewer findById(Long id);

    ViewerCommand findCommandById(Long l);

    ViewerCommand saveViewerCommand(ViewerCommand command);

    void deleteById(Long idToDelete);
}
