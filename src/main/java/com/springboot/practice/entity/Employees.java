package com.springboot.practice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.springboot.practice.model.AddModel;

@Entity
@Table(name = "employees")
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID", nullable = false)
    private Integer id;
    
    @Column(name = "NAME", length = 45)
    private String name;
    @Column(name = "PHONE_NUMBER", length = 45)
    private String phoneNumber;
    @Column(name = "SUPERVISORS", length = 45)
    private String supervisors;
    @Column(name = "PASSWORD", length = 45)
    private String password;

    public Employees(String name, String phoneNumber, String supervisors, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.supervisors = supervisors;
        this.password = password;
    }

    public Employees() {
    }

    public Employees(AddModel model) {
        this.name = model.getName();
        this.phoneNumber = model.getPhoneNumber();
        this.supervisors = model.getSupervisors();
        this.password = model.getPassword();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSupervisors() {
        return supervisors;
    }

    public void setSupervisors(String supervisors) {
        this.supervisors = supervisors;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
