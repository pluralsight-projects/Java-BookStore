<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Book Store</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>


<body>
	<ul>
	  <li><a href="list">Book Listing</a></li>
    <li><a class="active" href="admin">Admin</a></li>
	</ul>

    <div class="container">
	    <div class="booktable">
	        <table border="1" cellpadding="5">
	            <caption>List of Books</caption>
	            <tr>
	                <th>Title</th>
	                <th>Author</th>
	                <th>Price</th>
                  <th><a href="new">Add Book</a></th>
	            </tr>

	 			      <c:forEach items="${books}" var="item">
	                <tr>
	                    <td> ${ item.getTitle() } </td>
	                    <td> ${ item.getAuthor() } </td>
	                    <td> <fmt:formatNumber value = "${ item.getPrice() }" type = "currency"/>  </td>
                      <td> <a href="edit?id=<c:out value='${item.getId()}' />">Edit</a>
                           <a href="delete?id=<c:out value='${item.getId()}' />">Delete</a> </td>
	                </tr>
	            </c:forEach>
	        </table>
	    </div>
    </div>
</body>
</html>
