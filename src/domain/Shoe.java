package domain;

import java.io.Serializable;

public class Shoe implements Serializable {
	
	private String name;
	
	private String shoeID;
	
	private String imgPath;
	
	private String brand;
	
	private double price;
	
	private double discount_price;
	
	private String status;

	public Shoe() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShoeID() {
		return shoeID;
	}

	public void setShoeID(String shoeID) {
		this.shoeID = shoeID;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Double getDiscount_price() {
		return discount_price;
	}

	public void setDiscount_price(double discount_price) {
		this.discount_price = discount_price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
