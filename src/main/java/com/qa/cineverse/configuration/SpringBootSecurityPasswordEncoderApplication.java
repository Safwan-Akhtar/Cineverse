//package com.qa.cineverse.configuration;
//
//import com.qa.cineverse.dto.UserDTO;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@SpringBootApplication
//public class SpringBootSecurityPasswordEncoderApplication {
//
//    private UserDTO user;
//
//    public void encoder() {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String password = user.getPassword();
//        String encodedPassword = passwordEncoder.encode(password);
//        System.out.println();
//        System.out.println("Password is         : " + password);
//        System.out.println("Encoded Password is : " + encodedPassword);
//    }
//
//    public void decoder() {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String password = user.getPassword();
//        String encodedPassword = passwordEncoder.encode(password);
//        System.out.println();
//        System.out.println("Password is         : " + password);
//        System.out.println("Encoded Password is : " + encodedPassword);
//        System.out.println();
//
//        boolean isPasswordMatch = passwordEncoder.matches(password, encodedPassword);
//        System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);
//
//        password = "yawin";
//        isPasswordMatch = passwordEncoder.matches(password, encodedPassword);
//        System.out.println("Password : " + password + "           isPasswordMatch    : " + isPasswordMatch);
//    }
//}
