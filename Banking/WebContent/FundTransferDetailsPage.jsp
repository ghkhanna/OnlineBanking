<%@page session="false" %>
<%@page import="com.cg.banking.beans.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fund Transfer</title>
</head>
<style>
body {background-image:url("cropped-cropped-capgemini_logo_color_rgb.png");
background-repeat:no-repeat;
background-position:center-bottom;
background-size:700px 700px;}
</style>
<body style="background-color:#FFF0F5">
<div align="center">
<br><br><br><br><br><br><br><br><br><br><br><br>
<h1>Enter fund transfer details</h1>
<form action="FundTransferServlet" method="post">
	<table border="2" bgcolor="lightblue">
	<tr>
		<td><h4>Transfer to Account (Account Number):</h4></td>
		<td><input type="number" name="transferAccountNumber"></td>
	</tr>
	<tr>
		<td><h4>Transfer Amount(in Rs):</h4></td>
		<td><input type="number" name="transferAmount"></td>
	</tr>
	</table><br>
	<input type="submit" value="SUBMIT">
</form><br>
<font color="red">
	${transferErrorMessage}
</font>
</div>
</body>
</html>