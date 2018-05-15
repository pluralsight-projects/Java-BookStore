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

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import org.mockito.MockingDetails;
import org.mockito.invocation.Invocation;

import org.powermock.reflect.Whitebox;
import java.lang.reflect.Method;

import java.io.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CartController.class)
public class Module6_Task5_thru_9_IT extends Mockito {

	private CartController cartController;
  Method method = null;
  boolean called_updateCart = false;
  String errorMsg = "";
  HttpServletRequest request;
  HttpServletResponse response;
  HttpSession session;
  ShoppingCart shoppingCart;

    @Before
    public void setUp() throws Exception {
      try {
        method = Whitebox.getMethod(CartController.class,
                  "updateCart", HttpServletRequest.class, HttpServletResponse.class);
      } catch (Exception e) {}

      errorMsg = "private void updateCart() does not exist in CartController";
      assertNotNull(errorMsg, method);
      CartController cartController = PowerMockito.spy(new CartController());

      request = mock(HttpServletRequest.class);
      response = mock(HttpServletResponse.class);
      session = mock(HttpSession.class);
      shoppingCart = mock(ShoppingCart.class);

       try {
         when(request.getPathInfo()).thenReturn("/update");
      //   //PowerMockito.doNothing().when(controllerServlet, "deleteBook", request, response);
         when(request.getSession()).thenReturn(session);
         when(request.getParameter("index")).thenReturn("0");
         when(request.getParameter("quantity")).thenReturn("1");
         when(session.getAttribute("cart")).thenReturn(shoppingCart);
       } catch (MethodNotFoundException e) {}

      try {
       cartController.doGet(request, response);
       try {
          PowerMockito.verifyPrivate(cartController)
                      .invoke("updateCart", request, response);
          called_updateCart = true;
       } catch (Throwable e) {}
      } catch (Exception e) {}
    }

    private void checkMethodExists() {
      errorMsg = "private void updateCart() does not exist in CartController";
      assertNotNull(errorMsg, method);
      errorMsg = "After action \"" + "/delete" +
                        "\", did not call updateCart().";
      assertTrue(errorMsg, called_updateCart);
    }

    @Test
    public void module6_task5() throws Exception {
      checkMethodExists();

      boolean called_getSession = false;
      try {
         Mockito.verify(request).getSession();
         called_getSession = true;
      } catch (Throwable e) {}
      errorMsg = "Does not call request.getSession() in updateCart().";
      assertTrue(errorMsg, called_getSession);
    }

    @Test
    public void module6_task6() throws Exception {
      checkMethodExists();

      boolean called_getParameter = false;
      try {
         Mockito.verify(request).getParameter("index");
         called_getParameter = true;
      } catch (Throwable e) {}
      errorMsg = "Does not call request.getParameter(\"index\") in updateCart().";
      assertTrue(errorMsg, called_getParameter);
    }

    @Test
    public void module6_task7() throws Exception {
      checkMethodExists();

      boolean called_getParameter = false;
      try {
         Mockito.verify(request).getParameter("quantity");
         called_getParameter = true;
      } catch (Throwable e) {}
      errorMsg = "Does not call request.getParameter(\"quantity\") in updateCart().";
      assertTrue(errorMsg, called_getParameter);
    }

    @Test
    public void module6_task8() throws Exception {
      checkMethodExists();

      boolean called_getAttribute = false;
      try {
         Mockito.verify(session).getAttribute("cart");
         called_getAttribute= true;
      } catch (Throwable e) {}
      errorMsg = "Does not call session.getAttribute() in updateCart().";
      assertTrue(errorMsg, called_getAttribute);
    }

    // @Test
    // public void module5_task9() throws Exception {
    //   checkMethodExists();
    //
    //   boolean called_updateCartItem = false;
    //   try {
    //      Mockito.verify(shoppingCart).updateCartItem(0, 1);
    //      called_updateCartItem= true;
    //   } catch (Throwable e) {}
    //   errorMsg = "Does not call shoppingCart.updateCartItem() in updateCart().";
    //   assertTrue(errorMsg, called_updateCartItem);
    // }

    @Test
    public void module6_task9() throws Exception {
			 checkMethodExists();
			 errorMsg = "Does not call shoppingCart.updateCartItem() in updateCart().";

			 MockingDetails mockingDetails = Mockito.mockingDetails(shoppingCart);

			 Collection<Invocation> invocations = mockingDetails.getInvocations();

			 List<String> methodsCalled = new ArrayList<>();
			 for (Invocation anInvocation : invocations) {
			   methodsCalled.add(anInvocation.getMethod().getName());
			 }
			 assertTrue(errorMsg, methodsCalled.contains("updateCartItem"));
    }

}
