<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="utils.CookieManager" %>    
<%@ page import="utils.JSFunction" %>    
<%
	String user_id = request.getParameter("user_id");
	String user_pw = request.getParameter("user_pw");
	String save_check = request.getParameter("save_check");

	if("must".equals(user_id) && "1234".equals(user_pw)){ //로그인 성공, 실패 유무
		if(save_check != null && save_check.equals("Y")){ //아이디 저장 체크 유무 
			CookieManager.makeCookie(response, "loginId", user_id, 86400); //저장 체크하고 로그인 성공 시 쿠키 생성 
		}else{														//86400초 = 하루
			CookieManager.deleteCookie(response, "loginId"); //저장 체크 안 하고 로그인 성공 시 쿠키는 삭제
			
		}
		
		JSFunction.alertLocation("로그인 성공", "idSave.jsp", out);
	}else{
		JSFunction.alertBack("로그인 실패", out); // 로그인 실패 시
		
	}
	
%>