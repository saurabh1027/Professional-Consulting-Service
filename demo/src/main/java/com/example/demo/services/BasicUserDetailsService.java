package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.models.BasicUserDetails;
import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepo;

@Component
public class BasicUserDetailsService implements UserDetailsService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employee employee = employeeRepo.findByEmail(email);
		if(employee == null)
			return null;
		else
			return new BasicUserDetails(employee);
	}

}
