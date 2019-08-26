package com.pluralsight.module1;
import com.pluralsight.*;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Method;

import java.io.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest({DriverManager.class, PreparedStatement.class, BookDAO.class})
public class Module1_Task6_IT {

    // Verify the deleteBook() method exists in BookDAO
    @Test
    public void _task6() throws Exception {
      Method method = null;
      String sql = "DELETE FROM book WHERE id = ?";
      Connection spyConnection = Mockito.mock(Connection.class);
      PreparedStatement mockStatement = Mockito.mock(PreparedStatement.class);
      BookDAO bookDAO = new BookDAO(spyConnection);
      BookDAO spyBookDAO = Mockito.spy(bookDAO);
      boolean called_setInt = false;
      boolean called_execute = false;
      boolean called_prepareStatement = false;
      boolean called_close = false;

      Mockito.when(spyConnection.prepareStatement(sql)).thenReturn(mockStatement);

      try {
         method =  BookDAO.class.getMethod("deleteBook", int.class);
      } catch (NoSuchMethodException e) {
         System.err.println(e.getMessage());
      }

      String message = "The method deleteBook() doesn't exist in BookDAO.java.";
      assertNotNull(message, method);

      try {
        method.invoke(spyBookDAO, 0);
      } catch (Exception e) {}

      try {
        Mockito.verify(mockStatement, Mockito.atLeast(1)).setInt(Mockito.anyInt(), Mockito.anyInt());
        called_setInt = true;
        Mockito.verify(mockStatement, Mockito.atLeast(1)).executeUpdate();
        called_execute = true;
        Mockito.verify(mockStatement, Mockito.atLeast(1)).close();
        called_close = true;
      } catch (Throwable e) {}

      message = "The method deleteBook() doesn't call setInt().";
      assertTrue(message, called_setInt);

      message = "The method deleteBook() doesn't call executeUpdate().";
      assertTrue(message, called_execute);

      message = "The method deleteBook() doesn't call PreparedStatement close().";
      assertTrue(message, called_close);
    }
}
