package com.example.demo.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ViewerValidation.class)
public @interface UniqueViewer {
    String message() default "Viewer isn't unique";
    Class<?>[]groups()default {};
    public abstract Class<? extends Payload>[] payload() default {};
}
