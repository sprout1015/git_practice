package com.example.employeegraphql.graphql;

import com.example.employeegraphql.domain.Employee;
import com.example.employeegraphql.repository.EmployeeRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EmployeeGraphQLController {

    private final EmployeeRepository employeeRepository;

    public EmployeeGraphQLController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // ---------------- Query --------------------
    @QueryMapping
    public List<Employee> employees() {
        return employeeRepository.findAll();
    }

    @QueryMapping
    public Employee employee(@Argument Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // ---------------- Mutation -----------------
    @MutationMapping
    public Employee createEmployee(@Argument EmployeeInput input) {
        Employee emp = new Employee(
                input.name(),
                input.age(),
                input.job(),
                input.language(),
                input.pay()
        );
        return employeeRepository.save(emp);
    }

    @MutationMapping
    public Employee updateEmployee(@Argument Long id, @Argument EmployeeInput input) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        emp.setName(input.name());
        emp.setAge(input.age());
        emp.setJob(input.job());
        emp.setLanguage(input.language());
        emp.setPay(input.pay());

        return employeeRepository.save(emp);
    }

    @MutationMapping
    public Boolean deleteEmployee(@Argument Long id) {
        if (!employeeRepository.existsById(id)) return false;
        employeeRepository.deleteById(id);
        return true;
    }
}
