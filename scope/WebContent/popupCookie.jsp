<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String chkVal = request.getParameter("inactiveToday"); //체크박스를 체크 했는지 파라메터를 불러와 확인
		
	if(chkVal != null && chkVal.equals("1")){ //체크박스를 선택 햇으면(체크박스 벨류가 1일 경우) 실행문 실행(쿠키생성)
		
	//1.쿠키 객체를 생성한다.
	Cookie cookie = new Cookie("PopupClose","off");  //쿠키값에 공백이 들어가면 안됨!
	// 2. 쿠키의 생성 경로 결정	
	cookie.setPath(request.getContextPath());
	// 3.유지시간
	cookie.setMaxAge(60*60*24); //(단위 = 초)
	// 4.응답 헤더에 쿠키 정보 저장	
	response.addCookie(cookie);

	out.print("<h1>하루동안 창 열기 금지<h1>");
	
	}

%> 
