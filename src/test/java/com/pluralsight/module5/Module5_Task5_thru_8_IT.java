package com.pluralsight;

import static org.junit.Assert.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.exceptions.*;

import org.powermock.reflect.Whitebox;
import java.lang.reflect.Method;

import java.io.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CartController.class)
public class Module5_Task5_thru_8_IT extends Mockito {

	private CartController cartController;
  Method method = null;
  boolean called_deleteFromCart = false;
  String errorMsg = "";
  HttpServletRequest request;
  HttpServletResponse response;
  HttpSession session;
  ShoppingCart shoppingCart;

    @Before
    public void setUp() throws Exception {
      try {
        method = Whitebox.getMethod(CartController.class,
                  "deleteFromCart", HttpServletRequest.class, HttpServletResponse.class);
      } catch (Exception e) {}

      errorMsg = "private void deleteFromCart() does not exist in CartController";
      assertNotNull(errorMsg, method);
      CartController cartController = PowerMockito.spy(new CartController());

      request = mock(HttpServletRequest.class);
      response = mock(HttpServletResponse.class);
      session = mock(HttpSession.class);
      shoppingCart = mock(ShoppingCart.class);

       try {
         when(request.getPathInfo()).thenReturn("/delete");
      //   //PowerMockito.doNothing().when(controllerServlet, "deleteBook", request, response);
         when(request.getSession()).thenReturn(session);
         when(request.getParameter("index")).thenReturn("0");
         when(session.getAttribute("cart")).thenReturn(shoppingCart);
       } catch (MethodNotFoundException e) {}

      try {
       cartController.doGet(request, response);
       try {
          PowerMockito.verifyPrivate(cartController)
                      .invoke("deleteFromCart", request, response);
          called_deleteFromCart = true;
       } catch (Throwable e) {}
      } catch (Exception e) {}
    }

    private void checkMethodExists() {
      errorMsg = "private void deleteFromCart() does not exist in CartController";
      assertNotNull(errorMsg, method);
      errorMsg = "After action \"" + "/delete" +
                        "\", did not call deleteFromCart().";
      assertTrue(errorMsg, called_deleteFromCart);
    }

    @Test
    public void module5_task5() throws Exception {
      checkMethodExists();

      boolean called_getSession = false;
      try {
         Mockito.verify(request).getSession();
         called_getSession = true;
      } catch (Throwable e) {}
      errorMsg = "Does not call request.getSession() in deleteFromCart().";
      assertTrue(errorMsg, called_getSession);
    }

    @Test
    public void module5_task6() throws Exception {
      checkMethodExists();

      boolean called_getParameter = false;
      try {
         Mockito.verify(request).getParameter("index");
         called_getParameter = true;
      } catch (Throwable e) {}
      errorMsg = "Does not call request.getParameter() in deleteFromCart().";
      assertTrue(errorMsg, called_getParameter);
    }

    @Test
    public void module5_task7() throws Exception {
      checkMethodExists();

      boolean called_getAttribute = false;
      try {
         Mockito.verify(session).getAttribute("cart");
         called_getAttribute= true;
      } catch (Throwable e) {}
      errorMsg = "Does not call session.getAttribute() in deleteFromCart().";
      assertTrue(errorMsg, called_getAttribute);
    }

    @Test
    public void module5_task8() throws Exception {
      checkMethodExists();

      boolean called_deleteCartItem = false;
      try {
         Mockito.verify(shoppingCart).deleteCartItem(0);
         called_deleteCartItem= true;
      } catch (Throwable e) {}
      errorMsg = "Does not call shoppingCart.deleteCartItem() in deleteFromCart().";
      assertTrue(errorMsg, called_deleteCartItem);
    }

}
