package com.microservice.mongo.repositories;

import com.microservice.mongo.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface IEmployeeRepository extends MongoRepository<Employee, Long> {
}
