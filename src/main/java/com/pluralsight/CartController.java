package com.pluralsight;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CartController
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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		String action = request.getPathInfo();		
		try {
			switch(action) {
			case "/addcart":
				addToCart(request, response);
				break;
			case "/delete":
				deleteFromCart(request, response);
				break;
			default:
				response.sendRedirect("/ShoppingCart.jsp");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//response.sendRedirect("/ShoppingCart.jsp");
}

	private void addToCart(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session =request.getSession();
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		String quantityStr = request.getParameter("quantity");
		int quantity = Integer.parseInt(quantityStr);
		
		// Get book from the database
		Book existingBook = bookDAO.getBook(id);
		
		//Check if a ShoppingCart exists
		//If not create one
		ShoppingCart shoppingCart = null;
		Object objCartBean = session.getAttribute("cart");
		
		if(objCartBean != null) {
			shoppingCart = (ShoppingCart) objCartBean;
		}
		else {
			shoppingCart = new ShoppingCart();
			session.setAttribute("cart", shoppingCart);
		}
		
		shoppingCart.addCartItem(existingBook, quantity);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ShoppingCart.jsp");
		dispatcher.forward(request, response);

	}
	
	private void deleteFromCart(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session =request.getSession();
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		String quantityStr = request.getParameter("quantity");
		int quantity = Integer.parseInt(quantityStr);
		
		// Get book from the database
		Book existingBook = bookDAO.getBook(id);
		
		//Check if a ShoppingCart exists
		//If not create one
		ShoppingCart shoppingCart = null;
		Object objCartBean = session.getAttribute("cart");
		
		if(objCartBean != null) {
			shoppingCart = (ShoppingCart) objCartBean;
		}
		else {
			shoppingCart = new ShoppingCart();
			session.setAttribute("cart", shoppingCart);
		}
		
		shoppingCart.deleteCartItem(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ShoppingCart.jsp");
		dispatcher.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
