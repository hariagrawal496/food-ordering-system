package com.hcl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mycard")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	
	@Column(name = "item_name")
	private String itemName ;
	
	@Column(name = "qty")
	private long qty ;
	
	@Column(name = "price")
	private long price ;
	
	@Column(name = "bill")
	private long bill ;
	
	@Column(name = "user_name")
	private String userName ;
	
	@Column(name = "address")
	private String address ;
		

}
