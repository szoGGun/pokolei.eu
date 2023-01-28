package eu.przemyslawrutkowski.pokolei.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidTravelClassValidator.class)
@Documented
public @interface ValidTravelClass {
    String message() default "Klasa wagonu musi wynosić 1 lub 2";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


