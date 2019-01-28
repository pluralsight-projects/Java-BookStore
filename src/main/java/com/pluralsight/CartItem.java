package com.pluralsight;

public class CartItem {
	
	private Book book;
	private int quantity;
	
	public CartItem(Book book, int quantity)
	{
		this.book = book;
		this.quantity = quantity;
	}
	
	public String getTitle() {
		return book.getTitle();
	}
	
	public String getAuthor() {
		return book.getAuthor();
	}
	
	public float getPrice() {
		return book.getPrice();
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public float getTotalCost() {
		return getPrice()*this.quantity;
	}
}
