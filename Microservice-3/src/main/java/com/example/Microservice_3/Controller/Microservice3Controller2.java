package com.example.Microservice_3.Controller;


import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Microservice3Controller2 {

    @GetMapping("/")
    public String microservice3(){

        throw new CustomException("Exception, Testing");
//        return "Hello Microservice3, Successfully Logged In !";
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(HttpServletResponse response, CustomException e) throws IOException {

        return ResponseEntity.status(HttpStatus.SC_EXPECTATION_FAILED).body(e.getMessage());

    }
}
