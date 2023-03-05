package com.hcl.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.model.Admin;
import com.hcl.model.Item;
import com.hcl.model.Order;
import com.hcl.model.User;
import com.hcl.service.AdminService;
import com.hcl.service.ItemService;
import com.hcl.service.OrderService;
import com.hcl.service.UserService;

@RestController

//creating a root api for a admin-controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private OrderService orderService;

//Admin is doing a login and check the data that is storing a back-end..
	@PostMapping("/login")
	public String adminLoginData(@RequestBody Admin admin) {

		String name = admin.getAdminName();
		String pass = admin.getPassword();
		
		//if data is empty then show a invalid message ....
		if (name.isEmpty() || pass.isEmpty()) {
			return "Invalid username or password";
		} else {
			return adminService.checkAdminDataOfLogin(name, pass);
		}
	}

//Admin is doing a logout action here
	@GetMapping("/logout")
	public String adminDataLogout() {
		return "Admin Logout Successfully";
	}

	
// Admin is doing a CRUD operation on user
	
	//inserting a data into a user table
	@PostMapping("/userAdd")
	public String createUserRegisterData(@RequestBody User user) {

		User userData = new User();
		userData.setUserName(user.getUserName());
		userData.setPassword(user.getPassword());
		return userService.insertUserDataOfLogin(userData);
	}

//searching a data from the user table by unique id 
	@GetMapping("/userSearch/{id}")
	public User searchUserData(@PathVariable("id") long id) {
		User user = userService.searchDataById(id);
		return user;
	}

	
//deleting a data from the user table by a id	
	@GetMapping("/userDelete/{id}")
	public String deleteUserData(@PathVariable("id") long id) {
		String data = userService.deleteDataById(id);
		return data;
	}

//updating a user data into a user table or changing a data By id...	
	@PostMapping("/userUpdate/{id}")
	public String updateUserRegisterData(@RequestBody User user, @PathVariable("id") long id) {

		User userData = new User();
		userData.setId(id);
		userData.setUserName(user.getUserName());
		userData.setPassword(user.getPassword());

		if (user.getUserName().isEmpty() || user.getPassword().isEmpty()) {
			return "Invalid username or password";
		} else {
			return userService.updateUserDataOfLogin(userData);
		}

	}


//Searching a data by using a menu name (Lunch /Dinner/Breakfast) ...	
	@GetMapping("/{menu}")
	public List<Item> findItemMenu(@PathVariable("menu") String menu) {
		return itemService.findDataByMenu(menu);
	}

//Inserting a item in item-table by selecting a specific menu ..	
	@PostMapping("/{menu}/itemAdd")
	public String createUserRegisterData(@PathVariable("menu") String menu, @RequestBody Item item) {

		Item itemData = new Item();
		itemData.setMenu(menu);
		itemData.setItemName(item.getItemName());
		itemData.setPrice(item.getPrice());
		return itemService.insertItemInMenu(itemData);
	}

//Searching a item-data by selecting a menu (Lunch /Dinner/Breakfast) and item-name ....	
	@GetMapping("/{menu}/itemSearch/{itemName}")
	public Item searchItemDataById(@PathVariable("menu") String menu, @PathVariable("itemName") String itemName) {
		return itemService.searchItemByItemName(itemName, menu);
	}

// Deleting a data from the item-table by selecting  menu and item-name
	@GetMapping("/{menu}/itemDelete/{itemName}")
	public String deleteItemDataById(@PathVariable("menu") String menu, @PathVariable("itemName") String itemName) {
		return itemService.deleteItemDataByItemName(itemName, menu);
	}

//Updating a item data with in a item-table by selecting menu and item-name	...
	@PostMapping("/{menu}/itemUpdate/{itemName}")
	public String updateItemData(@PathVariable("menu") String menu, @RequestBody Item item,
			@PathVariable("itemName") String itemName) {

		Item userData = new Item();
		userData.setMenu(menu);
		userData.setItemName(item.getItemName());
		userData.setPrice(item.getPrice());
		return itemService.updateItemDataInMenu(userData, itemName);
	}

// As an admin we are generating all current date bill with there user-name ....
	@GetMapping("/generatingTodaysAllBillByAdmin")
	public Set<String> allBillGeneratedTodayis() {
		return orderService.generatingAllBillOfToday();
	}

//as an admin I am searching all order of a specific user ....
	@GetMapping("/orderDoneBySpecificUser/{userName}")
	public List<Order> userAllOrder(@PathVariable("userName") String UserName) {
		return orderService.userOrderDetailByAdmin(UserName);
	}

//as a admin I am finding the total sale(turnover) from the starting ....	
	@GetMapping("/totalSaleByAdmin")
	public String totalSaleOfMonthByAdmin() {
		return orderService.totalSaleOfMonthByAdmin();
	}
}
