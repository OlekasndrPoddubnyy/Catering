package com.catering.controller.validator;

import com.catering.model.Chef;
import com.catering.service.ChefService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ChefValidator implements Validator {

    @Autowired
    private ChefService chefService;

    private static Logger logger = LoggerFactory.getLogger(BuffetValidator.class);

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");

        if (!errors.hasErrors()) {
            logger.debug("confermato: valori non nulli");
            if (this.chefService.alreadyExists((Chef) o)) {
                logger.debug("e' un duplicato");
                errors.reject("duplicato");
            }
        }
    }

    @Override
    public boolean supports(Class<?> aClass) { return Chef.class.equals(aClass);}
}
