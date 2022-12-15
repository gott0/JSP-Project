<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ page import="java.util.Date"%>
<%@ page import="common.Person"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%-- <c:set></c:set>  or  <c:set/> --%>
	
	<!-- 변수를 정의하는 방식 -->
	<c:set var="directVar" value="100" />  <!--  int directVar = 100 -->
	<c:set var="elVar" value="${diretVar mod 5 }" /> <!-- mod = 나머지 구하는(?) -->
	<c:set var="expVar" value="<%= new Date() %>" />
	<c:set var="betweenVar" > 변수값 요렇게 설정</c:set>
	
	directVar : ${ pageScope.directVar }<br>
	elVar:${ elVar }<br>
	expVar:${ expVar }<br>
	betweenVar:${ betweenVar }<br><br>
	
	<c:set var="personVar1" value='<%=new Person("홍길동",10) %>' scope="request"/>
	${ requestScope.personVar1.name } <br>
	${ personVar1.age } <br>


	<c:remove var="personVar1" scope="request" />
	${ requestScope.personVar1.name } <br>
	${ personVar1.age } <br>
	
</body>
</html>