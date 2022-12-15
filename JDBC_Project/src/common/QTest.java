package common;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class QTest {
	public Connection con1;
	public Statement stmt1;
	public PreparedStatement psmt1;
	public ResultSet rs1;
	
	public QTest() {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			con1 = DriverManager.getConnection(url,"sott", "tiger");	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public QTest(String driver, String url, String id, String pwd) { 
		try{
			Class.forName(driver); 
			con1 = DriverManager.getConnection(url, id, pwd); 	
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void close() {
		try{
			if(rs1 != null) rs1.close();
			if(stmt1 != null) stmt1.close();
			if(psmt1 != null) psmt1.close();
			if(con1 != null) con1.close();  
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
}
