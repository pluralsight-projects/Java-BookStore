package com.pluralsight;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.inject.Inject;
/**
 * Servlet implementation class HelloWorld
 */

public class CartController extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private DBConnection dbConnection;

		@Inject
    private BookDAO bookDAO;

    public void init() {
			dbConnection = new DBConnection();
			bookDAO = new BookDAO(dbConnection.getConnection());
    }

		public void destroy() {
			dbConnection.disconnect();
		}

    public CartController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		String action = request.getPathInfo();

		try {
			switch(action) {
				case "/addcart":
					 addToCart(request, response);
           break;
			 case "/update":
					 updateCart(request, response);
           break;
			 case "/delete":
				 	 //deleteFromCart(request, response);
           break;
        default:
           break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect("../ShoppingCart.jsp");
	}

  protected void addToCart(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
   HttpSession session = request.getSession();
   String idStr = request.getParameter("id");
   int id = Integer.parseInt(idStr);
   String quantityStr = request.getParameter("quantity");
   int quantity = Integer.parseInt(quantityStr);

   Book existingBook = bookDAO.getBook(id);

   ShoppingCart shoppingCart = null;
   Object objCartBean = session.getAttribute("cart");

   if(objCartBean!=null) {
    shoppingCart = (ShoppingCart) objCartBean ;
   } else {
    shoppingCart = new ShoppingCart();
    session.setAttribute("cart", shoppingCart);
   }

   shoppingCart.addCartItem(existingBook, quantity);
  }

	protected void updateCart(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	 HttpSession session = request.getSession();
   String indexStr = request.getParameter("index");
   int index = Integer.parseInt(indexStr);
   String quantityStr = request.getParameter("quantity");
   int quantity = Integer.parseInt(quantityStr);

   ShoppingCart shoppingCart = (ShoppingCart)session.getAttribute("cart");
   shoppingCart.updateCartItem(index, quantity);
  }

	 // protected void deleteFromCart(HttpServletRequest request, HttpServletResponse response)
	 // 	throws ServletException, IOException {
		//HttpSession session = request.getSession();
		//String indexStr = request.getParameter("index");
	  //int index = Integer.parseInt(indexStr);

		//ShoppingCart shoppingCart = (ShoppingCart)session.getAttribute("cart");
 		//shoppingCart.deleteCartItem(index);
	 // }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
