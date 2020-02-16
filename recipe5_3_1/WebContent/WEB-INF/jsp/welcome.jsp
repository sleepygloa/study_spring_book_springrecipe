<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ tablib prefix = fmt" uri= "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome</title>
</head>
<body>
	<h2>Welcome to Court Reservation System</h2>
	Today is <fmt:formatDate value="${today}" pattern="yyyy-MM-dd" />
</body>
</html>