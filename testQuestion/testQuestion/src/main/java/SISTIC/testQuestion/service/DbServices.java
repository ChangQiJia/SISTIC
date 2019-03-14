package SISTIC.testQuestion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import SISTIC.testQuestion.entity.Items;
import SISTIC.testQuestion.repo.ItemsRepo;

@Component
public class DbServices {
	
	@Autowired
	ItemsRepo itemsRepo; 
	
	//Inserting Single Record
	public void insertData (Items item) {
		
		System.out.println ("Saving item: " + item.toString());
		
		itemsRepo.save(item);
	}
	
	//Inserting Multiple Records
	public void insertMultiple (List<Items> items) {
		
		System.out.println ("Saving list of items");
		
		for (Items i: items) {
			System.out.println ("Saving " + i.toString());
		}
		
		itemsRepo.saveAll(items);
	}

	//Select information based on uniqueKey
	public Items searchData (String name) throws Exception {
		
		//Did not use try catch because it seems abit redundant to catch an exception to throw another new exception
		//Wanted to personalize the error message 
		
		/*Items item = null; 
		
		try {
			item = itemsRepo.findByProductName(name);
		}catch(Exception e) {
			System.out.println("ERROR: Unable to find record with name " + name);
			e.printStackTrace();
			throw new Exception ("ERROR: Unable to find record with name " + name);
		} */
		
		System.out.println ("Querying database for item with productName: " + name);
		
		Items item = itemsRepo.findByProductName(name);
		
		if (item == null){
			throw new Exception ("ERROR: Unable to find record with name " + name);
		}
		
		return item;	
	}
	
	//Select information based on id
	public Items searchDataById (int id) throws Exception {
		
		//Did not use try catch because it seems abit redundant to catch an exception to throw another new exception
		//Wanted to personalize the error message 
		
		/*Items item = null; 
		
		try {
			item = itemsRepo.findByProductName(name);
		}catch(Exception e) {
			System.out.println("ERROR: Unable to find record with name " + name);
			e.printStackTrace();
			throw new Exception ("ERROR: Unable to find record with name " + name);
		} */
		
		System.out.println ("Querying database for item with id: " + id);
		
		Items item = itemsRepo.findById(id);
		
		if (item == null){
			throw new Exception ("ERROR: Unable to find record with ID " + id);
		}
		
		return item;	
	}
	
	//Finding all records that has a quantity lesser than input
	public List<Items> searchDataLessThanQuantity (int quantity) throws Exception {
		
		System.out.println ("Querying database for item with quantity less than " + quantity);
		
		List <Items> items = itemsRepo.findLessThanQuantity(quantity);
		
		if (items.isEmpty()){
			throw new Exception ("ERROR: Unable to find any record that has a quantity lesser than " + quantity);
		}
		
		return items;	
	}
	
	//Finding all records that has a price lesser than input
	public List<Items> searchDataLessThanPrice (double price) throws Exception {
		
		System.out.println ("Querying database for item with price less than " + price);
		
		List <Items> items = itemsRepo.findLessThanPrice(price);
		
		if (items.isEmpty()){
			throw new Exception ("ERROR: Unable to find any record that has a quantity lesser than " + price);
		}
		
		return items;	
	}
	
	//Finding all records that has a price more than input
	public List<Items> searchDataMoreThanPrice (double price) throws Exception {

		System.out.println ("Querying database for item with price more than " + price);
		
		List <Items> items = itemsRepo.findMoreThanPrice(price);
		
		if (items.isEmpty()){
			throw new Exception ("ERROR: Unable to find any record that has a quantity lesser than " + price);
		}
		
		return items;	
	}
	
	public List<Items> searchHasStock () throws Exception {
		
		System.out.println ("Querying database for item with quantity more than 0");
		
		List <Items> items = itemsRepo.findHasStock();
		
		if (items.isEmpty()){
			throw new Exception ("ERROR: Your Store has no stock!");
		}
		
		return items;	
	}
	
	//Select all records in the store
	public List<Items> listAll () {
		
		System.out.println ("Listing all items in DB");
		
		return itemsRepo.findAll(); 
	}
	
	//Updating all fields of a record based on the id, if the field is not returned, the new record that is being updated will have the field as null
	public void updateData (Items item) throws Exception {
		
		Items foundItem; 
		
		System.out.println ("Querying database for item with id: " + item.getId());
		
		foundItem = itemsRepo.findById(item.getId());
		
		//This is done such that if record cannot be found, an error will be thrown, look at method updateCreate for the case where if record cannot be found a new record will be inserted instead.
		if (foundItem == null) {
			throw new Exception ("ERROR: Unable to find record with ID " + item.getId());
		}
		
		else {
			
			System.out.println ("Updating item");
		
			foundItem.setDescription(item.getDescription());
			foundItem.setPrice(item.getPrice());
			foundItem.setProductName(item.getProductName());
			foundItem.setQuantity(item.getQuantity());
			itemsRepo.save(foundItem);
		}
		
	}
	
	//Add a record's quantity this can be use to reduce the quantity as well but sending a negative number for the argument
	public Items addQuantity (int id, int quantity) throws Exception {
		
		Items foundItem; 
		
		System.out.println ("Querying database for item with id: " + id);
		
		foundItem = itemsRepo.findById(id);
		
		//This is done such that if record cannot be found, an error will be thrown, look at method updateCreate for the case where if record cannot be found a new record will be inserted instead.
		if (foundItem == null) {
			throw new Exception ("ERROR: Unable to find record with ID " + id);
		}
		
		else {
			
			System.out.println ("Updating the quantity");
			
			foundItem.setQuantity(foundItem.getQuantity() + quantity);
			return itemsRepo.save(foundItem);
		}
		
	}
	
	//Updating all fields of a record based on the id or create a new record if id cannot be found
	public void updateCreateData (Items item) {
		
		Items foundItem; 
		
		System.out.println ("Querying database for item with id: " + item.getId());
		
		foundItem = itemsRepo.findById(item.getId());
		
		//If record cannot be found, create a new record
		if (foundItem == null) {
			foundItem = new Items(); 
		}
		

		foundItem.setDescription(item.getDescription());
		foundItem.setPrice(item.getPrice());
		foundItem.setProductName(item.getProductName());
		foundItem.setQuantity(item.getQuantity());
		itemsRepo.save(foundItem);
		
	}
	
	//Removing a record based on the id
	public void deleteData(int id) throws Exception{
		
	
		//Did not use try catch because it seems abit redundant to catch an exception to throw another new exception
		//Wanted to personalize the error message 
		
		/*Items item = null; 
		
		try {
			item = itemsRepo.findById(id);
		}catch(Exception e) {
			System.out.println("ERROR: Unable to find record with ID " + id);
			e.printStackTrace();
			throw new Exception ("ERROR: Unable to find record with ID " + item.getId());
		}*/
		
		System.out.println ("Querying database for item with id: " + id);
		
		Items item = itemsRepo.findById(id);
		
		if (item == null){
			throw new Exception ("ERROR: Unable to find record with ID " + id);
		}
		
		System.out.println ("Deleting item");
		itemsRepo.delete(item);
		
	}
	
	public void deleteAllData() {
		
		System.out.println ("Deleting all items");
		
		itemsRepo.deleteAll();
	}
	
	
	
}
