<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<title>Webbshop || Home</title>
</head>
<body>
	<h1 class="header">Webbshop || Home</h1>
	<br>
	<br>
	<div id="menu">
		<a href="user-update">Update</a> your profile
		<core:out value="${productInbox}" />
		# of products is currently in your shopping cart.
	</div>
	<div class="main">
		<div class="category">
			<button value="category">LP</button>
			</br>
			<button value="category">CD</button>
			</br>
			<button value="category">DOWNLOAD</button>
			</br>
		</div>
		<div class="products">
			<table border="1">
				<tr>
					<th>Product id:</th>
					<th>Product name:</th>
					<th>Product price:</th>
				</tr>
				<core:forEach items="${products}" begin="0" end="4" var="product">
					<tr>
						<td><core:out value="${product.prodctId}" /></td>
						<td><core:out value="${product.artist} - ${product.title}" /></td>
						<td><core:out value="${product.price}" />:-</td>
					</tr>
				</core:forEach>

			</table>
		</div>
		<div class="login">
			<table>
				<tr>
					<th>Email:</th>
					<th>Name:</th>
				</tr>
				<tr>
					<td><core:out value="${sessionScope.user.email}" /></td>
					<td><core:out value="${sessionScope.user.firstname}" /> <core:out
							value="${sessionScope.user.lastname}" /></td>
				</tr>
				<tr>
					<td></td>
					<td><a href="signOut">Sign out</a></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>