package com.example.demo.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.employee.model.Employees;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {
}

