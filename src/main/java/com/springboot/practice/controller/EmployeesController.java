package com.springboot.practice.controller;

import java.util.List;
import java.util.Optional;

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

@CrossOrigin(origins = "http://localhost:4100")
@Controller
@RequestMapping(value = "/employees")
public class EmployeesController {
	
    @Autowired
    private IEmployeesService employeesService;
    
    /**
     * Description: redirect to list page
     */
    @RequestMapping(value="/list")
    public ModelAndView list() {
    	ModelAndView mv = new ModelAndView("/list");
    	return mv;
    }
    
    
    @ResponseBody
	@RequestMapping(value="/getData")
    public JSONArray employeesList(String name, Integer id) {
		try {
			List<Employees> employeesList = 
			employeesService.searchEmployeesList(name, id);
			JSONArray jsonArray = new JSONArray();
			jsonArray.addAll(employeesList);
			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    
    @ResponseBody
    @RequestMapping(value="/getData/{id}/{name}")
    public JSONArray searchForEmp( @PathVariable("id") Integer id,@PathVariable("name") String name) {
		try {
			List<Employees> employeesList = 
			employeesService.searchEmployeesList(name, id);
			JSONArray jsonArray = new JSONArray();
			jsonArray.addAll(employeesList);
			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    
    @ResponseBody
    @RequestMapping(value="/getData/{id}")
    public JSONArray getemp( @PathVariable("id") Integer id) {
		try {
			List<Employees> employeesList = 
			employeesService.searchEmployeesList(null, id);
			JSONArray jsonArray = new JSONArray();
			jsonArray.addAll(employeesList);
			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    
    @ResponseBody
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public JSONObject delete(Integer id) {
    	try {
			employeesService.deleteEmployees(id);
			String result = "{\"result\":\"success\"}";
			return JSONObject.fromObject(result);
		} catch (Exception e) {
			e.printStackTrace();
			String result = "{\"result\":\"fail\"}";
			return JSONObject.fromObject(result);
		}
    }
    
    
   
    @RequestMapping(value = "/addPage")
    public ModelAndView add(AddModel model) {
        ModelAndView mv = new ModelAndView("/add");
        return mv;
    }
    

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addEmployee(AddModel model) {
        try {
            Employees employee = new Employees(model);
            employeesService.save(employee);
            return new ModelAndView("redirect:/employees/list");
        } catch (Exception e) {
            return new ModelAndView("/error");
        }
    }
    
    
    //delete angular
    @ResponseBody
    @RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.POST)
    public boolean deleteEmployee(@PathVariable("id") Integer id) 
    	throws Exception {
    		return employeesService.deleteEmployees(id);
   
    } 
        
    
    
    //add angular
    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public ResponseEntity<AddModel> saveEmployee(@RequestBody AddModel model) throws Exception {
            Employees employees = new Employees();
            employees.setName(model.getName());
            employees.setPassword(model.getPassword());
            employees.setPhoneNumber(model.getPhoneNumber());
            employees.setSupervisors(model.getSupervisors());
         	Employees emp= employeesService.save(employees);
         	
         	AddModel newModel= new AddModel();
         	newModel.setEmpId(emp.getId());
         	newModel.setName(emp.getName());
         	newModel.setPhoneNumber(emp.getPhoneNumber());
         	newModel.setSupervisors(emp.getSupervisors());
         	newModel.setPassword(emp.getPassword());
        	
    		return new ResponseEntity<>(newModel, HttpStatus.OK);
    
    		
      }
    
    
    
  //update angular
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ResponseEntity<Employees> editEmployee(@PathVariable("id") Integer id,@RequestBody AddModel model) throws Exception {
   Employees oldEmp=employeesService.getOne(id);
   oldEmp.setName(model.getName()); 	 
   oldEmp.setPassword(model.getPassword());
   oldEmp.setPhoneNumber(model.getPhoneNumber());
   oldEmp.setSupervisors(model.getSupervisors());
   
   return new ResponseEntity<>(employeesService.save(oldEmp), HttpStatus.OK);
    		
         	
    		
      }
    
    
}



