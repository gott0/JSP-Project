<%@ page import="utils.CookieManager"%>
<%@ page import="utils.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%
	//request 내장 객체를 통해 전송된 폼 값 받기 
	String user_id = request.getParameter("user_id");
	String user_pw = request.getParameter("user_pw");
	String save_check = request.getParameter("save_check");	
	
	if("musthave".equals(user_id) && "1234".equals(user_pw)){ // 아이디와 패스워드를 하드코딩된 값과 비교(사용자 인증)
		// 로그인 성공 시
		if(save_check != null && save_check.equals("y")){ // 아이디 저장하기 체크 확인
			CookieManager.makeCookie(response, "loginId", user_id, 86400); //저장하기 체크일 경우, 쿠기생성(response,쿠키명,쿠키값,지속시간[86400초=24시간])
		}else{ // 로그인 성공은 했지만 체크박스 체크가 안 됬을 경우, 쿠키 삭제(response,쿠키명)
			CookieManager.deleteCookie(response, "loginId");
		}
	
		JSFunction.alertLocation("로그인에 성공했습니다", "IdSaveMain.jsp", out);
		
	}
	else{
		// 로그인 실패
		JSFunction.alertBack("로그인에 실패하였습니다.", out);
	}
	
		/* 
		하드코딩: 값을 고정시켜 놓는 것
		public String hardCoding(String val) {
			return "하드코딩";
		}
		
		소프트코딩: 값이 고정되지 않고 가변적인 것
		public String softCoding(String val) {
			return val;
		} 
		*/
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>