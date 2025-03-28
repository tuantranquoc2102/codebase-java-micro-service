package com.microservice.mongo.controller;

import com.microservice.mongo.anotation.UserRole;
import com.microservice.mongo.entity.Employee;
import com.microservice.mongo.services.IEmployeeServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.QueryParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeController {

    @Autowired
    private IEmployeeServices employeeServices;

    @UserRole({"1"})
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> list(HttpServletRequest request) {
        List<Employee> emp = new ArrayList<>();
        try {
            emp = employeeServices.listAll();
        } catch(Exception e) {
            return new ResponseEntity<>(emp, HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @UserRole({"1", "2"})
    @GetMapping("/detail")
    public ResponseEntity<Employee> findById(@RequestParam("id")long id) {
        Employee employee = employeeServices.findById(id);

        if (employee == null) {
            return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @UserRole({"1"})
    @PostMapping("/create")
    public ResponseEntity<String> createNewEmployee(@RequestBody() Employee employee) {
        try {
            employeeServices.createNewEmployee(employee);
        } catch (Exception e) {
            return new ResponseEntity<>("Fail to save employee" + e.getMessage(), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>("Create a new employee successfully", HttpStatus.OK);
    }

    @UserRole({"1", "2"})
    @PostMapping("/update")
    public ResponseEntity<String> updateEmployee(@RequestBody() Employee employee) {
        try {
            Employee emp = employeeServices.findById(employee.getId());

            if (emp == null)  {
                return new ResponseEntity<>("Your employee is not exist", HttpStatus.BAD_REQUEST);
            }

            ModelMapper modelMapper =new ModelMapper();

            emp = modelMapper.map(employee, Employee.class);

            employeeServices.createNewEmployee(emp);
        } catch (Exception e) {
            return new ResponseEntity<>("Fail to save employee" + e.getMessage(), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>("Update an employee successfully", HttpStatus.OK);
    }

    @UserRole({"1"})
    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
        try {
            employeeServices.deleteEmployee(id);


        } catch (Exception e) {
            return new ResponseEntity<>("Fail to delete employee" + e.getMessage(), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>("Delete an employee successfully", HttpStatus.OK);
    }
}
