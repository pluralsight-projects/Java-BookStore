package com.pluralsight;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import com.pluralsight.*;
public class BookDAO {
    private Connection jdbcConnection;
    public BookDAO(Connection connection)
    {
      jdbcConnection = connection;
    }

    public Book getBook(int id) {
      Book book = null;
      String sql = "SELECT * FROM book WHERE id = ?";

      try {
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");

            book = new Book(id, title, author, price);
        }

        resultSet.close();
        statement.close();
      } catch (SQLException e) {
    	  System.err.println("Some proble"+e.getMessage());
          e.printStackTrace();
      }

      return book;
    }

    public ArrayList<Book> listAllBooks() {
      ArrayList<Book> listBook = new ArrayList<>();

      String sql = "SELECT * FROM book";

		  try {
			    Statement statement = jdbcConnection.createStatement();

	        ResultSet resultSet = statement.executeQuery(sql);

	        while (resultSet.next()) {
              int id = resultSet.getInt("id");
	            String title = resultSet.getString("title");
	            String author = resultSet.getString("author");
	            float price = resultSet.getFloat("price");

	            Book book = new Book(id, title, author, price);
	            listBook.add(book);
	        }

	        resultSet.close();
	        statement.close();
  		} catch (SQLException e) {
  			System.err.println("some problem"+e.getMessage());
  			e.printStackTrace();
  		}
        return listBook;
    }

    public boolean insertBook(Book book)  {
        String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";

        try {
	        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	        statement.setString(1, book.getTitle());
	        statement.setString(2, book.getAuthor());
	        statement.setFloat(3, book.getPrice());

	        boolean rowInserted = statement.executeUpdate() > 0;
	        statement.close();
	        return rowInserted;
        } catch (SQLException e) {
        	System.err.println("some problem"+e.getMessage());
        		e.printStackTrace();
        }

        return false;
    }
    public void deleteBook(int id) {
    	
    	String SQL = "DELETE FROM book WHERE id = ?";
    	try {
        			
    		PreparedStatement statement = jdbcConnection.prepareStatement(SQL);

		    statement.setInt(1, id);
			
			statement.executeUpdate();
			
			
			statement.close();
		}   catch (SQLException e) {
			System.err.println("some problem"+e.getMessage());
					// TODO Auto-generated catch block
			e.printStackTrace();
			}
    			
    }
}
















