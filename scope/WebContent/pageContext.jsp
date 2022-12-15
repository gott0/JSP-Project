<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="common.Person" %>
    
<%
	pageContext.setAttribute("pageInteger", 1000);
	pageContext.setAttribute("pageString", "페이지 영역");
	pageContext.setAttribute("pagePerson", new Person("홍길동",30));
	// page영역의 유효범위 : 페이지 이동시 데이터 소멸

%>
        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>page 영역에서 속성값 읽기</h2>
	<%
		int num = (Integer)(pageContext.getAttribute("pageInteger"));
		String str = (String)(pageContext.getAttribute("pageString"));
		Person p = (Person)(pageContext.getAttribute("pagePerson"));
	%>
	
	<ul>
		<li><%=num %></li>
		<li><%=str %></li>
		<li><%=p.getName() %>, <%=p.getAge() %></li>
	</ul>
	
	<h2>페이지 이동 후 page영역 읽어오기</h2>
	<a href="PageLocation.jsp">다음페이지로 이동</a>
</body>
</html>