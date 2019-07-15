package com.pluralsight;

import java.util.ArrayList;

public class ShoppingCart {
	
	private ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
	private double orderTotal;
	
	public int getLineItemCount() {
		return cartItems.size();
	}
	
	public void addCartItem(Book book, int quantity) {
		CartItem cartItem = new CartItem(book, quantity);
		cartItems.add(cartItem);
	}
	
	public void deleteCartItem(int id)
	{
		cartItems.remove(id);
	}
	
	public void addCartItem(CartItem cartItem) {
		cartItems.add(cartItem);
	}
	
	public CartItem getCartItem(int itemIndex) {
		CartItem cartItem = null;
		if(cartItems.size()>itemIndex)
		{
			cartItem = cartItems.get(itemIndex);
		}
		return cartItem;
	}
	
	public ArrayList<CartItem> getCartItems() {
		return cartItems;
	}
	
	public void setCartItems(ArrayList<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	public double getOrderTotal() {
		return orderTotal;
	}
	
	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}
	
	public void calculateOrderTotal() {
		double total = 0;
		for (int counter=0; counter<cartItems.size(); counter++)
		{
			total += cartItems.get(counter).getTotalCost();
		}
		setOrderTotal(total);
	}
}
