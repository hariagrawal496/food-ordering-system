package com.hcl.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "items")
@Data
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	
	@Column(name = "item_name")
	private String itemName ;
	
	@Column(name = "menu")
	private String menu ;
	
	@Column(name = "price")
	private long price ;

	public Item(String itemName, int price) {
		super();
		this.itemName = itemName;
		this.price = price;
	}

	public Item(int id, String itemName, int price) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.price = price;
	}

	public Item() {
		super();
	}
	
	

}
