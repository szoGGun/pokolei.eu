package eu.przemyslawrutkowski.pokolei.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class ValidTravelClassValidator implements ConstraintValidator<ValidTravelClass, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && (value == 1 || value == 2);
    }
}
