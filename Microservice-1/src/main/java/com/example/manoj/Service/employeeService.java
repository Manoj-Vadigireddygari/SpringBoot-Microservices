package com.example.manoj.Service;


import com.example.manoj.Model.employee;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.manoj.Repository.employeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class employeeService {

    @Autowired
    public employeeRepository repo;

    public List<employee> getEmployees(){

        return repo.findAll();
    }

    public Optional<employee> getEmployeeById(Long id){

        return repo.findById(id);
    }

    public employee saveEmployee(employee emp){

        return repo.save(emp);
    }

    public Optional<employee> updateEmployee(Long id, employee emp){

        return repo.findById(id).map(e -> {

                e.setName(emp.getName());
                e.setSalary(emp.getSalary());

                return repo.save(e);
        });
    }

    public Optional<employee> deleteEmployee(Long id){
        return repo.findById(id).map(u -> {
            repo.delete(u);
            return u;
        });
    }

}
