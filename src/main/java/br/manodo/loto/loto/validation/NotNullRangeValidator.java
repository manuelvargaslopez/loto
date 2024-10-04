package br.manodo.loto.loto.validation;

import java.util.Set;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotNullRangeValidator implements ConstraintValidator<ListNumberValidacao, Set<Integer>>{

    private Integer min;
    private Integer max;  
   
    @Override
    public boolean isValid( Set<Integer> conjunto, 
                            ConstraintValidatorContext constraintValidatorContext) { 
     
      if (conjunto.contains(null) || conjunto.isEmpty()){
        return false;
      } 

      for(Integer numero : conjunto){
        if( numero < min  || numero > max ){
          return  false;  
        }
      }
      return true;
    }

    @Override
    public void initialize(ListNumberValidacao constraintAnnotation) {

       this.min =  constraintAnnotation.min();
       this.max =  constraintAnnotation.max();
    }    
}
