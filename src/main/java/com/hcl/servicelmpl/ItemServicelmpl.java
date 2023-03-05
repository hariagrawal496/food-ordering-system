package com.hcl.servicelmpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.model.Item;
import com.hcl.repository.ItemRepository;
import com.hcl.service.ItemService;

@Service
public class ItemServicelmpl implements ItemService {

	@Autowired
	private ItemRepository itemRepo;

// inserting a data into a item-table ...	
	public String insertItemInMenu(Item itemdata) {
		int count = 0;
		
// checking that our item data will not repeat into a same menu
		for (Item data : itemRepo.findAll()) {
			if (data.getItemName().equalsIgnoreCase(itemdata.getItemName())
					&& data.getMenu().equalsIgnoreCase(itemdata.getMenu())) {
				count++;
			}
		}

//checking our data is empty or not 
// also checking whether data is repeating or not ( if count = 1 same data is there)
		if (itemdata.getItemName().isEmpty() || itemdata.getPrice() < 0) {
			return "Please insert correct data";
		} else if (count > 0) {
			return "Item is Already available in menu";
		} else {
			itemRepo.save(itemdata);
			return "Item Added Successfully";
		}
	}

	
// searching a item data from the menu with using a item-name 
	public Item searchItemByItemName(String itemName, String menu) {
		for (Item data : itemRepo.findAll()) {
			if (data.getItemName().equalsIgnoreCase(itemName) && data.getMenu().equalsIgnoreCase(menu)) {
				return data;
			}
		}
		return null;
	}

//Deleting a data from item-table by selecting a item-name and menu	
	public String deleteItemDataByItemName(String itemName, String menu) {
		for (Item data : itemRepo.findAll()) {
			if (data.getItemName().equalsIgnoreCase(itemName)) {
				long id = data.getId();
				itemRepo.deleteById(id);
				return "Item is Delete with name " + itemName + " successfully";
			}
		}
		return "Item not found in Menu";
	}

//Updating a item-table table...	
	public String updateItemDataInMenu(Item item, String itemName) {
		
		System.out.println(item);
		for (Item data : itemRepo.findAll()) {
			if (data.getItemName().equalsIgnoreCase(itemName)) {				
				long id = data.getId();
				 item.setId(id);	
				 
				itemRepo.save(item);
				return "Item " + itemName+" update Successfully";
			}
		}
		return "Data is not update ";

	}

// find all item-data by selecting a menu (Breakfast/Lunch/Dinner)	
	public List<Item> findDataByMenu(String menu) {
		List<Item> list = new ArrayList<Item>();

		for (Item data : itemRepo.findAll()) {
			if (data.getMenu().equalsIgnoreCase(menu)) {
				list.add(data);
			}
		}
		return list;
	}

//Finding all a item data from item-table ...	
	public List<Item> searchAllItemData() {
		return itemRepo.findAll();
	}

}
