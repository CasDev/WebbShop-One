<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<title>Webbshop || Update Account (<core:out
		value="${sessionScope.user.firstname} ${sessionScope.user.lastname}" />)
</title>
</head>
<body>
	<h1 class="header">Webbshop || Update Account</h1>
	<br>
	<br>
	<div id="menu">
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
		<div class="create">
			<form:form modelAttribute="form" method="POST">
				<table>
					<tr>
						<td>Password</td>
						<td></td>
					</tr>
					<tr>
						<td><form:password path="password" /></td>
						<td><core:out value="${passwordOK}" /></td>
					</tr>
					<tr>
						<td>E-mail</td>
						<td></td>
					</tr>
					<tr>
						<td><form:input path="email" /></td>
						<td><core:out value="${emailOK}" /></td>
					</tr>
					<tr>
						<td>Lastname</td>
					</tr>
					<tr>
						<td><form:input path="lastname" /></td>
						<td></td>
					</tr>
					<tr>
						<td><core:out value="${lastnameOK}" /></td>
						<td></td>
					</tr>
					<tr>
						<td>Country:</td>
						<td></td>
					</tr>
					<tr>
						<td><form:input path="country" /></td>
						<td><core:out value="${countryOK}" /></td>
					</tr>
					<tr>
						<td>City:</td>
						<td></td>
					</tr>
					<tr>
						<td><form:input path="city" /></td>
						<td><core:out value="${cityOK}" /></td>
					</tr>
					<tr>
						<td>Street:</td>
						<td></td>
					</tr>
					<tr>
						<td><form:input path="street" /></td>
						<td><core:out value="${streetOK}" /></td>
					</tr>
					<tr>
						<td>Zip:</td>
						<td></td>
					</tr>
					<tr>
						<td><form:input path="zip" /></td>
						<td><core:out value="${zipOK}" /></td>
					</tr>
					<tr>
						<td>Area</td>
						<td></td>
					</tr>
					<tr>
						<td><form:input path="area" /></td>
						<td><core:out value="${areaOK}" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Update" /></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
</html>