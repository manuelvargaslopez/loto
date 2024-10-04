package br.manodo.loto.loto.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy= NotNullRangeValidator.class )
public @interface ListNumberValidacao {
  
    int min(); 
    int max();   

    String message()  default "A lista Annotation não pode ser vazia da interface";

    Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
