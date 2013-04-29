<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<title>Webbshop || Home</title>
</head>
<body>
	<h1 class="header">Webbshop || Create An Account</h1>
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
			</br>
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
						<td>Verify Password</td>
						<td></td>
					</tr>
					<tr>
						<td><form:password path="verifyPassword" /></td>
						<td><core:out value="${passwordVerifiedOK}" /></td>
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
						<td>Birthdate</td>
						<td></td>
					</tr>
					<tr>
						<td><form:input path="birthdate" /></td>
						<td><core:out value="${birthdateOK}" /></td>
					</tr>
					<tr>
						<td>Firstname</td>
						<td>Lastname</td>
					</tr>
					<tr>
						<td><form:input path="firstname" /></td>
						<td><form:input path="lastname" /></td>
					</tr>
					<tr>
						<td><core:out value="${firstnameOK}" /></td>
						<td><core:out value="${lastnameOK}" /></td>
					</tr>
					<tr>
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
						<td><input type="submit" value="Create" /></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
</html>