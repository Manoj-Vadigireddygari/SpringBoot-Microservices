package com.example.manoj.Controller;


import com.example.manoj.Model.employee;
import com.example.manoj.Service.ProducerService;
import com.example.manoj.Service.employeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class employeeController {

    @Autowired
    public employeeService serv;

    @Autowired
    public ProducerService producer;


    @GetMapping("/employees")
    @Cacheable(value = "employees")
    public List<employee> findAllEmployees(){

        return serv.getEmployees();
    }

    @GetMapping("/employee/{id}")
    @Cacheable(value = "emp" , key ="#id")
    public Optional<employee> findEmployeeById(@PathVariable Long id){

        return serv.getEmployeeById(id);
    }


    @PostMapping("/employee/save")
    @CachePut (value = "emp", key="#result.id")
    @CacheEvict(value = "employees", allEntries = true)
    public employee saveEmployee(@RequestBody employee emp){

        return serv.saveEmployee(emp);
    }


    @PutMapping("/employee/{id}")
    @CachePut(value = "emp", key = "#result.id" )
    @CacheEvict(value = "employees", allEntries = true)
    public Optional<employee> updateEmployee(@PathVariable long id, @RequestBody employee emp){

        return serv.updateEmployee(id,emp);

    }

    @DeleteMapping("/employee/{id}")
    @Caching(evict = { @CacheEvict(value = "emp", key = "#result.id"), @CacheEvict(value = "employees", allEntries = true)})
    public Optional<employee> deleteEmployee(@PathVariable long id){

        return  serv.deleteEmployee(id);
    }

    @GetMapping("/publish")
    public void sendKafkaMessage( @RequestParam String message){

        producer.sendMessage("demo_topic",message);

        System.out.println("Message send to topic : " + message);

    }
}
