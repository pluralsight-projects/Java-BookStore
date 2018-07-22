package com.pluralsight;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnection {
	static final String dbUrl = "jdbc:oracle:thin:@localhose1521:xe";
	static final String username = "system";
	static final String password = "admin";

	
	private Connection jdbcConnection;

  public DBConnection() {
    connect();
  }

  public Connection getConnection() {
    return jdbcConnection;
  }

  public void connect()  {
    try {
      Class.forName("org.sqlite.JDBC");
     // jdbcConnection = DriverManager.getConnection("jdbc:sqlite:book_store.db");
     
     jdbcConnection = DriverManager.getConnection(dbUrl,username,password); 
      System.out.println("Opened database successfully");

      createTableIfNotExists();
    } catch ( Exception e ) {
     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
     System.exit(0);
   }
 }

 private void createTableIfNotExists() {
   try {
       DatabaseMetaData meta = jdbcConnection.getMetaData();
       ResultSet res = meta.getTables(null, null, null, new String[] {"TABLE"});
       Statement stmt = jdbcConnection.createStatement();
       if (!res.next()) {
       	// Create table

           String sql = "CREATE TABLE book " +
                          "(id INTEGER PRIMARY KEY NOT NULL," +
                          " title TEXT NOT NULL, " +
                          " author TEXT NOT NULL, " +
                          " price REAL)";
           stmt.executeUpdate(sql);

           sql = "INSERT INTO book (title, author, price) VALUES (\"1984\", \"George Orwell\", 1.00)";
           stmt.executeUpdate(sql);

           stmt.close();
       }
    } catch ( Exception e ) {
       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
       System.exit(0);
    }
 }


  public void disconnect() {
    try {
    	if (jdbcConnection != null && !jdbcConnection.isClosed()) {
    	    jdbcConnection.close();
    	}
    } catch (SQLException e) {
    	e.printStackTrace();
    }
  }
}
