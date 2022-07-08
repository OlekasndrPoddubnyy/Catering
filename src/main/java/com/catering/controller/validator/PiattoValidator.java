package com.catering.controller.validator;


import com.catering.model.Piatto;
import com.catering.service.PiattoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PiattoValidator implements Validator {

    @Autowired
    private PiattoService piattoService;

    private static Logger logger = LoggerFactory.getLogger(PiattoValidator.class);

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");

        if (!errors.hasErrors()) {
            logger.debug("confermato: valori non nulli");
            if (this.piattoService.alreadyExists((Piatto) o)) {
                logger.debug("e' un duplicato");
                errors.reject("duplicato");
            }
        }
    }

    public void validate2(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");

        if (!errors.hasErrors()) {
            logger.debug("confermato: valori non nulli");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) { return Piatto.class.equals(aClass);}
}
