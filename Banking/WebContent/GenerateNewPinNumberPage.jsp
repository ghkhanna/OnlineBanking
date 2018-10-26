<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pin Change</title>
</head>
<style>
body {background-image:url("cropped-cropped-capgemini_logo_color_rgb.png");
background-repeat:no-repeat;
background-position:center-bottom;
background-size:700px 700px;}
</style>
<body style="background-color:#FFF0F5">
<br><br><br><br><br><br><br><br><br><br><br><br>
<div align="center">
<h1>Change your PIN</h1>
<form action="ChangePinNumberServlet" method="post">
<table border="2" bgcolor="lightblue">
<tr>
	<td><h4>Enter Old Pin:</h4></td>
	<td><input type="number" name="oldPinNumber"></td>	
</tr>
<tr>
	<td><h4>Enter New Pin:</h4></td>
	<td><input type="number" name="newPinNumber"></td>
</tr>
</table>
<input type="submit" value="SUBMIT">
</form>
<font color="red"><br>
	${IncorrectPin}
</font>
</div>
</body>
</html>