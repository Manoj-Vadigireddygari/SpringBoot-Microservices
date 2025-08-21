package com.example.manoj.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private String name;
    private Long salary;

    public Integer getId() {
        return id;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
