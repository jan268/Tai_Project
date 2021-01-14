package com.example.demo.Validation;

import com.example.demo.model.Viewer;
import com.example.demo.repositories.ViewerRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class EmailValidation implements ConstraintValidator<UniqueEmail, String> {

    private final ViewerRepository viewerRepository;

    public EmailValidation(ViewerRepository viewerRepository) {
        this.viewerRepository = viewerRepository;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Set<Viewer> viewerSet = new HashSet<>();
        viewerRepository.findAll().iterator().forEachRemaining(viewerSet::add);

        if(viewerSet.stream().filter(viewer -> viewer.getEmail().equals(s))
                .findFirst().isPresent()){
            return false;
        }else return true;
    }
}
