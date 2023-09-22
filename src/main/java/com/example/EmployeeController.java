package com.example;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository eRepo;

	@PostMapping("/employee")
	public ResponseEntity<Employee> saveData(@RequestBody Employee emp) {
		return new ResponseEntity<>(eRepo.save(emp), HttpStatus.CREATED);
	}

	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getAllData() {
		List<Employee> list = eRepo.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getDataBy(@PathVariable int id) {
		Optional<Employee> obj = eRepo.findById(id);
		if (obj.isPresent()) {
			return new ResponseEntity<>(obj.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);

		}
	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateDataBy(@PathVariable int id, @RequestBody Employee emp) {
		Optional<Employee> obj = eRepo.findById(id);
		if (obj.isPresent()) {
			obj.get().setId(emp.getId());
			obj.get().setName(emp.getName());
			obj.get().setSalary(emp.getSalary());
			return new ResponseEntity<>(obj.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);

		}
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Employee> deleteDataBy(@PathVariable int id) {
		Optional<Employee> obj = eRepo.findById(id);
		if (obj.isPresent()) {
			eRepo.deleteById(id);
			return new ResponseEntity<>(obj.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);

		}
	}

	@GetMapping("/employee/page-One")
	public List<Employee> getPageOne() {
		Pageable paging = PageRequest.of(0, 5, Sort.by("id").ascending());
		Page<Employee> page = eRepo.findAll(paging);
		return page.getContent();
	}
	@GetMapping("/employee/page-Two")
	public List<Employee> getPageTwo() {
		Pageable paging = PageRequest.of(1, 5, Sort.by("id").ascending());
		Page<Employee> page = eRepo.findAll(paging);
		return page.getContent();
	}
	@GetMapping("/employee/page-Three")
	public List<Employee> getPageThree() {
		Pageable paging = PageRequest.of(2, 5, Sort.by("id").ascending());
		Page<Employee> page = eRepo.findAll(paging);
		return page.getContent();
	}

}