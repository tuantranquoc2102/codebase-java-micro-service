package com.microservice.mongo.repositories;

import com.microservice.mongo.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Repository
public interface IEmployeeRepository extends MongoRepository<Employee, Long> {
}
