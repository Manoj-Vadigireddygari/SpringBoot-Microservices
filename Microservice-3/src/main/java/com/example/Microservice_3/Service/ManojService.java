package com.example.Microservice_3.Service;

import com.example.Microservice_3.Configuration.FeignConfig;
import com.example.Microservice_3.Model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "MANOJ",url = "${feign.client.Manoj-Serice.url}",configuration = FeignConfig.class)
public interface ManojService {

    @GetMapping("/api/employees")
    public ResponseEntity<List<Employee>> getAllEmployees();
}
