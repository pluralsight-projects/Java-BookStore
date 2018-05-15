package com.pluralsight;

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
public class Module1_Task5_IT {

    // Verify the deleteBook() in BookDAO calls prepareStatement()
    @Test
    public void module1_task5() throws Exception {
      Method method = null;
      String sql = "DELETE FROM book WHERE id = ?";
      Connection mockConnection = Mockito.mock(Connection.class);
      PreparedStatement mockStatement = Mockito.mock(PreparedStatement.class);
      BookDAO bookDAO = new BookDAO(mockConnection);
      BookDAO spyBookDAO = Mockito.spy(bookDAO);
      boolean called_prepareStatement = false;


      Mockito.when(mockConnection.prepareStatement(sql)).thenReturn(mockStatement);

      try {
         method =  BookDAO.class.getMethod("deleteBook", int.class);
      } catch (NoSuchMethodException e) {
         //e.printStackTrace();
      }

      String message = "The method deleteBook() doesn't exist in BookDAO.java.";
      assertNotNull(message, method);

      try {
        method.invoke(spyBookDAO, 0);
      } catch (Exception e) {}

      try {
        Mockito.verify(mockConnection,Mockito.atLeast(1)).prepareStatement(sql);
        called_prepareStatement = true;
      } catch (Throwable e) {}

      message = "The method deleteBook() doesn't call prepareStatement() correctly.";
      assertTrue(message, called_prepareStatement);
    }
}
