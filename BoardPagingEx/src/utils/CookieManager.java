package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager { //쿠키를 생성할 때는 객체 생성, 경로 및 유지기간 설정 등이 필요.
	
	//명시한 이름, 값, 유지 기간 조건으로 새로운 쿠키를 생성합니다.
	public static void makeCookie(HttpServletResponse response, String cName, String cValue, int cTime) {
		
		Cookie cookie = new Cookie(cName, cValue); //쿠키 생성
		
		cookie.setPath("/");  //경로 설정 ( "/"은 웹 애플리케이션 전체에서 사용됨을 의미)
		cookie.setMaxAge(cTime); //유지 기간 설정
		response.addCookie(cookie); //응답 객체에 추가해 클라이언트로 쿠키 전송
	
	} // 4개의 변수를 받아 쿠키생성 후 응답헤더에 추가

	
	//명시한 이름의 쿠키를 찾아 그 값을 반환합니다.
	public static String readCookie(HttpServletRequest request, String cName) { //request 내장 객체부터 클라이언트가 보내온 쿠키 목록을 받아서
																				//cName과 이름이 같은 쿠키가 있다면 그 값을 반환
		String cookieValue = ""; // 반환값과 반환 타입 설정
		
		//1.요청(request) 헤더에서 쿠키정보 얻기
		Cookie[] cookies = request.getCookies(); // request에서 받아온 쿠키 목록들을 배열로 선언(헤더에 있는 모든 쿠키 목록를 배열에 담음)
		
		// 2.쿠키정보가 있는지 확인
		if(cookies != null) { // 쿠키 이름이 null이 아닌 경우
			for(Cookie c : cookies) {
				String cookieName = c.getName(); //헤더에서 가져온 모든 쿠키 목록 중 순차적으로 하나씩 cookieName 변수에 담음
				if(cookieName.equals(cName)) // 그게 cName과 같을 경우 
					cookieValue = c.getValue(); // 반환값 갱신 (""를 해당 네임의 쿠기 값으로 갱신)
			}
		}
		return cookieValue; //(쿠키값 반환)
	}
	
	
	//명시한 이름의 쿠키를 삭제합니다
	public static void deleteCookie(HttpServletResponse response, String cName) {
		makeCookie(response,cName,"",0);
	}
	
	
}//c
