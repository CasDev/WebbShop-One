<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<title>Webbshop || Order</title>
</head>
<body>
	<h1 class="header">Webbshop || Shopping Cart</h1>
	<br>
	<br>
	<div id="menu">
		<a href="user-create">Create</a> an new user
		<core:out value="${productInbox}" />
		# of products is currently in your shopping cart. Go to <a
			href="goOrder">Shopping Cart</a>
	</div>
	<div class="main">
		<div class="category">
			<button value="category">LP</button>
			</br>
			<button value="category">CD</button>
			</br>
			<button value="category">DOWNLOAD</button>
			</br> <a href="products">
				<button value="products">All Products</button>
			</a> </br> <a href="">
				<button value="home">Home</button>
			</a>
		</div>
		<div class="products">
			<table border="1">
				<tr>
					<th>Product id:</th>
					<th>Product name:</th>
					<th>Product price:</th>
				</tr>
				<core:forEach items="${products}" var="product">
					<tr>
						<td><core:out value="${product.prodctId}" /></td>
						<td><core:out value="${product.artist} - ${product.title}" /></td>
						<td><core:out value="${product.price}" />:-</td>
					</tr>
				</core:forEach>

			</table>
		</div>
		<div class="login">
			<form:form action="login" modelAttribute="form" method="POST">
				<table>
					<tr>
						<th>Email:</th>
						<th>Password:</th>
					</tr>
					<tr>
						<td><form:input path="email" /></td>
						<td><form:password path="password" /></td>
					</tr>
					<tr>
						<td><core:out value="${emailOK}" /></td>
						<td></td>
					</tr>
					<tr>
						<td><core:out value="${passwordOK}" /></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Log in" /></td>
					<tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
</html>