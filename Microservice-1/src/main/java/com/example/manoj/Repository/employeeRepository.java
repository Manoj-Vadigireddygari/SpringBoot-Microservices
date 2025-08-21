package com.example.manoj.Repository;

import com.example.manoj.Model.employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface employeeRepository extends JpaRepository<employee,Long> {

}
