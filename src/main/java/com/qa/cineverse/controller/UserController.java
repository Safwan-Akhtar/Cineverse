package com.qa.cineverse.controller;

import com.qa.cineverse.domain.User;
import com.qa.cineverse.dto.UserDTO;
import com.qa.cineverse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }



    @GetMapping("currentUsername")
    public String managersStatusCheck(Authentication authentication) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
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


    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDTO>> getAllUser(){
        return ResponseEntity.ok(this.service.readUser ());
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
        return new ResponseEntity<>(this.service.createUser (user), HttpStatus.CREATED);
    }


//    @PostMapping("registerNewUserAccount")
//    public ModelAndView registerUserAccount(
//            @ModelAttribute("user") @Valid UserDTO userDTO,
//            HttpServletRequest request, Errors errors) {
//
//        try {
//            User registered = service.registerNewUserAccount(userDTO);
//        } catch (UserAlreadyExistsException uaeEx) {
//            ModelAndView mav = new ModelAndView("register.html", "user", userDTO);
//            mav.addObject("message", "An account for that username/email already exists.");
//            return mav;
//        }
//
//        return new ModelAndView("index.html", "user", userDTO);
//    }


}
