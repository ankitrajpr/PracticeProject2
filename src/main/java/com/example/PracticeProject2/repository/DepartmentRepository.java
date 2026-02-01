/* =================================

author ankitrajprasad created on 01/02/26 
inside the package - com.example.PracticeProject2.repository 

=====================================*/


package com.example.PracticeProject2.repository;

import com.example.PracticeProject2.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
}