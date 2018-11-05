
package com.springboot.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.practice.entity.Employees;
import com.springboot.practice.model.AddModel;
import com.springboot.practice.service.IEmployeesService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
* Description:login controller
* 
*/
@CrossOrigin(origins = "http://localhost:4100")
@Controller
@RequestMapping(value="/common")
public class LoginController {

	@Autowired
	private IEmployeesService employeesService;
	
	@RequestMapping(value="/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("/login");
		return mv;
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView loginPost(Employees employees) {
		try {
			ModelAndView mv;
			if (employeesService.isExistEmployees(employees)) {
				mv = new ModelAndView("redirect:/employees/list"); 
				return mv;
			}
			mv = new ModelAndView("/login");
			mv.addObject("error", "The ID or Password you entered does not match our records.");
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			ModelAndView mv = new ModelAndView("/login");
			mv.addObject("error", "Failed");
			return mv;
		}
		
	}
	
	
	//login for angular
	@ResponseBody
	@RequestMapping(value="/loginPage/{name}/{password}")
	public boolean loginRequest(@PathVariable("name") String name,@PathVariable("password") String password)  
	throws Exception {
		System.out.println("Inside loginPage");
		 boolean employeesList = 
		employeesService.loginEmployeeesList(name, password);
		 //JSONArray jsonArray = new JSONArray();
		 //jsonArray.addAll(employeesList);
		return employeesList;
		
	} 
}
	
	
	
	

