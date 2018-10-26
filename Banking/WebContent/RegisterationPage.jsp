<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome...Register Here</title>
</head>
<style>
body {background-image:url("cropped-cropped-capgemini_logo_color_rgb.png");
background-repeat:no-repeat;
background-position:center-bottom;
background-size:700px 700px;}
</style>
<body style="background-color:#FFF0F5">
<h1 align="center">New Registration</h1>
<br><br><br><br><br><br><br><br><br><br><br><br>
	<div align="center">
		<form action="RegisterationServlet" method="post">
			<table border="2" bgcolor="lightblue">
				<tr>
					<td><h4>Account type:</h4></td>
					<td><input type="radio" name="accountType" value="Savings" required>Savings
							<input type="radio" name="accountType" value="Current">Current
							<input type="radio" name="accountType" value="Salary">Salary
						</td>
					<!-- <td><input type="text" name="accountType"></td> -->
				</tr>
				<td><h4>Deposit Initial Amount:</h4></td>
				<td><input type="number" name="initialAccountBalance"></td>
				</tr>
			</table>
			<br>
			<input type="submit" value="REGISTER">
		</form>
		<font color="red">${invalidAmount}</font>
	</div>
</body>
</html>