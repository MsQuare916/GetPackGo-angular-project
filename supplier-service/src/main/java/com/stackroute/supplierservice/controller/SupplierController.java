package com.stackroute.supplierservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.supplierservice.model.SupplierInfo;
import com.stackroute.supplierservice.repo.SupplierRepository;

@RestController
@RequestMapping("/api/v1")
public class SupplierController {
	
	@Autowired
	private SupplierRepository supplierRepository;	
	
	
	@PostMapping("/service/post")
	public ResponseEntity<?> addSupplierInfo(@RequestBody SupplierInfo supplierInfo)
	{
		SupplierInfo save = this.supplierRepository.save(supplierInfo);
		return ResponseEntity.ok(save);
	}
		
	@GetMapping("/service/get")
	public ResponseEntity<?> getSupplierInfo()
	{
		return ResponseEntity.ok(this.supplierRepository.findAll());
	}
	
//	@GetMapping("/hello")
//	public String hello() {
//		return "hello world";
//	}
}
