/* =================================

author ankitrajprasad created on 01/02/26 
inside the package - com.example.PracticeProject2.controller 

=====================================*/


package com.example.PracticeProject2.controller;

import com.example.PracticeProject2.dto.DepartmentDTO;
import com.example.PracticeProject2.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return new ResponseEntity<>(service.getAllDepartments(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> addDepartment(@Valid @RequestBody DepartmentDTO dto) {
        DepartmentDTO created = service.addDepartment(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getDepartmentById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentDTO dto) {
        return new ResponseEntity<>(service.updateDepartment(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        service.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }
}