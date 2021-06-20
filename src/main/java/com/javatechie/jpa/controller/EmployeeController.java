package com.javatechie.jpa.controller;

import com.javatechie.jpa.dto.Employee;
import com.javatechie.jpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //get all employee

    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    //Create employee

    @PostMapping("/employees")
    public Employee createEmployees(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }
 // get employee id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("user does not exist {}"));
        return ResponseEntity.ok(emp);
    }

    // update employee id
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @RequestBody  Employee employee) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("user does not exist {}"));
        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
        emp.setEmailId(employee.getEmailId());
       Employee employeeFromDB = employeeRepository.save(emp);
        return ResponseEntity.ok(employeeFromDB);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("user does not exist {}"));
        employeeRepository.delete(emp);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
