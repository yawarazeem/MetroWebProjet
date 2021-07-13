<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="./addBalance">
	Please enter your Card ID to check your balance :  <input type="number" name="cardId"><br><br>
	Please enter the amount to be added to your card:  <input type="number" name="bal"><br><br>
	<input type="submit" value="Check">
	
</form>
<br><br>
<span>Balance will be added only if the card is exists!</span>
<a href="./">Go to Main Page</a>
</body>
</html>