package com.hcl.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hcl.model.MyCard;
import com.hcl.model.Order;

@Service
public interface OrderService {

	public String orderFoodBySelectingMenuItem(Order orderData);

	public String finalBillOfTheOrderIs(String userName);

	public List<Order> finalOrderHistory(String userName);

	public List<Order> userOrderDetailByAdmin(String userName);

	public Set<String> generatingAllBillOfToday();

	public String totalSaleOfMonthByAdmin();

	public List<MyCard> getMyCardBeforeBill(String UserName);
}
