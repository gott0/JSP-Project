package utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

public class JSFunction {
	//메세지창 출력 및 페이지 이동
	
	// 메세지 알림창을 띄운 후 명시한 URL로 이동합니다.
	public static void alertLocation(String msg, String url, JspWriter out) { // jsp에서 사용 됨
		try {
			
			String script = "" //삽입할 자바스크립트 코드
					+"<script>"
					+"	alert('"+ msg + "');"
					+"	location.href='" + url + "';"
					+"</script>";
			
			out.print(script);
		}catch(Exception e){
			
		}
	}
	
	
	
	
	
	public static void alertLocation(HttpServletResponse resp, String msg, String url) { //alertLocation메소드 오버로딩(서블릿에서 사용 됨)
		try { // 서블릿에서 사용할 수 있도록 매개변수 수정(response 객체의 getWriter메소드를 사용하기 위해)
			
			resp.setContentType("text/html;charset=UTF-8"); // 한글 깨짐 방지
			PrintWriter writer = resp.getWriter(); 
			String script = "" //삽입할 자바스크립트 코드
					+"<script>"
					+"	alert('"+ msg + "');"
					+"	location.href='" + url + "';"
					+"</script>";
			
			writer.print(script);
		}catch(Exception e){
			
		}
	}
	
	// 메세지 알림창을 띄운 후 이전 페이지로 돌아갑니다.
	public static void alertBack(String msg, JspWriter out) { //jsp에서 사용 됨
		try {
			
			String script = "" //삽입할 자바스크립트 코드
					+"<script>"
					+"	alert('"+ msg + "');"
					+"	history.back()"
					+"</script>";
			
			out.print(script);
		}catch(Exception e){
			
		}
	}
	
	
	public static void alertBack(HttpServletResponse resp, String msg) { //alertBack메소드 오버로딩(서블릿에서 사용 됨)
		try { // 서블릿에서 사용할 수 있도록 매개변수 수정(response 객체의 getWriter메소드를 사용하기 위해)
		
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter(); 
			String script = "" //삽입할 자바스크립트 코드
					+"<script>"
					+"	alert('"+ msg + "');"
					+"	history.back()"
					+"</script>";
			
			writer.print(script);
		}catch(Exception e){
			
		}
	}
}//class
