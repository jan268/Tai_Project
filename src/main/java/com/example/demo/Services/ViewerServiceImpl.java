package com.example.demo.Services;

import com.example.demo.Command.ViewerCommand;
import com.example.demo.model.Viewer;
import com.example.demo.repositories.ViewerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class ViewerServiceImpl implements ViewerService {

    private final ViewerRepository viewerRepository;

    public ViewerServiceImpl(ViewerRepository viewerRepository) {
        this.viewerRepository = viewerRepository;
    }

    @Override
    public Set<Viewer> getViewers() {
        log.debug("I entered Viewer Service");
        Set<Viewer> viewerSet = new HashSet<>();
        viewerRepository.findAll().iterator().forEachRemaining(viewerSet::add);

        return viewerSet;
    }

    @Override
    public Viewer findById(Long id) {
        return viewerRepository.findById(id).get();
    }

    @Override
    public ViewerCommand findCommandById(Long l) {
        return null;
    }

    @Override
    public ViewerCommand saveRecipeCommand(ViewerCommand command) {
        return null;
    }

    @Override
    public void deleteById(Long idToDelete) {
        viewerRepository.deleteById(idToDelete);
    }
}
