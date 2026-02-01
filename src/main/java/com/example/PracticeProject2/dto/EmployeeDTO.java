
/* =================================

author ankitrajprasad created on 01/02/26 
inside the package - com.example.PracticeProject2.dto 

=====================================*/


package com.example.PracticeProject2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record EmployeeDTO(
        Long id,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,

        @Positive(message = "Salary must be positive")
        Double salary,

        String department
) {}