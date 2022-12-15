<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>       
<%@ page import="common.QTest" %>

<%

	QTest jdbc1 = new QTest();

	out.print("<h1>Test DB연결 성공</h1>");
	jdbc1.close();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2> EMP01 테이블 조회 </h2>
	<%
	
	
	String sql1 = "select * form EMP01";
	Statement stmt1 = jdbc1.con1.createStatement();
	
	ResultSet rs1 = stmt1.executeQuery(sql1);
	
	while(rs1.next()){
		int empno = rs1.getInt(1);
		String ename = rs1.getString(2);
		String job = rs1.getString(3);
		int mgr = rs1.getInt(4);
		String hiredate = rs1.getString(5); // 날짜는 date외에 String 타입도 가능
		int sal = rs1.getInt(6);
		int comm = rs1.getInt(7);
		int deptno = rs1.getInt(8);
		
		out.print(String.format("%d %s %s %d %s %d %d %d", empno , ename, job, mgr, hiredate, sal, comm, deptno) + "<br/>");
	
	}
	
	jdbc1.close();
	
	%>
</body>
</html>