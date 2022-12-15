<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<li>${personObj.name} , ${personObj.age}</li>
		<li>${ stringObj }</li>
		<li>${ integerObj }</li>
	</ul>
	
	<P> ${param.firstNum}</P>  <!-- 10 -->
	<P> ${param.secondNum}</P> <!-- 20 -->
	
	<P> ${param.firstNum} + ${param.secondNum}</P>  <!-- 10 + 20 (파라메터는 문자열로 값을 받가 때문-->
	<P> ${param.firstNum + param.secondNum}</P>   <!-- 30 ( EL 안에서는 연산이 가능 )-->
</body>
</html>