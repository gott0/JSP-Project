<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//쿠기 정보 만들기

	// 1.쿠키 객체를 생성한다.
	Cookie cookie = new Cookie("myCookie","쿠키맛나요");  //쿠키값에 공백이 들어가면 안됨!
	// 2. 쿠키의 생성 경로 결정	
	cookie.setPath(request.getContextPath());
	// 3.유지시간
	cookie.setMaxAge(3600); //(단위 = 초, 현재 3600초 = 1시간)
	// 4.응답 헤더에 쿠키 정보 저장	
	response.addCookie(cookie);
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2> 2.쿠키 설정 직후 쿠키값 확인하기</h2>
	<%
		//쿠기 정보 읽어오기
	
		// 1.요청(request) 헤더에서 쿠키정보 얻기
		Cookie[] cookies = request.getCookies();
		// 2.쿠키정보가 있는지 확인
		if(cookies != null){
			for(Cookie c : cookies){
				String cookieName = c.getName();
				String cookieValue = c.getValue();
				out.print("<br>");
				out.print(cookieName + " : " + cookieValue);
			}
		}else{
			out.print("쿠키 정보가 없습니다.");
		}
		
	%>
</body>
</html>