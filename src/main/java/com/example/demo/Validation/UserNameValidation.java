package com.example.demo.Validation;

import com.example.demo.model.Viewer;
import com.example.demo.repositories.ViewerRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class UserNameValidation implements ConstraintValidator<UniqueUsername, String> {

    private final ViewerRepository viewerRepository;

    public UserNameValidation(ViewerRepository viewerRepository) {
        this.viewerRepository = viewerRepository;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        Set<Viewer> viewerSet = new HashSet<>();
        viewerRepository.findAll().iterator().forEachRemaining(viewerSet::add);

        if(viewerSet.stream().filter(viewer -> viewer.getUsername().equals(s))
                .findFirst().isPresent()){
                return false;
        }else return true;
    }
}
