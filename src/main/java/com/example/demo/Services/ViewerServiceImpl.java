package com.example.demo.Services;

import com.example.demo.Command.ViewerCommand;
import com.example.demo.Converters.ViewerCommandToViewer;
import com.example.demo.Converters.ViewerToViewerCommand;
import com.example.demo.model.Viewer;
import com.example.demo.repositories.ViewerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class ViewerServiceImpl implements ViewerService {

    private final ViewerRepository viewerRepository;
    private final ViewerToViewerCommand viewerToViewerCommand;
    private final ViewerCommandToViewer viewerCommandToViewer;

    public ViewerServiceImpl(ViewerRepository viewerRepository, ViewerToViewerCommand viewerToViewerCommand, ViewerCommandToViewer viewerCommandToViewer) {
        this.viewerRepository = viewerRepository;
        this.viewerToViewerCommand = viewerToViewerCommand;
        this.viewerCommandToViewer = viewerCommandToViewer;
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

        Optional<Viewer>viewerOptional =viewerRepository.findById(id);

        if(!viewerOptional.isPresent()){
            throw new RuntimeException("Recipe Not Found!");
            //throw new NotFoundException("Viewer Not Found! For ID value: " + id);
        }

        return viewerOptional.get();
    }

    @Transactional
    @Override
    public ViewerCommand findCommandById(Long l) {
        return viewerToViewerCommand.convert(findById(l));
    }

    @Transactional
    @Override
    public ViewerCommand saveRecipeCommand(ViewerCommand command) {
        Viewer detachedViewer = viewerCommandToViewer.convert(command);

//        Set<Viewer> viewerSet = new HashSet<>();
//        viewerRepository.findAll().iterator().forEachRemaining(viewerSet::add);
//
//        if(viewerSet.stream().filter(viewer -> viewer.getUsername().equals(detachedViewer.getUsername()))
//                .findFirst().isPresent()){
//                return
//        }else {
//            Viewer savedViewer = viewerRepository.save(detachedViewer);
//            log.debug("Saved viewer id: " + savedViewer.getId());
//            return viewerToViewerCommand.convert(savedViewer);
//        }

        Viewer savedViewer = viewerRepository.save(detachedViewer);
        log.debug("Saved viewer id: " + savedViewer.getId());
        return viewerToViewerCommand.convert(savedViewer);
    }

    @Override
    public void deleteById(Long idToDelete) {
        viewerRepository.deleteById(idToDelete);
    }
}
