/* =================================

author ankitrajprasad created on 01/02/26 
inside the package - com.example.PracticeProject2.dto 

=====================================*/


package com.example.PracticeProject2.dto;
import jakarta.validation.constraints.NotBlank;

public record DepartmentDTO(
        Long id,

        @NotBlank(message = "Department name is required")
        String departmentName,

        @NotBlank(message = "Location is required")
        String location
) {}
