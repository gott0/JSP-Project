<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title>
<script type="text/javascript">
	function validateForm(form) {
		if(form.pass.value == ""){
			alert("비밀번호를 입력하세요.")
			form.pass.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<h2>파일 첨부형 게시판 - 비밀번호 검증(Pass)</h2>
	<form name="writeFrm" method="post" action="pass.do" onsubmit="return validateForm(this);">
	<input type="hidden" name="idx" value="${ param.idx }" />   <!-- idx = 전달될 글번호(idx)값 (히든값 = 강제전송)-->
	<input type="hidden" name="mode" value="${ param.mode }" />  <!-- mode = 전달될 삭제or수정(mode)값 (히든값 = 강제전송)-->
	<table border="1" width="90%">
		<tr>
			<td>비밀번호</td>
			<td>
				<input type="password" name="pass" style="width: 100px;"/>	 <!-- pass = 전달될 비밀번호 (입려값)-->
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<button type="submit">검증하기</button>
				<button type="reset">RESET</button>
				<button type="button" onclick="location.href='list.do';">
					목록 바로가기
				</button>
			</td>
		</tr>	
	</table>
	</form>
</body>
</html>