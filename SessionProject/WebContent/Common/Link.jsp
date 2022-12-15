<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<table border="1" width="90%">
	 <tr>
	 	<td align="center">
	 	<!-- 로그인 여부에 따른 메뉴 변화 -->
	 	<% if(session.getAttribute("UserId") == null ) { %>
	 	<a href="../06sesstion/LoginForm.jsp">로그인</a>
		<%}else{ %> 	
	 	<a href="../06sesstion/Logout.jsp">로그아웃</a>
	 	<%} %>
	 		<!-- 8,9장의 회원제 게시판 프로젝트에서 사용할 링크 -->
	 		&nbsp;&nbsp;&nbsp; <!-- 메뉴사이에 공백 확보 -->
	 		<a href="../08borser/List.jsp">게시판(페이징X)</a>
	 		&nbsp;&nbsp;&nbsp;	
		 	<a href="../09PagingBoard/List.jsp">게시판(페이징O)</a>
		</td>
	</tr>	 	
</table>