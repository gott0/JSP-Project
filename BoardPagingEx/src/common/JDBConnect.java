package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletContext;

import java.sql.ResultSet;

public class JDBConnect { // DB연결 관리 클래스

	public Connection con; //DB와의 연결을 담당
	public Statement stmt; //정적 쿼리문을 실행할 때 사용
	public PreparedStatement psmt; //동적 쿼리문을 실행할 때 사용
	public ResultSet rs; //쿼리문의 결과를 저장할 때 사용
	
	//기본생성자
	public JDBConnect() {
		try {
			//JDBC드라이버 로드
			Class.forName("oracle.jdbc.OracleDriver");
			
			//DB에 연결
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "musthave";
			String pwd = "1234";
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB연결 성공(기본생성자)");
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	//기본생성자 다른 방법
//	public JDBConnect()) {
//		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//		try{
//			Class.forName("oracle.jdbc.driver.OracleDriver"); //드라이버 정보를 확인 & 찾아주는 역할
//			con = DriverManager.getConnection(url,"scott", "tiger");	// (DB주소 , 아이디(오라클 계정), 계정 비번(대소문자 구분-문자열))			
//		
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	
	
	//두번째 생성자
	public JDBConnect(String driver, String url, String id, String pwd) { //생성자 오버로딩
		try{
			//JDBC 드라이버 로드
			Class.forName(driver); 
			//DB에 연결
			con = DriverManager.getConnection(url, id, pwd); 	
		
			System.out.println("DB연결 성공(인수 생성자1)");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//세번째 생성자
	public JDBConnect(ServletContext application) { 
		try{
			//JDBC 드라이버 로드
			String driver = application.getInitParameter("OracleDriver");
			Class.forName(driver);
			
			//DB에 연결
			String url = application.getInitParameter("OracleURL");
			String id = application.getInitParameter("OracleId");
			String pwd = application.getInitParameter("OraclePwd");
			con = DriverManager.getConnection(url, id, pwd);		
			
			System.out.println("DB연결 성공(인수 생성자2)");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//연결해제(자원반납)
	public void close() {
		try{
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(psmt != null) psmt.close();
			if(con != null) con.close();    //닫는 순서도 중요! [rs(처음) -> stmt and psmt는 상관없음 -> con(마지막)]
			
			System.out.println("JDBC 자원 해제");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}//c
