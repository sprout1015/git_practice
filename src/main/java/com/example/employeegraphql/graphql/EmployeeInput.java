package com.example.employeegraphql.graphql;

public record EmployeeInput(
        String name,
        int age,
        String job,
        String language,
        int pay
) {}
