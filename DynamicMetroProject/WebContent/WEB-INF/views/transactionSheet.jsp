<%@page import="com.metro.bean.Transaction"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>TRAVEL HISTORY:</h1>
	<% Collection<Transaction> card_Transactions=(Collection<Transaction>)request.getAttribute("card_Transactions");%>
	<table border="1">
		<tr>
			<th>CARD ID</th>
			<th>SOURCE_STATION</th>
			<th>DESTINATION_STATION</th>
			<th>CHARGES </th>
			<th> SWIPE_IN_TIME</th>
			<th>SWIPE_OUT_TIME</th>
			
		</tr>
		<%for(Transaction transaction:card_Transactions){ %>
		<tr>
			<td><%out.println(transaction.getCard_num()); %></td>
			<td><% out.println(transaction.getSource()); %></td>
			<td><% out.println(transaction.getDestination()); %></td>
			<td><% out.println(transaction.getFare()); %></td>
			<td><% out.println(transaction.getTs()); %></td>
			<td><% out.println(transaction.getTo()); %></td>
		</tr>
		<% } %>
	</table>
</body>
</html>