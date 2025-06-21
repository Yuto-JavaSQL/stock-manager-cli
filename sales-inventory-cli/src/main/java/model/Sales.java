package model;

import java.time.LocalDateTime;

public class Sales {
	
	private int saleId;
	private int productId;
	private int quantity;
	private LocalDateTime saleDate;
	
	//ゲッター、セッター
	public int getSaleId() {
		return saleId;
	}
	
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public LocalDateTime getSaleDate() {
		return saleDate;
	}
	
	public void setSaleDate(LocalDateTime saleDate) {
		this.saleDate = saleDate;
	}

}
