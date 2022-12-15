<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>    
<%@ page import="common.JDBConnect" %>
    
<%
	//DML
	
	//    Statement     (정적)  = ex) select나 insert 등 쿼리문에서 조회되거나 추가,수정,삭제되는 값이 정해진 경우 
	// PreparedStatement (동적)  = ex) select나 insert 등 쿼리문에서 조회되거나 추가,수정,삭제되는 값이 변수를 적용하는 것 처럼 다양할 경우
	// ( Statementsql , PreparedStatement)는 sql를 다룰 수 있도록 오라클에서 제공하는 객체
	
	//ResultSet = 결과값을 받아오는 객체
	
	//executeQuery() - select
	//executeUpdate() - insert, update, delete

%>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- <h2>회원 추가 테스트(executeUpdate() 사용)</h2> -->
	<%
	/* JDBConnect jdbc = new JDBConnect();
	
	String sql = "insert into member values(?,?,?,sysdate)";  // 동적 쿼리문
	PreparedStatement psmt = jdbc.con.prepareStatement(sql);
	psmt.setString(1, "test1"); // (몇번째의 ,  값)
	psmt.setString(2, "1111");
	psmt.setString(3, "테스트 1회원");
	
	int result = psmt.executeUpdate(); //쿼리문 실행
	
	if(result != 0){
		out.print("쿼리문 실행 완료");
	}else{
		out.print("쿼리문 실행 실패");
	}
	
	
	
	jdbc.close(); */
	
	%>
	
	<h2>회원 목록 조회 테스트(executeQuery()사용)</h2>
	<%
	JDBConnect jdbc = new JDBConnect();
	
	String sql = "select * from member"; //정적 쿼리문
	Statement stmt = jdbc.con.createStatement();
	
	ResultSet rs = stmt.executeQuery(sql);
	
	while(rs.next()){ //true or false로 반환
		String id = rs.getString("id");   // rs.getString(1);
		String pw = rs.getString("pass");  // rs.getString(2);
		String name = rs.getString("name");
		java.sql.Date regidate = rs.getDate("regidate");
		
		out.println(String.format("%s %s %s %s", id, pw, name, regidate) + "<br/>");
		
	}
	 
	/* ResultSet, PreparedStatement, Statement 객체는 서버의 부하를 막기위해 닫아줘야됨 */
	
	
	jdbc.close();	/* 닫는 순서 : rs(처음) -> stmt and psmt는 상관없음 -> con(마지막) */
	
	
	%>
	
	
	
</body>
</html>