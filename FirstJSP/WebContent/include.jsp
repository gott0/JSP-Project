<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="includeFile.jsp" %>
	
<% 
	// 지시어 증 하나인 include는 한 화면에 여러 부분파일들을 포함시키는 역할.
    // (메인 파일 작성 후 부분 파일을 포함 시킴)
    
   // 절대 경로 : http://localhost:8081/FirstJSP(프로젝트까지)/
   // 상대 경로 : includeFile.jsp(같은 프로젝트일 경우)
%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	out.print("오늘 날짜 : " + today);
	out.print("<br/>");
	out.print("내일 날짜 : " + tomorrow);

%>
</body>
</html>