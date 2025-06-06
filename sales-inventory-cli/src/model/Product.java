package model;

public class Product {
	private int productId;
	private String productName;
	private double price;
	private int stockQuantity;
	
	// コンストラクタ、ゲッター、セッター
	public Product() {}
	
	public Product(int productId, String productName, double price, int stockQuantity) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}
	
	//ゲッター、セッター
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productid) {
		this.productId = productid;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getStockQuantity() {
		return stockQuantity;
	}
	
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

}
