/* =================================

author ankitrajprasad created on 01/02/26 
inside the package - com.example.PracticeProject2.service 

=====================================*/


package com.example.PracticeProject2.service;

import com.example.PracticeProject2.dto.DepartmentDTO;
import com.example.PracticeProject2.entity.Department;
import com.example.PracticeProject2.mapper.EmployeeMapper;
import com.example.PracticeProject2.repository.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;
    private final EmployeeMapper mapper;

    public DepartmentService(DepartmentRepository repository, EmployeeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<DepartmentDTO> getAllDepartments() {
        return repository.findAll().stream()
                .map(mapper::toDepartmentDto)
                .collect(Collectors.toList());
    }

    public DepartmentDTO addDepartment(DepartmentDTO dto) {
        Department dept = new Department();
        dept.setName(dto.departmentName());
        dept.setLocation(dto.location());

        Department saved = repository.save(dept);
        return mapper.toDepartmentDto(saved);
    }

    public DepartmentDTO getDepartmentById(Long id) {
        Department dept = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Department not found with id: " + id));
        return mapper.toDepartmentDto(dept);
    }

    public DepartmentDTO updateDepartment(Long id, DepartmentDTO dto) {
        Department existing = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Department not found with id: " + id));

        existing.setName(dto.departmentName());
        existing.setLocation(dto.location());

        Department saved = repository.save(existing);
        return mapper.toDepartmentDto(saved);
    }

    public void deleteDepartment(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Department not found with id: " + id);
        }
        repository.deleteById(id);
    }
}