package com.hcl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hcl.model.Item;

@Service
public interface ItemService {

	public String insertItemInMenu(Item item);

	public Item searchItemByItemName(String itemName , String menu);

	public String deleteItemDataByItemName(String itemName,String menu);

	public String updateItemDataInMenu(Item item ,String itemName);

	public List<Item> findDataByMenu(String menu);

	public List<Item> searchAllItemData();

}
