package com.hcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.model.Item;
import com.hcl.model.MyCard;
import com.hcl.model.Order;
import com.hcl.model.User;
import com.hcl.service.ItemService;
import com.hcl.service.OrderService;
import com.hcl.service.UserService;

@RestController

// creating a root api for a user-controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private OrderService orderService;

	
//user is doing a registration into the data of user table
	@PostMapping("/register")
	public String createUserRegisterData(@RequestBody User user) {

		User userData = new User();
		userData.setUserName(user.getUserName());
		userData.setPassword(user.getPassword());
		return userService.insertUserDataOfLogin(userData);
	}

//user is doing a login from the data of user table
	@PostMapping("/login")
	public String checkUserLoginData(@RequestBody User user) {
		String username = user.getUserName();
		String password = user.getPassword();

		if (username.isEmpty() || password.isEmpty()) {
			return "Invalid username or password";
		} else {
			return userService.checkUserDataOfLogin(username, password);
		}
	}

// user is doing logout
	@GetMapping("/logout")
	public String userDataLogout() {
		return "User Logout Successfully";
	}

//User is searching all data from the item table
	@GetMapping("/searchItemAllRecord")
	public List<Item> userSearchAllItemData() {
		return itemService.searchAllItemData();
	}

// user is doing a order by selecting the (item-name, user-name , qty ,address 	)
	@PostMapping("/orderByItemAndQty")
	public String getOrderByItemName(@RequestBody Order order) {
		Order orderData = new Order();

		orderData.setUserName(order.getUserName());
		orderData.setItemName(order.getItemName());
		orderData.setNumberOfPlates(order.getNumberOfPlates());
		orderData.setUserAddress(order.getUserAddress());

// checking whether inserting data is empty or not 		
		if (order.getUserName().isEmpty() || order.getItemName().isEmpty() || order.getNumberOfPlates() == 0) {
			return "Data is not proper please check";
		} else {
			return orderService.orderFoodBySelectingMenuItem(orderData);
		}
	}

//finding a bill of a user
	@GetMapping("/{userName}/finalBill")
	public String finalBillOfAUsernameIs(@PathVariable("userName") String userName) {
		return orderService.finalBillOfTheOrderIs(userName);
	}

//finding the my-card history before the bill
	@GetMapping("/myCard/{userName}")
	public List<MyCard> getAllDataBill(@PathVariable("userName") String userName) {
		return orderService.getMyCardBeforeBill(userName);
	}

//finding a order history after the bill
	@GetMapping("/{userName}/getHistoryOfOrder")
	public List<Order> userNameOrderHistory(@PathVariable("userName") String userName) {
		return orderService.finalOrderHistory(userName);
	}
	

}
