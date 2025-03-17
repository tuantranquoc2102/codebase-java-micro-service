package com.microservice.mongo.services;

import com.microservice.mongo.entity.Employee;

import java.util.List;

public interface IEmployeeServices {
    void createNewEmployee(Employee emp);
    void updateEmployee(Employee emp);
    List<Employee> listAll();
    Employee findById(long id);
}
