/* =================================

author ankitrajprasad created on 01/02/26 
inside the package - com.example.PracticeProject2.service 

=====================================*/


package com.example.PracticeProject2.service;

import com.example.PracticeProject2.dto.EmployeeDTO;
import com.example.PracticeProject2.entity.Department;
import com.example.PracticeProject2.entity.Employee;
import com.example.PracticeProject2.mapper.EmployeeMapper;
import com.example.PracticeProject2.repository.DepartmentRepository;
import com.example.PracticeProject2.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository,
                           EmployeeMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }

    public EmployeeDTO addEmployee(EmployeeDTO dto) {
        System.out.println("\n========== ADD EMPLOYEE START ==========");
        System.out.println("1. RECEIVED DTO FROM CLIENT:");
        System.out.println("   DTO Name: " + dto.name());
        System.out.println("   DTO Email: " + dto.email());
        System.out.println("   DTO Salary: " + dto.salary());
        System.out.println("   DTO Department: " + dto.department());

        Employee employee = new Employee();
        employee.setName(dto.name());
        employee.setEmail(dto.email());
        employee.setSalary(dto.salary());

        if (dto.department() != null) {
            System.out.println("\n2. FINDING DEPARTMENT IN DATABASE:");
            Department dept = departmentRepository.findByName(dto.department())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Department not found: " + dto.department()));
            System.out.println("   Found Department ID: " + dept.getId());
            System.out.println("   Found Department Name: " + dept.getName());
            System.out.println("   Found Department Location: " + dept.getLocation());

            employee.setDepartment(dept);
        }

        Employee saved = employeeRepository.save(employee);

        System.out.println("\n3. SAVED EMPLOYEE:");
        System.out.println("   Saved ID: " + saved.getId());
        System.out.println("   Saved Name: " + saved.getName());
        System.out.println("   Saved Department Name: " + saved.getDepartment().getName());

        EmployeeDTO result = mapper.toEmployeeDto(saved);

        System.out.println("\n4. MAPPER CONVERTED TO DTO:");
        System.out.println("   Result DTO Name: " + result.name());
        System.out.println("   Result DTO Department: " + result.department());
        System.out.println("   Result DTO Salary: " + result.salary());
        System.out.println("========== ADD EMPLOYEE END ==========\n");

        return result;
    }

    public Page<EmployeeDTO> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .map(mapper::toEmployeeDto);
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Employee not found with id: " + id));

        System.out.println("Found employee: " + employee.getName());
        System.out.println("Department name: " + employee.getDepartment().getName());

        return mapper.toEmployeeDto(employee);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Employee not found with id: " + id));

        existing.setName(dto.name());
        existing.setEmail(dto.email());
        existing.setSalary(dto.salary());

        if (dto.department() != null) {
            Department dept = departmentRepository.findByName(dto.department())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Department not found: " + dto.department()));
            existing.setDepartment(dept);
        }

        Employee saved = employeeRepository.save(existing);
        return mapper.toEmployeeDto(saved);
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
}