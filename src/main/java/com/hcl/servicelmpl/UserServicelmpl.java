package com.hcl.servicelmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.model.User;
import com.hcl.repository.UserRepository;
import com.hcl.service.UserService;

@Service
public class UserServicelmpl implements UserService {

	@Autowired
	private UserRepository userRepo;

//inserting a data into the user-table ...	
	public String insertUserDataOfLogin(User user) {

		int count = 0;
		for (User userData : userRepo.findAll()) {
			if (userData.getUserName().equalsIgnoreCase(user.getUserName())) {
				count++;
			}
		}

//checking data is empty or not 
		// before inserting checking data is already available into a user-table by
		// using count ....
		if (user.getUserName().isEmpty() || user.getPassword().isEmpty()) {
			return "Please insert all the data";
		} else if (count > 0) {
			return "User Already registered";
		} else {
			userRepo.save(user);
			return "User Registered Succcessfully";
		}
	}

//checking through user-data table and giving response ....
	public String checkUserDataOfLogin(String username, String password) {
		for (User userData : userRepo.findAll()) {
			if (userData.getUserName().equalsIgnoreCase(username) && userData.getPassword().equals(password)) {
				return "User Login SuccessFully";
			}
		}
		return "Invalid username or password";
	}

//searching a user by it user-id from user-table .....	
	public User searchDataById(long id) {
		User user = userRepo.findById(id).get();
		if (user.getId() > 0) {
			return user;
		}
		return null;
	}

//deleting a user from user-table by it user-id ....	
	public String deleteDataById(long id) {
		for (User userData : userRepo.findAll()) {
			if (userData.getId() == id) {
				userRepo.deleteById(id);
				return "User is Delete with id " + id + " successfully";
			}
		}
		return "Data not found";
	}

//updating a user from user-table by it user-id ...	
	public String updateUserDataOfLogin(User user) {

		int count = 0;
		int count_id = 0;
		for (User userData : userRepo.findAll()) {
			
			if (userData.getUserName().equalsIgnoreCase(user.getUserName())) {
				// if same user-name is found
				count++;
			} else if (userData.getId() == user.getId()) {
				// if id is not found
				count_id++;
			}
		}
		
// before updating checking data is already available into a user-table by using count ....
		if (count > 0) {
			return "User Already available ";
		} else if (count_id == 0) {
			return "Id is not found";
		} else {
			userRepo.save(user);
			return "User update Succcessfully";
		}
	}

}
