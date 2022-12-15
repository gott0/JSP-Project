<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <jsp:forward page="ParamForward.jsp">
		<jsp:param value="임꺽정" name="param1"/>		
		<jsp:param value="10" name="param2"></jsp:param>
	</jsp:forward> --%>
		<!-- name = 변수 , value = 값 -->
		
	<jsp:include page="inc/ParamInclude.jsp">
		<jsp:param value="경상도" name="loc1"/>
		<jsp:param value="대구" name="loc2"/>
	</jsp:include>
</body>
</html>