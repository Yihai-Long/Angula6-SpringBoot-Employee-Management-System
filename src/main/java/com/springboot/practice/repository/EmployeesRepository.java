package com.springboot.practice.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.springboot.practice.entity.Employees;

public interface EmployeesRepository extends JpaRepository<Employees,Integer> {
	
	
}
