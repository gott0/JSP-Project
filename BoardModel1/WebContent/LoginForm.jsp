<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="./Common/Link.jsp"></jsp:include>
	<h2>로그인 페이지</h2>
	<span style="color: red; font-size: 1.2em;">
		<%= request.getAttribute("LoginErrMsg") == null ?
			"":request.getAttribute("LoginErrMsg")%>
	</span>
	<%
	if(session.getAttribute("UserId") == null){
	%>
	<script>
	function validateForm(form){
		if (!form.user_id.value){
			alert("아이디를 입력하세요.");
			return false;
		}
		if (form.user_pw.value == ""){ //!form.user_pw.value와 같음 둘 다 값이 없다는 뜻
			alert("패스워드를 입력하세요.");
			return false;
		}	
	}
	</script>
	<form action="LoginProcess.jsp" method="post" name="loginFrm" 
	onsubmit= "return validateForm(this);"> <!-- return validateForm(this)가 true면 액션값으로 페이지이동, false면 종료 -->
		아이디: <input type="text" name="user_id" /><br/>
		패스워드: <input type="password" name="user_pw" /><br/>
		<input type="submit" value="로그인하기" />
	</form>
	<%
	}else{
	%>
		<%= session.getAttribute("UserName")%> 회원님, 로그인 하셨습니다.<br/>
		<a href="Logout.jsp">[로그아웃]</a>
	<% 
	}
	%>
		
</body>
</html>