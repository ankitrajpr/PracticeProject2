/* =================================

author ankitrajprasad created on 01/02/26 
inside the package - com.example.PracticeProject2.repository 

=====================================*/


package com.example.PracticeProject2.repository;

import com.example.PracticeProject2.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findAll(Pageable pageable);
}