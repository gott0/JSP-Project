<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>1.클라이언트와 서버의 환경정보 읽기</h2>
	<a href="./RequestWebInfo.jsp?eng=Hello&han=안녕">  <!-- GET 방식으로 요청 (주소창에 값이 노출)-->
	<!-- query string: ?뒤에 값을 붙이는 것(a.k.a: 파라메터값) --> 
	 GET 방식 전송
	</a>
	
	<br/><br/>	
	
	<form action="RequestWebInfo.jsp" method="post">  <!-- POST 방식으로 요청 (주소창에 값이 비노출)--> 
		영어 : <input type="text" name="eng" value="Bye" /><br/> <!-- form태그를 이용한 방식 뿐 <form method="post"> -->
		한글 : <input type="text" name="han" value="안녕" /><br/> <!-- name 속성을 반드시 적어야 됨 (name 속성이 파라메터의 역할)-->
		<input type="submit" value="POST 방식 전송" />
	</form>
	 			<!-- *name에 해당하는 파라메터 값(데이터)을 action속성이 적용된 페이지로 보냄  -->
<!-- 	
		C -> S request(get,post)
	    C <- S respanse
	    
	    jsp : 동기
	    ajax : 비동기
 -->	
 
 
</body>
</html>