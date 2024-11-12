package com.leon.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.leon.webapp.model.Employee;
import com.leon.webapp.service.EmployeeService;

import lombok.Data;

@Controller
@Data
public class EmployeeController {
	@Autowired
	private EmployeeService service;

	@GetMapping("/employees")
	public String employees(Model model) {

		Iterable<Employee> listEmployee = service.getEmployees();
		model.addAttribute("employees", listEmployee);

		return "employees";
	}

	@GetMapping("/createEmployee")
	public String createEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new";
	}

	
	@GetMapping("/updateEmployee/{id}")
	public String updateEmployee(@PathVariable("id") final int id, Model model) {
		Employee e = service.getEmployee(id);

		if (e != null) {
			model.addAttribute("employee", e);
			return "update";
		}

		return "redirect:/employees";
	}

	@GetMapping("/deleteEmployee/{id}")
	public ModelAndView deleteEmployee(@PathVariable("id") final int id) {
		service.deleteEmployee(id);
		return new ModelAndView("redirect:/employees");
	}

	@PostMapping("/saveEmployee")
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
		if (employee.getId() != null) {
			
			Employee current = service.getEmployee(employee.getId());
			employee.setPassword(current.getPassword());
		}
		service.saveEmployee(employee);
		return new ModelAndView("redirect:/employees");
	}

}
