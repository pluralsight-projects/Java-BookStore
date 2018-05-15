package com.pluralsight;

import java.util.ArrayList;
import javax.inject.Inject;

public class ShoppingCart {
 @Inject
 private ArrayList cartItems = new ArrayList();
 private double dblOrderTotal ;

 public int getLineItemCount() {
  return cartItems.size();
 }

 public void addCartItem(Book book, int quantity) {
   CartItem cartItem = new CartItem(book, quantity);
   cartItems.add(cartItem);
   calculateOrderTotal();
 }

 public void addCartItem(CartItem cartItem) {
  cartItems.add(cartItem);
 }

 // public void updateCartItem(int index, int quantity) {
 //   try {
 //     CartItem cartItem = (CartItem)cartItems.get(index);
 //     cartItem.setQuantity(quantity);
 //   } catch (Exception e) {
 //     e.printStackTrace();
 //   }
 // }
 //
 // public void deleteCartItem(int index) {
 //   try {
 //     cartItems.remove(index);
 //   } catch (Exception e) {}
 // }

 public CartItem getCartItem(int iItemIndex) {
  CartItem cartItem = null;
  if(cartItems.size()>iItemIndex) {
   cartItem = (CartItem) cartItems.get(iItemIndex);
  }
  return cartItem;
 }

 public ArrayList getCartItems() {
  return cartItems;
 }
 public void setCartItems(ArrayList cartItems) {
  this.cartItems = cartItems;
 }
 public double getOrderTotal() {
  return dblOrderTotal;
 }
 public void setOrderTotal(double dblOrderTotal) {
  this.dblOrderTotal = dblOrderTotal;
 }

 protected void calculateOrderTotal() {
  double dblTotal = 0;
  for(int counter=0;counter<cartItems.size();counter++) {
   CartItem cartItem = (CartItem) cartItems.get(counter);
   dblTotal+=cartItem.getTotalCost();

  }
  setOrderTotal(dblTotal);
 }

}
