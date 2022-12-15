<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<% 
	// 3대 지시어 : page(기본설정), include(선택), taglib(선택)
	// 지시어는 <%에 @를 붙여쓴다!
	// 지시어 증 하나인 page는 jsp 페이지에 대한 정보를 설정한다.
    
%>  <!-- <% %> = 스크립트릿: 자바 코드 작성 방식(태그 젤 위나 바디부분에 작성가능-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	Date today = new Date();
	int hour = today.getHours();
	//out.print(hour);
	
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	out.print(sf.format(today));

	
	
%>
</body>
</html>