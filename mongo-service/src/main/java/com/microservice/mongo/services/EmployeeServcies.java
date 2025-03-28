package com.microservice.mongo.services;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.microservice.mongo.entity.Employee;
import com.microservice.mongo.repositories.IEmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
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
    public void deleteEmployee(long id) {
        try {
            Optional<Employee> employee = this.employeeRepository.findById(id);

            if (employee.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The employee is not exists");
            }

            Employee updateEmployee = employee.get();

            updateEmployee.setIsRetired(true);
            updateEmployee.setRetiredDate(new Date());
            updateEmployee.setStatus(2);

            employeeRepository.save(updateEmployee);
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
