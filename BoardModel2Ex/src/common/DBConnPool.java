package common;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class DBConnPool { //모델
	
	public Connection con;
    public Statement stmt;
    public PreparedStatement psmt;
    public ResultSet rs;

	
	public DBConnPool() {
		try {
			Context intiCtx = new InitialContext();
			javax.naming.Context ctx = (Context)intiCtx.lookup("java:comp/env");
			DataSource source = (DataSource)ctx.lookup("dbcp_myoracle");
		
		con = source.getConnection();
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	 public void close() {
	      try {
	    	  if (rs != null) rs.close();
	          if (stmt != null) stmt.close();
	          if (psmt != null) psmt.close();
	          if (con != null) {
	            con.close();
	         }
	      }catch(Exception e){
	         e.printStackTrace();
	      }
	   }

	
	
	
}
