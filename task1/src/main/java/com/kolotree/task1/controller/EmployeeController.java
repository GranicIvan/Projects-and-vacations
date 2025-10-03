package com.kolotree.task1.controller;

import com.kolotree.task1.dto.employee.EmployeePatchDto;
import com.kolotree.task1.mapper.EmployeeMapper;
import com.kolotree.task1.model.Employee;
import com.kolotree.task1.repository.EmployeeRepo;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepo employeeRepo;

    public EmployeeController(EmployeeRepo employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    @GetMapping
    public Iterable<Employee> getAll(){
        return employeeRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getOne(@PathVariable Integer id) {
        return employeeRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeRepo.save(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id){
        if(!employeeRepo.existsById(id)) return ResponseEntity.notFound().build();
        employeeRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchEmployee(@PathVariable Integer id, @Valid @RequestBody EmployeePatchDto dto){

        //Body must same at least one field
        if (dto.getFirstName() == null && dto.getLastName() == null &&
                dto.getDateOfBirth() == null && dto.getEmail() == null &&
                dto.getAddress() == null && dto.getVacationDaysLeft() == null &&
                dto.getUserType() == null) {
            return ResponseEntity.badRequest().body("At least one field must be provided.");
        }

        //Name can't be blank
        if (dto.getFirstName() != null && dto.getFirstName().isBlank())
            return ResponseEntity.badRequest().body("firstName must not be blank when provided.");
        if (dto.getLastName() != null && dto.getLastName().isBlank())
            return ResponseEntity.badRequest().body("lastName must not be blank when provided.");


        return employeeRepo.findById(id)
                .map(existing -> {
                    EmployeeMapper.applyPatch(existing, dto);
                    var saved = employeeRepo.save(existing);
                    return ResponseEntity.ok(saved); // or map to an EmployeeResponse DTO if you have one
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
