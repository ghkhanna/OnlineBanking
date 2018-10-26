<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Web-Banking</title>
</head>
<style>
body {background-image: url("Blurred-Background-Sunset-3c-Free.jpg");
background-repeat:no-repeat;
background-position:center;
height: 100%;
background-size: cover;
}
table {
    border-collapse: collapse;
    border: 1px solid black;
    width: 30%;
    height:50%;    
}
</style>
<body>
<h1 align="center">CG BANKING</h1>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<div align="center">
<h2></h2>
<form action="LoginServlet" method="post">
<table cellpadding="10">
<tr>
	<td><h4>Account Number</h4></td><td><input type="text" name="accountNo"></td>
</tr>
<tr>
	<td><h4>Pin Number</h4></td><td><input type="password" name="pinNumber"></td>
</tr>
</table>
<br>
<input type="submit" value="LOGIN">
</form>
<br>Or<br><br>
<form action="RegisterationPage.jsp" method="post">
<input type="submit" value="REGISTER">
</form><br>
		<font color="red"> ${errorMessage} </font>
	</div>
</body>
</html>