package com.example.demo.Validation;

import com.example.demo.Command.ViewerCommand;
import com.example.demo.model.Viewer;
import com.example.demo.repositories.ViewerRepository;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ViewerValidation implements ConstraintValidator<UniqueViewer, ViewerCommand> {

    private final ViewerRepository viewerRepository;

    public ViewerValidation(ViewerRepository viewerRepository) {
        this.viewerRepository = viewerRepository;
    }

    public void initialize(UniqueViewer constraint) {
    }

    @Override
    public boolean isValid(ViewerCommand viewerCommand, ConstraintValidatorContext constraintValidatorContext) {
        Set<Viewer> viewerSet = new HashSet<>();
        viewerRepository.findAll().iterator().forEachRemaining(viewerSet::add);

        if (viewerSet.stream().filter(workingViewer -> workingViewer.getId().equals(viewerCommand.getId())) //this line looks if there is such a record in DB
                .findFirst().isPresent()){
            if(viewerSet.stream().filter(viewer -> viewer.getUsername().equals(viewerCommand.getUsername())) //if there is Username in DB
                    .findFirst().isPresent()) {
                if (viewerSet.stream().filter(viewer -> viewer.getUsername().equals(viewerCommand.getUsername())) //if Username in DB id is EQUAL to Username in FORM id
                        .findFirst().get().getId().equals(viewerCommand.getId())) {
                    return true;
                } else return false;
            }else {
                if (viewerSet.stream().filter(viewer -> viewer.getEmail().equals(viewerCommand.getEmail()))
                        .findFirst().isPresent()) {
                    if (viewerSet.stream().filter(viewer -> viewer.getEmail().equals(viewerCommand.getEmail()))
                            .findFirst().get().getId().equals(viewerCommand.getId())) {
                        return true;
                    } else return false;
                }else return true;
            }
        }else {
            if(viewerSet.stream().filter(viewer -> viewer.getUsername().equals(viewerCommand.getUsername()))
                    .findFirst().isPresent()){
                return false;
            }else {
                if(viewerSet.stream().filter(viewer -> viewer.getEmail().equals(viewerCommand.getEmail()))
                        .findFirst().isPresent()){
                    return false;
                }else return true;
            }
        }
    }
}
