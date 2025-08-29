package com.example.Microservice_3.Controller;

import com.example.Microservice_3.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Microservice3Controller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/microservice3/employees")
    public ResponseEntity<List<Employee>> getEmployee() {

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("admin", "admin123");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Using List
        ResponseEntity<List<Employee>> response = restTemplate.exchange(
                "http://localhost:8081/api/employees",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Employee>>() {}
        );
        List<Employee> employees = response.getBody();
       return response;
    }
}
