package com.springboot.practice.service;

import java.util.List;
import java.util.Optional;


import com.springboot.practice.entity.Employees;

public interface IEmployeesService {
    Employees save(Employees employees);
    
    boolean isExistEmployees(Employees employees);
    
    List<Employees> searchEmployeesList(String name, Integer id);
    
    boolean loginEmployeeesList(String name, String password);
    
    boolean deleteEmployees(Integer id);
    
    public Employees getOne(Integer id);
}
