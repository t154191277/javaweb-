package domain;

import java.io.Serializable;

public class Img implements Serializable {
	
	private String productID;
	
	private String imgPath; 

	public Img() {
		
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	
	
}
