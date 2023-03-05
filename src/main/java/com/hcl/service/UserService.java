package com.hcl.service;

import org.springframework.stereotype.Service;
import com.hcl.model.User;

@Service
public interface UserService {

	public String insertUserDataOfLogin(User user);

	public String checkUserDataOfLogin(String username, String password);

	public User searchDataById(long id);

	public String deleteDataById(long id);

	public String updateUserDataOfLogin(User user);

}
