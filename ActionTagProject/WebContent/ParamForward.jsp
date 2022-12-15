<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String param1 = request.getParameter("param1");
	String param2 = request.getParameter("param2");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=param1%>
	<%=param2%>
</body>
</html>