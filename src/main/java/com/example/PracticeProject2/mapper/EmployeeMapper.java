/* =================================

author ankitrajprasad created on 01/02/26 
inside the package - com.example.PracticeProject2.mapper 

=====================================*/


package com.example.PracticeProject2.mapper;

import com.example.PracticeProject2.dto.EmployeeDTO;
import com.example.PracticeProject2.dto.DepartmentDTO;
import com.example.PracticeProject2.entity.Employee;
import com.example.PracticeProject2.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "department.name", target = "department")
    EmployeeDTO toEmployeeDto(Employee employee);

    @Mapping(source = "name", target = "departmentName")
    DepartmentDTO toDepartmentDto(Department department);
}