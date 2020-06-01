package com.qa.cineverse.controller;

import com.qa.cineverse.domain.User;
import com.qa.cineverse.dto.UserDTO;
import com.qa.cineverse.exception.UserAlreadyExistsException;
import com.qa.cineverse.service.MyUserService;
import com.qa.cineverse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

//    public ModelAndView registerUserAccount(
//            @ModelAttribute("user") @Valid UserDTO userDTO,
//            HttpServletRequest request, Errors errors) {
//
//    }

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
        return "register";
    }

    @PostMapping("register")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDTO userDTO,
            HttpServletRequest request, Errors errors) {

        try {
            User registered = service.registerNewUserAccount(userDTO);
        } catch (UserAlreadyExistsException uaeEx) {
            ModelAndView mav = new ModelAndView("registration", "user", userDTO);
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }

        return new ModelAndView("successRegister", "user", userDTO);
    }


}
