<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>   
<%@ page import="common.JDBConnect" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	JDBConnect jdbc = new JDBConnect();

	/* String updatesql = "update emp01 set deptno = ? where deptno = ?";    // 수정
	PreparedStatement psmt2 = jdbc.con.prepareStatement(updatesql);
	psmt2.setInt(1, 10);  //(물음표가 작성된 순서 , 값)
	psmt2.setInt(2, 30);  // set deptno = ?(=10) where deptno = ?(=30)이 됨   =>   deptno 30을 10으로 바꾼다.
	
	psmt2.executeUpdate(); //데이터 수정하기 */ 
	
	
	/* String deletesql = "delete from emp01 where deptno = ? or deptno = ?";
	PreparedStatement psmt3 = jdbc.con.prepareStatement(deletesql);
	psmt3.setInt(1,10); //
	psmt3.setInt(2,20); //where deptno = ?(=10) or deptno = ?(=20)"   =>   deptno 10인 데이터를 다 지운다.
	
	psmt3.executeUpdate(); //데이터 지우기 */
	
	String sql = "select * from EMP01";     //조회
	PreparedStatement psmt = jdbc.con.prepareStatement(sql);
	ResultSet rs = psmt.executeQuery();
	
	
	while(rs.next()){ //첫번째 행의 레코드가 존재하면 true, 실행문이 끝나면 rs.next() 커서가 두번째 행을 가르키게 되고 이 동작들이 반복됨
		int empno = rs.getInt(1);
		String ename = rs.getString(2);
		String job = rs.getString(3);
		int mgr = rs.getInt(4);
		String hiredate = rs.getString(5); // 날짜는 date외에 String 타입도 가능
		int sal = rs.getInt(6);
		int comm = rs.getInt(7);
		int deptno = rs.getInt(8);
		
		out.print(String.format("%d %s %s %d %s %d %d %d", empno , ename, job, mgr, hiredate, sal, comm, deptno) + "<br/>");
		
		//rs.next() 값이 존재하므로 truerl가 되어 반복문 실행, 한 행씩 해당 컬럼들의 값들이 조회된다   
	}
	
	jdbc.close();
	
%>
</body>
</html>