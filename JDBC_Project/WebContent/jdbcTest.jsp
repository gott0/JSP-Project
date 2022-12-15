<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
//직접치는 방식

	Connection conn = null;

	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver"); //드라이버 정보를 확인 & 찾아주는 역할
		conn = DriverManager.getConnection(url,"musthave", "1234");	// (DB주소 , 아이디(오라클 계정), 계정 비번(대소문자 구분-문자열))
		
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	DB연결
</body>
</html>