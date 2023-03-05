package com.hcl.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


@Entity
@Table(name = "orders")
@Data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "item_name")
	private String itemName;

	@Column(name = "price")
	private long price;

	@Column(name = "number_of_plates")
	private long numberOfPlates;

	@Column(name = "user_address")
	private String userAddress;

	@Column(name = "order_bill")
	private long orderBill;

	@Column(name = "date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	public Order() {
		super();
	}

	public Order(long id, String itemName, long price, long numberOfPlates, String userAddress) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.price = price;
		this.numberOfPlates = numberOfPlates;
		this.userAddress = userAddress;
	}

	public Order(long id, String userName, String itemName, long price, long numberOfPlates, String userAddress,
			long orderBill, LocalDate date) {
		super();
		this.id = id;
		this.userName = userName;
		this.itemName = itemName;
		this.price = price;
		this.numberOfPlates = numberOfPlates;
		this.userAddress = userAddress;
		this.orderBill = orderBill;
		this.date = date;
	}

}
