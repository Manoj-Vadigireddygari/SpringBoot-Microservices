package com.example.Microservice_3.Controller;

import com.example.Microservice_3.Model.Employee;
import com.example.Microservice_3.Service.ManojService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/microservice3")
public class Microservice3Controller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/employees")
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

    @Autowired
    public RestClient restClient;

    @GetMapping("/restclient/employees")
    public ResponseEntity<List<Employee>> getRestClientEmployee() {
        List<Employee> response = restClient
                .get()
                .uri("http://localhost:8081/api/employees")
                .headers(headers -> headers.setBasicAuth("admin", "admin123"))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<Employee>>() {});

        return ResponseEntity.ok(response);
    }

    @Autowired
    public ManojService manojService;

    @GetMapping("/feignclient/employees")
    public ResponseEntity<List<Employee>> getFeignClientEmployee() {

       ResponseEntity<List<Employee>> response = manojService.getAllEmployees();

       return response;

    }
}
