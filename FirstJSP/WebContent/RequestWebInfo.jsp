<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8"); //한글 깨짐 방지
	String eng = request.getParameter("eng"); // 파라메터로 넘어오는 데이터는 모두 문자열이다
	String han = request.getParameter("han"); // 그래서 String 타입의 변수에 담아준다
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>1.클라이언트와 서버의 환경정보 읽기</h2>
	<ul>
		<li><%=eng %></li>
		<li><%=han %></li>	
		<li><%=request.getRequestURL() %></li> 
		<li><%=request.getRequestURI() %></li>
		<li><%=request.getRealPath("/") %></li>
		<li><%=request.getQueryString() %></li>
	</ul>
</body>
</html>