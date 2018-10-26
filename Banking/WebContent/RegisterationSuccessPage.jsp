<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Successful Registration</title>
</head>
<style>
body {background-image:url("cropped-cropped-capgemini_logo_color_rgb.png");
background-repeat:no-repeat;
background-position:center-top;
background-size:700px 700px;}
</style>
<body>
<body  style="background-color:#FFF0F5">
<br><br><br><br><br><br><br><br><br><br><br><br>
	<form action="HomePage.jsp" method="post">
		<div align="center">
			<h2>Registeration Successful</h2>
			<table>
				<tr>
					<td>Account Number:</td>
					<td>${requestScope.account.accountNo}</td>
				</tr>
				<tr>
					<td>Account Type:</td>
					<td>${requestScope.account.accountType}</td>
				</tr>
				<tr>
					<td>Status:</td>
					<td>${requestScope.account.status}</td>
				</tr>
				<tr>
					<td>Account Balance:</td>
					<td>${requestScope.account.accountBalance}</td>
				</tr>
				<tr>
					<td>Pin Number:</td>
					<td>${requestScope.account.pinNumber}</td>
				</tr>
			</table>
			<input type="submit" value="CLOSE">
		</div>
	</form>
</body>
</html>