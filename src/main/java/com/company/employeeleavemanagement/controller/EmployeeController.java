package com.company.employeeleavemanagement.controller;

import com.company.employeeleavemanagement.model.Employee;
import com.company.employeeleavemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Optional<Employee> updatedEmployee = employeeService.updateEmployee(id, employee);
        return updatedEmployee.orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public boolean deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}/update-leave-days")
    public Employee updateLeaveDays(@PathVariable Long id, @RequestParam int leaveDays) {
        Optional<Employee> updatedEmployee = employeeService.updateLeaveDays(id, leaveDays);
        return updatedEmployee.orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }
}
