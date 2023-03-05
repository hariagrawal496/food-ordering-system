package com.hcl.servicelmpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.model.Item;
import com.hcl.model.MyCard;
import com.hcl.model.Order;
import com.hcl.model.OrderFinalRecords;
import com.hcl.model.User;
import com.hcl.repository.ItemRepository;
import com.hcl.repository.MyCardRepository;
import com.hcl.repository.OrderFinalRecordRepository;
import com.hcl.repository.OrderRepository;
import com.hcl.repository.UserRepository;
import com.hcl.service.OrderService;

@Service
public class OrderServicelmpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MyCardRepository myCardRepository;

	@Autowired
	private OrderFinalRecordRepository orderFinalRecordRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public String orderFoodBySelectingMenuItem(Order orderData) {
		
		String message = null;
		long price = 0;
		
		for (User user : userRepository.findAll()) {
			if (user.getUserName().equalsIgnoreCase(orderData.getUserName())) {
			
	// insert data into my card from here only ....
				MyCard cardData = new MyCard();
				cardData.setQty(orderData.getNumberOfPlates());
				cardData.setAddress(orderData.getUserAddress());
				cardData.setUserName(orderData.getUserName());

				String item = orderData.getItemName();
				cardData.setItemName(item);

				
	//checking whether item is present or not
				for (Item itemData : itemRepository.findAll()) {
					if (itemData.getItemName().equalsIgnoreCase(item)) {
						price = itemData.getPrice();
					}
				}
				cardData.setPrice(price);

				long bill = price * orderData.getNumberOfPlates();
				cardData.setBill(bill);

	//if item not present in item list then it will show price =0 
				if (price == 0) {
					message = "Item not found in menu";
				} 
				else
				{					
	//it will going to save the data
					myCardRepository.save(cardData);
					message = "Order is successfully added in list do you want more order";
				}
				
				return message ;
			} 
			
		}
		message = "check whether your username is correct or not";
		return message;
	}

	@Override
	public String finalBillOfTheOrderIs(String userName) {

		LocalDate date = LocalDate.now();

	// Transferring our data from MyCard table to order table
		for (MyCard cardData : myCardRepository.findAll()) {
			if (cardData.getUserName().equalsIgnoreCase(userName)) {

				Order orderData = new Order();
				orderData.setDate(date);
				orderData.setItemName(cardData.getItemName());
				orderData.setNumberOfPlates(cardData.getQty());
				orderData.setOrderBill(cardData.getBill());
				orderData.setPrice(cardData.getPrice());
				orderData.setUserAddress(cardData.getAddress());
				orderData.setUserName(userName);

				orderRepository.save(orderData);
			}
		}

	// generating total payment of a user ...
		long totalBillValue = 0;
		for (Order orderData : orderRepository.findAll()) {
			if (orderData.getUserName().equalsIgnoreCase(userName) && orderData.getDate().equals(date)) {
				totalBillValue = totalBillValue + orderData.getOrderBill();
			}
		}

	// saving record with every-user final-bill
		OrderFinalRecords orderFinalRecord = new OrderFinalRecords();
		orderFinalRecord.setDate(date);
		orderFinalRecord.setTotalBill(totalBillValue);
		orderFinalRecord.setUserName(userName);

		orderFinalRecordRepository.save(orderFinalRecord);

	// deleting the data from the MyCard table after bill generating
		for (MyCard cardData : myCardRepository.findAll()) {
			if (cardData.getUserName().equalsIgnoreCase(userName)) {
				long id = cardData.getId();
				myCardRepository.deleteById(id);
			}
		}
		return " Total order bill of " + userName + " is :" + totalBillValue;
	}

	@Override
	public List<Order> finalOrderHistory(String userName) {
		
		LocalDate date = LocalDate.now() ;
		List<Order> list = new ArrayList<Order>();
		
	//checking if user-name is present in order table or not
		for (Order order : orderRepository.findAll()) {
			if (order.getUserName().equalsIgnoreCase(userName) && order.getDate().equals(date)) {
				list.add(order);
			}
		}
		return list;
	}

	@Override
	public List<Order> userOrderDetailByAdmin(String userName) {
		List<Order> list = new ArrayList<Order>();
		
	// admin is checking from it portal of user data
		for (Order order : orderRepository.findAll()) {
			if (order.getUserName().equalsIgnoreCase(userName)) {
				list.add(order);
			}
		}
		return list;
	}

	@Override
	public Set<String> generatingAllBillOfToday() {
		LocalDate date = LocalDate.now();
		Set<String> list = new HashSet<String>();

	//generating final user-name of today with its total bill
		for (OrderFinalRecords finalorder : orderFinalRecordRepository.findAll()) {
			if (finalorder.getDate().equals(date)) {
				list.add(" Todays bill of " + finalorder.getUserName() + " is : " + finalorder.getTotalBill());
			}
		}
		return list;
	}

	@Override
	public String totalSaleOfMonthByAdmin() {
		long totalsale = 0;
		
	// finding total sale from the starting
		for (Order order : orderRepository.findAll()) {
			totalsale = totalsale + order.getOrderBill();
		}
		return "Total Sale from the starting of a hotel is : " + totalsale;
	}

	@Override
	public List<MyCard> getMyCardBeforeBill(String userName) {
		List<MyCard> list = new ArrayList<MyCard>();
		
	//adding my data into the my card before the bill pay
		for (MyCard card : myCardRepository.findAll()) {
			if (card.getUserName().equalsIgnoreCase(userName)) {
				list.add(card);
			}
		}
		return list;
	}

}
