package com.example.demo.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.employee.model.Employees;
import com.example.demo.employee.repository.EmployeesRepository;

@Controller
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeesRepository employeeRepository;

    // Show all employees
    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "index";
    }

    // Show form to create new employee
    @GetMapping("/create")
    public String createEmployeeForm(Model model) {
        model.addAttribute("employee", new Employees());
        return "create";
    }

    // Save a new employee
    @PostMapping("/create")
    public String saveEmployee(@ModelAttribute Employees employee) {
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

    // Show form to update employee
    @GetMapping("/update/{id}")
    public String updateEmployeeForm(@PathVariable Long id, Model model) {
        Employees employee = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("employee", employee);
        return "update";
    }

    // Update employee
    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employees employee) {
        employee.setId(id);
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

    // Delete employee
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return "redirect:/employees";
    }
}

