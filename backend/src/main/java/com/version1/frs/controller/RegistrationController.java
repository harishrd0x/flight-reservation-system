                                    package com.version1.frs.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
import com.version1.frs.model.User;
import com.version1.frs.repository.UserRepository;
 
@RestController
@CrossOrigin
public class RegistrationController {
	UserRepository usreop;
 
	@Autowired
	public RegistrationController(UserRepository usreop) {
		super();
		this.usreop = usreop;
	}
	@PostMapping("/creatuse")
	public String docreate(@RequestBody User use) {
		usreop.save(use);
		return "user inserted bro";
	}


 
}
