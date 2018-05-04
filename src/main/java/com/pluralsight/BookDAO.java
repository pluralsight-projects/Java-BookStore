package com.pluralsight;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.Statement;

public class BookDAO {
    private Connection jdbcConnection;
    public BookDAO(Connection connection)
    {
      jdbcConnection = connection;
    }

    public void connect()  {
    	//try {
            //System.out.println("Opened database successfully");

         //    DatabaseMetaData meta = jdbcConnection.getMetaData();
         //    ResultSet res = meta.getTables(null, null, null, new String[] {"TABLE"});
         //    Statement stmt = jdbcConnection.createStatement();
         //    if (!res.next()) {
         //    	// Create table
         //
         //        String sql = "CREATE TABLE book " +
         //                       "(id INTEGER PRIMARY KEY NOT NULL," +
         //                       " title TEXT NOT NULL, " +
         //                       " author TEXT NOT NULL, " +
         //                       " price REAL)";
         //        stmt.executeUpdate(sql);
         //
         //        sql = "INSERT INTO book (title, author, price) VALUES (\"1984\", \"George Orwell\", 1.00)";
         //        stmt.executeUpdate(sql);
         //
         //        stmt.close();
         //        //jdbcConnection.close();
         //    }
         // } catch ( Exception e ) {
         //    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         //    System.exit(0);
         // }
    }

    public void disconnect() {
    //     try {
		// 	if (jdbcConnection != null && !jdbcConnection.isClosed()) {
		// 	    jdbcConnection.close();
		// 	}
		// } catch (SQLException e) {
		// 	e.printStackTrace();
		// }
    }

    public Book getBook(int id) {
      Book book = null;
      String sql = "SELECT * FROM book WHERE id = ?";
      connect();

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
          e.printStackTrace();
      }

      return book;
    }

    public ArrayList<Book> listAllBooks() {
        ArrayList<Book> listBook = new ArrayList<>();

        String sql = "SELECT * FROM book";

        connect();

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
			e.printStackTrace();
		}

        disconnect();

        return listBook;
    }

    public boolean insertBook(Book book)  {
        String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";
        connect();

        try {
	        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	        statement.setString(1, book.getTitle());
	        statement.setString(2, book.getAuthor());
	        statement.setFloat(3, book.getPrice());

	        boolean rowInserted = statement.executeUpdate() > 0;
	        statement.close();
	        disconnect();
	        return rowInserted;
        } catch (SQLException e) {
        		e.printStackTrace();
        }

        return false;

    }

    public void deleteBook(int id) {
      String sql = "DELETE FROM book WHERE id = ?";
      //connect();

      try {
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
        //
         statement.executeUpdate();

        statement.close();
      } catch (SQLException e) {
          e.printStackTrace();
      }
    }
}
