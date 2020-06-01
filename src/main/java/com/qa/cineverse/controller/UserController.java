package com.qa.cineverse.controller;

import com.qa.cineverse.dto.UserDTO;
import com.qa.cineverse.service.UserService;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.Valid;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Controller
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDTO userDTO,
            HttpServletRequest request, Errors errors) {

    }

//    @PostMapping("/registerNewUserAccount")
//    public ResponseEntity<UserDTO> registerNewUserAccount(@RequestBody User accountDTO) throws EmailExistsException {
//        return new ResponseEntity<User> (this.service.registerNewUserAccount (accountDTO), HttpStatus.CREATED);
//    }
//
//    @PostMapping("/registerNewUserAccount")
//    public ResponseEntity<UserDTO> registerNewUserAccount(@RequestBody User accountDTO){
//        return new ResponseEntity<>(this.service.registerNewUserAccount (accountDTO), HttpStatus.CREATED);
//    }

    @GetMapping("register")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "registration";
    }

}
