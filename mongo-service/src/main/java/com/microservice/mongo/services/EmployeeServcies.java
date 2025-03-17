package com.microservice.mongo.services;

import com.microservice.mongo.entity.Employee;
import com.microservice.mongo.repositories.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServcies implements IEmployeeServices {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public void createNewEmployee(Employee emp) {
        try {
            employeeRepository.save(emp);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateEmployee(Employee emp) {
        try {
            employeeRepository.save(emp);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Employee> listAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long id) {
        Optional<Employee> employeeData = employeeRepository.findById(id);

        return employeeData.orElse(null);
    }
}
