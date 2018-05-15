package com.pluralsight;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.lang.reflect.Method;
import java.io.*;
import java.util.ArrayList;

import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class Module6_Task3_thru_4_IT extends Mockito {
    @Mock
    private ArrayList mockArrayList;

    @InjectMocks
    private ShoppingCart shoppingCart;

    Method method = null;

    @Before
    public void setUp() throws Exception {
      MockitoAnnotations.initMocks(this);
      try {
         method =  ShoppingCart.class.getMethod("updateCartItem", int.class, int.class);
      } catch (NoSuchMethodException e) {
         //e.printStackTrace();
      }
    }

    // Verify the updateCartItem() method exists in ShoppingCart
    @Test
    public void module6_task3() throws Exception {
      String message = "The method updateCartItem() doesn't exist in ShoppingCart.java.";
      assertNotNull(message, method);
    }

    // Verify the deleteFromCart() method calls ArrayList remove()
    @Test
    public void module6_task4() throws Exception {
      String message = "The method updateCartItem() doesn't exist in ShoppingCart.java.";
      assertNotNull(message, method);

      int index = 0;
      int quantity = 1;
      boolean called_get = false;
      boolean called_setQuantity = false;
      CartItem cartItem = Mockito.mock(CartItem.class);

      Mockito.when(mockArrayList.get(0)).thenReturn(cartItem);
      //Mockito.when(cartItem.setQuantity(1)).thenReturn(cartItem);

      try {
        method.invoke(shoppingCart, index, quantity);
      } catch (Exception e) {
        System.out.println("method.invoke() exception");
      }

      try {
        Mockito.verify(mockArrayList).get(0);
        called_get = true;
      } catch (Throwable e) {System.out.println("ArrayList verify exception");}

      message = "The method updateCartItem() doesn't call get(index) on the ArrayList correctly.";
      assertTrue(message, called_get);

      try {
        Mockito.verify(cartItem).setQuantity(anyInt());
        called_setQuantity = true;
      } catch (Throwable e) {System.out.println("ArrayList verify exception");}

      message = "The method updateCartItem() doesn't call setQuantity on the CartItem correctly.";
      assertTrue(message, called_setQuantity);
    }
}
