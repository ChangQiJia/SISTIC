package SISTIC.testQuestion.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SISTIC.testQuestion.entity.Items;
import SISTIC.testQuestion.repo.ItemsRepo;
import SISTIC.testQuestion.service.DbServices;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class DbController {
	
	@Autowired
	DataSource dataSource; 
	
	@Autowired
	DbServices service; 
	
	@Autowired
	ItemsRepo itemsRepo; 
	
	//Insert single record
	@PostMapping (value = "/items")
	public void insertData (@RequestBody Items item) {
		
		System.out.println ("Recieve request to creating single item");
		
		service.insertData(item);
	}
	
	//Insert multiple record
	@PostMapping (value = "/items/multiple")
	public void insertData (@RequestBody List<Items> items) {
		
		System.out.println ("Recieve request to creating multiple item");
		
		service.insertMultiple(items);
	}
	
	//Reading 1 record based on unique key
	@RequestMapping (value = "/items/productName")
	public Items searchData(@RequestParam("productName") String name) throws Exception {
		
		System.out.println ("Recieve request to find single item based on productName");
		
		return service.searchData(name);
	}
	
	//Reading 1 record based on id (Sequence)
	@RequestMapping (value = "/items/id")
	public Items searchDataById(@RequestParam("id") int id) throws Exception {
		
		System.out.println ("Recieve request to find single item based on id");
		
		return service.searchDataById(id);
	}
	
	//Finding all records that has a quantity lesser than input
	@RequestMapping (value = "/items/lessThanQuantity")
	public List <Items> searchDataLessThanQuantity(@RequestParam("quantity") int quantity) throws Exception {
		
		System.out.println ("Recieve request to find list of items that has lesser than input quantity");
		
		return service.searchDataLessThanQuantity(quantity);
	}
	
	//Finding all records that has a price lesser than input
	@RequestMapping (value = "/items/lessThanPrice")
	public List <Items> searchDataLessThanPrice(@RequestParam("price") double price) throws Exception {
		
		System.out.println ("Recieve request to find list of items that has lesser than input price");
		
		return service.searchDataLessThanPrice(price);
	}
	
	//Finding all records that has a price more than input
	@RequestMapping (value = "/items/moreThanPrice")
	public List <Items> searchDataMoreThanPrice(@RequestParam("price") double price) throws Exception {
		
		System.out.println ("Recieve request to find list of items that has more than input quantity");
		
		return service.searchDataMoreThanPrice(price);
	}
	
	//Finding all records that has stock (quantity > 0)
	@RequestMapping (value = "/items/hasStock")
	public List <Items> searchHasStock() throws Exception {
		
		System.out.println ("Recieve request to find list of items that has quantity > 0");
		
		return service.searchHasStock();
	}
	
	//Listing all records
	@RequestMapping (value = "/items")
	public List<Items> listAll () {
		
		System.out.println ("Recieve request to list all items");
		
		return service.listAll(); 
	}
	
	//Updating a single record
	@PostMapping (value = "/items/update")
	public void updateData (@RequestBody Items item) throws Exception {
		
		System.out.println ("Recieve request to update single item");
		
		service.updateData(item);
	}
	
	//Updating a single record
	@RequestMapping (value = "/items/updateQuantity")
	public Items updateQuantity (@RequestParam("id") int id, @RequestParam("quantity") int quantity) throws Exception {
		
		System.out.println ("Recieve request to update quantity of a single item");
		
		return service.addQuantity(id, quantity);
	}
	
	//Update or create a single record
	@PostMapping (value = "/items/updateCreate")
	public void updateCreateData (@RequestBody Items item) {
		
		System.out.println ("Recieve request to update single item, if item not found, it will be created instead");
		
		service.updateCreateData(item);
	}
	
	//Delete a single record based on the id
	@DeleteMapping (value = "/items/id")
	public void deleteData(@RequestParam("id") int id) throws Exception {
		
		System.out.println ("Recieve request to delete single item");
		
		service.deleteData(id);
	}
	
	//Deleting all records in the db
	@DeleteMapping (value = "/items")
	public void deleteAllData(){
		
		System.out.println ("Recieve request to delete all items");
		
		service.deleteAllData();
	}
	
}
