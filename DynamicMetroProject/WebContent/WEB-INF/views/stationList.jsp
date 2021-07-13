<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.metro.bean.Station"%>
<%@page import="java.util.Collection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Please Enter ID of Your SwipeIn station below:
	<% Collection<Station> allStations=(Collection<Station>)request.getAttribute("allStations");%>
	
	<ol>
	<%for(Station stn :allStations){ %>
	<li><%out.println(stn.getName()); %> </li>
	
	
	<% } %>
	
	</ol>
	
	


<form action="./stationId">
	Please enter your Card ID to check your balance :  <input type="number" name="stationId" placeholder="Please specify ID only"><br><br>

	<input type="submit" value="CheckIN">
	
</form>
<br><br>

<a href="./">Go to Main Page</a>
</body>
</html>