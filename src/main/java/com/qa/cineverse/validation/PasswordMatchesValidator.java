package com.qa.cineverse.validation;

import com.qa.cineverse.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<AnnotationValidatorCreator.PasswordMatches, Object> {

    @Override
    public void initialize(AnnotationValidatorCreator.PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserDTO user = (UserDTO) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
