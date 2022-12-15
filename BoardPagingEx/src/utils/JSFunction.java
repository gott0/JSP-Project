package utils;

import javax.servlet.jsp.JspWriter; //필요한 클래스 임포트

public class JSFunction {
	
	//메세지 알림창을 띄운 후 명시한 URL로 이동합니다.
	public static void alertLocation(String msg, String url, JspWriter out) {// msg: 알림창메세지 ,
		                                                                     // url: 알림창을 닫은 후 이동할 페이지의 url,
																			 // out: 자바스크립트 코드를 삽입할 출력 스트림(JSP의 out내장객체)
																			 // JSP의 내장객체를 클래스에서 사용하려면 이와같이 매개변수로 받아야 함
		try { 
			 //JSP의 내장객체를 클래스에서 사용하려면 이와같이 매개변수로 받아야 함
			String script = "" //삽입할 자바스크립트 코드를 문자열 형태로 선언
					+ "<script>"
					+ "   alert('" + msg + "');"  //알림창 코드
					+ "   location.href='" + url + "';"   //페이지 이동 코드 
					+ "</script>";  
			
			out.println(script); // 선언된 코드를 out객체로 출력
		}catch (Exception e){
			
		}		
	}
	
	
	//메세지 알림을 띄운 후 이전 페이지로 돌아갑니다.
	public static void alertBack(String msg, JspWriter out){
		try {
			String script=""
					+ "<script>"
					+ "   alert('" + msg + "');"
					+ "   history.back();" //호출자가 url을 지정할 수 없고 무조건 이전페이지로 이동함
					+ "</script>";
			out.println(script);
		}catch(Exception e) {
			
		}
	}
	
	
}//c