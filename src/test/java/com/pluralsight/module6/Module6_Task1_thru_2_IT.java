package com.pluralsight;

import static org.junit.Assert.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class Module6_Task1_thru_2_IT extends Mockito {

	private CartController cartController;
  Method method = null;

    @Before
    public void setUp() throws Exception {
      try {
        method = Whitebox.getMethod(CartController.class,
                  "updateCart", HttpServletRequest.class, HttpServletResponse.class);
      } catch (Exception e) {}
    }

		// Verify the updateCart() method exists in CartController
    @Test
    public void module6_task1() throws Exception {
      String errorMsg = "private void updateCart() does not exist in CartController";
      assertNotNull(errorMsg, method);
    }

    @Test
    public void module6_task2() throws Exception {
      String errorMsg = "private void updateCart() does not exist in CartController";
      assertNotNull(errorMsg, method);
      CartController cartController = PowerMockito.spy(new CartController());
      boolean called_updateCart = false;
      HttpServletRequest request = mock(HttpServletRequest.class);
      HttpServletResponse response = mock(HttpServletResponse.class);

       try {
         when(request.getPathInfo()).thenReturn("/update");
      //   //PowerMockito.doNothing().when(controllerServlet, "deleteBook", request, response);
      //   when(request.getParameter("id")).thenReturn(tempID);
       } catch (MethodNotFoundException e) {}

      try {
       cartController.doGet(request, response);
       try {
          PowerMockito.verifyPrivate(cartController)
                      .invoke("updateCart", request, response);
          called_updateCart = true;
       } catch (Throwable e) {}
      } catch (Exception e) {}

      errorMsg = "After action \"" + "/update" +
                        "\", did not call updateCart().";
      assertTrue(errorMsg, called_updateCart);
    }


}
