package com.example.Microservice_3.Controller;

import com.example.Microservice_3.Model.Employee;
import com.example.Microservice_3.Service.ManojService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${Manoj.service.url}")
     String producturl;

    @GetMapping("/employees")
    @RateLimiter(name = "employeeServiceLimiter", fallbackMethod = "rateLimitFallback")
    @Bulkhead(name = "MANOJService",type = Bulkhead.Type.SEMAPHORE,fallbackMethod = "bulkheadFallBack")
    public ResponseEntity<List<Employee>> getEmployee() {

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("admin", "admin123");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Using List
        ResponseEntity<List<Employee>> response = restTemplate.exchange(
                producturl+"/api/manoj/employees",
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
    @Bulkhead(name = "ManojService",type = Bulkhead.Type.THREADPOOL,fallbackMethod = "bulkheadFallBack")
    public ResponseEntity<List<Employee>> getRestClientEmployee() {
        List<Employee> response = restClient
                .get()
                .uri("http://localhost:8081/api/manoj/employees")
                .headers(headers -> headers.setBasicAuth("admin", "admin123"))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<Employee>>() {});

        return ResponseEntity.ok(response);
    }

    @Autowired
    public ManojService manojService;

    @GetMapping("/feignclient/employees")
    @Retry(name = "MANOJService",fallbackMethod = "retryFallBack")
    @CircuitBreaker(name = "MANOJService",fallbackMethod = "circuitBreakerFallBack")
    public ResponseEntity<List<Employee>> getFeignClientEmployee() {
        try {
            ResponseEntity<List<Employee>> response = manojService.getAllEmployees();
            return response;
        }catch (Exception e){

            System.out.println("Not able to invoke empployee API");
            throw e;
        }


    }

    public ResponseEntity<?> rateLimitFallback(Throwable t){

        return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body(t.getMessage());
    }

    public ResponseEntity<?> bulkheadFallBack(Throwable t){

        return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body(t.getMessage());
    }

    public ResponseEntity<?> retryFallBack(Throwable t){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(t.getMessage());
    }
    public ResponseEntity<?> circuitBreakerFallBack(Throwable t){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(t.getMessage());
    }
}
