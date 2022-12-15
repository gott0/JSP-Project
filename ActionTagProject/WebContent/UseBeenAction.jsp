<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/* String name = request.getParameter("name");
	String age = request.getParameter("ade");   */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="person" class="common.Person" />
	<%-- <jsp:setProperty property="name" name="person" value=""/>
	<jsp:setProperty property="age" name="person" value=""/> --%>
	<%-- <jsp:setProperty property="name" name="person" param="name"/>
	<jsp:setProperty property="age" name="person" value="" param="age"/> --%>
	
	<jsp:setProperty property="*" name="person"/> <!-- '*' = 와일드카드 -->
	
	<ul>
		<li><jsp:getProperty property="name" name="person" />
		<li><jsp:getProperty property="age" name="person" />
	</ul>

</body>
</html>