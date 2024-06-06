package com.data.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository er;
	
	@GetMapping
	public Page<Employee> getAll(@PageableDefault(sort="name" , direction= Sort.Direction.ASC)Pageable page)
	{
		return er.findAll(page);
	}
	
	
	@GetMapping("/{id}")
	public Employee getId(@PathVariable int id)
	{
		return er.findById(id).orElse(null);
		
	}
	
	@PostMapping
	public Employee saveAll(@RequestBody Employee emp)
	{
		return er.save(emp);
	}

	
	@PutMapping("/{id}")
	public Employee updateId(@PathVariable int id, @RequestBody Employee emp)
	{
		Employee e = er.findById(id).orElseThrow();
		e.setAddress(emp.getAddress());
		e.setName(emp.getName());
		e.setSalary(emp.getSalary());
		
		return er.save(e);
		
	}
	
	
	@DeleteMapping("/{id}")
	
	public void dleteId(@PathVariable int id)
	{
		er.deleteById(id);
	}
	
	
}
