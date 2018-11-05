package com.springboot.practice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;


import com.mysql.jdbc.StringUtils;
import com.springboot.practice.entity.Employees;
import com.springboot.practice.repository.EmployeesRepository;
import com.springboot.practice.service.IEmployeesService;

import net.sf.json.JSONArray;

@Service
public class EmployeesServiceImpl implements IEmployeesService {
    @Autowired
    EmployeesRepository repository;

    @Override
    public Employees save(Employees employees) {
       Employees emp= repository.save(employees);
       return emp;
    }

	@Override
	public boolean isExistEmployees(Employees employees) {
		//ExampleMatcher exampleMatcher = ExampleMatcher.matching();
		Example<Employees> example = Example.of(employees);
		return repository.exists(example);}

	
	@Override
	public List<Employees> searchEmployeesList(String name, Integer id) {
		Employees employees = new Employees();
		if (!StringUtils.isNullOrEmpty(name)) {
			employees.setName(name);
			System.out.print("name is"+name);
		}
		if (id != null) {
			employees.setId(id);
			System.out.print("id is"+id);
		}
		if ((!StringUtils.isNullOrEmpty(name)) || id != null) {
			//ExampleMatcher exampleMatcher = ExampleMatcher.matching();
			Example<Employees> example = Example.of(employees);
			return repository.findAll(example);
		}else {
			System.out.print("id is "+id+" name is "+name);
			return repository.findAll(); //return all if no input 
		}
		
	}
	
	
	@Override
	public boolean loginEmployeeesList(String name, String password) {
		Employees employees = new Employees();
		
			employees.setName(name);
			employees.setPassword(password);
			//ExampleMatcher exampleMatcher = ExampleMatcher.matching();
			Example<Employees> example = Example.of(employees);
			List<Employees> empList=repository.findAll(example);
			System.out.println("lenthg is"+ empList.size());
			if(empList.size()>0)
			return true;
			else return false;
			
	}

	@Override
	public boolean deleteEmployees(Integer id) {
		System.out.print("delete id "+id);
		Employees employees = new Employees();
		employees.setId(id);
		Example<Employees> example = Example.of(employees);
		employees = repository.findOne(example);
		repository.delete(employees);
		return true;
	
	}
	
	@Override
	public Employees getOne(Integer id) {
		return repository.getOne(id);
	}
	
	
	
}
