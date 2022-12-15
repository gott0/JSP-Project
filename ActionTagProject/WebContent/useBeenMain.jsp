<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 
	- 빈클래스를 생성하기 위해
	- getter / setter
	<jsp:useBean id=" dao (자바빈즈의 이름(변수)) "   class=" 사용할 패키지와 클래스명 "   scope=" 저장될객체(영역객체) " />
	<jsp:setProperty name="dao"  property="id"(소문자로 해도됨)  value="hong" />    //  dao.setId("hong");
	<jsp:getProperty name="dao"  property="id"(소문자로 해도됨)  />   // dao.getId(); 
	--%>
	
	
	<jsp:useBean id="person" class="common.Person" scope="request"></jsp:useBean>

	<jsp:setProperty property="name" name="person" value="임꺽정" />
	<jsp:setProperty property="age" name="person" value="10" />

	<ul>
		<li><jsp:getProperty property="name" name="person" />
		<li><jsp:getProperty property="age" name="person" />
	
	</ul>

</body>
</html>