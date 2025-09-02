package com.example.Microservice_3.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Microservice3Controller2 {

    @GetMapping("/")
    public String microservice3(){
        return "Hello Microservice3, Successfully Logged In !";
    }
}
