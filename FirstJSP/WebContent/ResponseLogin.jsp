<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String id = request.getParameter("user_id");
	String pwd = request.getParameter("user_pwd");
	
	if(id.equalsIgnoreCase("must") && pwd.equalsIgnoreCase("1234")){ //equalsIgnoreCase = 문자열 대소문자 구분 없이 인식
		
			response.sendRedirect("ResponseWelcome.jsp");
	}else{
		response.sendRedirect("request3.jsp");
	}
%>
