package main;

public class Product {

	private Integer productID;
	private String productName;
	private Integer productStock;
	private Integer productTypeID;
	private Integer productPrice;
	
	public Product(Integer productID, String productName, Integer productStock, Integer productTypeID,
			Integer productPrice) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.productStock = productStock;
		this.productTypeID = productTypeID;
		this.productPrice = productPrice;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductStock() {
		return productStock;
	}

	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

	public Integer getProductTypeID() {
		return productTypeID;
	}

	public void setProductTypeID(Integer productTypeID) {
		this.productTypeID = productTypeID;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	
	
	
	
	
	
}
