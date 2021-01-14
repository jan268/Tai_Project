package com.example.demo.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidation.class)
public @interface UniqueEmail {
    String message() default "Email isn't unique";
    Class<?>[]groups()default {};
    public abstract Class<? extends Payload>[] payload() default {};
}
