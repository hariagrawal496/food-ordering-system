package com.hcl.servicelmpl;

import org.springframework.stereotype.Service;

import com.hcl.service.AdminService;

@Service
public class AdminServicelmpl implements AdminService {

//Here we are verifying our data whether data insert for admin-name and password is correct or not ...	
	public String checkAdminDataOfLogin(String adminName, String password) {
		
		if (adminName.equalsIgnoreCase("Admin") && password.equals("pass")) {
			return "Admin Login SuccessFully";
		}
		return "Invalid username or password";
	}

}
