package com.pakhi.agile.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pakhi.agile.beans.AgileUser;
import com.pakhi.agile.exceptions.UserAlreadyExistsException;
import com.pakhi.agile.jparepository.AgileUserRepository;

@RestController
@RequestMapping("/register")
public class AgileUserController {
	private final AgileUserRepository agileUserRepository;
	
	@Autowired
	public AgileUserController(AgileUserRepository agileUserRepository) {
		this.agileUserRepository = agileUserRepository;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add(@RequestBody AgileUser agileUser) {
		checkEmailId(agileUser.getEmailId());
		
		return ResponseEntity.ok(this.agileUserRepository.save(agileUser));
	}
	
	private void checkEmailId(String emailId) {
		if(this.agileUserRepository.findByEmailId(emailId).isPresent()) throw new UserAlreadyExistsException(emailId);
	}
}
