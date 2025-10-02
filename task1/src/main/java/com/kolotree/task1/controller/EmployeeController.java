package com.kolotree.task1.controller;

import com.kolotree.task1.model.Employee;
import com.kolotree.task1.model.Project;
import com.kolotree.task1.repository.EmployeeRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empolyee")
public class EmployeeController {

    private final EmployeeRepo employeeRepo;

    public EmployeeController(EmployeeRepo employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/getAll")
    public Iterable<Employee> getAll(){
        return employeeRepo.findAll();
    }

    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeRepo.save(employee);
    }

    @DeleteMapping("/deleteEmployee")
    public void deleteEmployee(@RequestParam Integer id){
        employeeRepo.deleteById(id); // TODO da vraca da li je uspelo ili ne
    }

}
