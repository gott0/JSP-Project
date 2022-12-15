package common;

import java.sql.*;

import javax.servlet.ServletContext;

public class JDBConnect {
	public Connection con;
	public ResultSet rs;
	public PreparedStatement psmt;
	public Statement stmt;
	

	public JDBConnect() {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver"); //드라이버 정보를 확인 & 찾아주는 역할
			con = DriverManager.getConnection(url,"scott", "tiger");	// (DB주소 , 아이디(오라클 계정), 계정 비번(대소문자 구분-문자열))			
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public JDBConnect(String driver, String url, String id, String pwd) { //생성자 오버로딩
		try{
			Class.forName(driver); 
			con = DriverManager.getConnection(url, id, pwd); 	
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public JDBConnect(ServletContext application) { 
		try{
			String driver = application.getInitParameter("OracleDriver");
			
			Class.forName(driver);
			
			String url = application.getInitParameter("OracleURL");
			String id = application.getInitParameter("OracleId");
			String pwd = application.getInitParameter("OraclePwd");
			
			con = DriverManager.getConnection(url, id, pwd);		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void close() {
		try{
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(psmt != null) psmt.close();
			if(con != null) con.close();    //닫는 순서도 중요! [rs(처음) -> stmt and psmt는 상관없음 -> con(마지막)]
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
