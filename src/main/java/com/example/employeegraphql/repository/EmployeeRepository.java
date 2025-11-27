package com.example.employeegraphql.repository;

import com.example.employeegraphql.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
