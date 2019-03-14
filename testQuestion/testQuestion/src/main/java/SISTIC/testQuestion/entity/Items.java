package SISTIC.testQuestion.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "ITEMS")
public class Items implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name ="id")
	private int id; 
	
	@Column(name ="productName", unique = true)
	private String productName; 

	@Column(name ="description")
	private String description; 
	
	@Column(name ="price")
	private double price; 

	@Column(name ="quantity")
	private int quantity; 
	
	public Items() {
		super(); 
	}
	
	public Items(String productName, String description, double price, int quantity) {
		super(); 
		this.productName = productName; 
		this.description = description; 
		this.price = price; 
		this.quantity = quantity; 
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Items [id=" + id + ", productName=" + productName + ", description=" + description + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}

	
	
}
