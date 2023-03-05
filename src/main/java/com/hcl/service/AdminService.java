package com.hcl.service;

import org.springframework.stereotype.Service;

@Service
public interface AdminService {

	public String checkAdminDataOfLogin(String adminName, String password) ;
	

}
