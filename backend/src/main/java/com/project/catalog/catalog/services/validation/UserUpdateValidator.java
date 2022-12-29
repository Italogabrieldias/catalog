package com.project.catalog.catalog.services.validation;

import com.project.catalog.catalog.dto.UserDTO;
import com.project.catalog.catalog.dto.UserInsertDTO;
import com.project.catalog.catalog.dto.UserUpdateDTO;
import com.project.catalog.catalog.entities.User;
import com.project.catalog.catalog.repositories.UserRepository;
import com.project.catalog.catalog.resource.exeptions.FieldMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(UserUpdateValid ann) {
    }

    @Override
    public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {

       var uriVars =(Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
       long userId = Long.parseLong( uriVars.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        User user = repository.findByEmail(dto.getEmail());
        if (user != null && userId != user.getId()){
            list.add(new FieldMessage("email", "Email already exists"));


        }
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}