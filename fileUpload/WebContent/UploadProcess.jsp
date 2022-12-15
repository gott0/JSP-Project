<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="fileupload.MyFileDTO" %>    
<%@ page import="fileupload.MyFileDAO" %>     
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.io.File"%>

<%
	String saveDirectory = application.getRealPath("/Uploads"); // ┌ 실제로 이 역로를 뜻함
	//C:\work\JSPworkspace\.metadata\.plugins\org.eclipse.wst.server.core
	//                                      \tmp0\wtpwebapps\fileUpload\Uploads
	int maxPostSize = 1024 * 1000;
	String encoding = "UTF-8";
	
	try{

		// 1.객체 생성
		MultipartRequest mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding);
		
		String fileName = mr.getFilesystemName("attachedFile");
		String ext = fileName.substring(fileName.lastIndexOf(".")); //확장자 얻기
		String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
		String newFileName = now + ext;
		
		File oldFile = new File(saveDirectory + File.separator + fileName);
		File newFile = new File(saveDirectory + File.separator + newFileName);	
		oldFile.renameTo(newFile);
		
		//2.나머지 폼 데이터 받기
		String name = mr.getParameter("name");
		String title = mr.getParameter("title");
		String[] cateArray = mr.getParameterValues("cate");
		StringBuffer cateBuf = new StringBuffer();
		if(cateArray == null){
			cateBuf.append("선택 없음");
		}else{
			for(String s : cateArray){
				cateBuf.append(s + ", ");
			}
		}
		
		//3.DTO생성 및 저장
		MyFileDTO dto = new MyFileDTO();
		dto.setName(name);
		dto.setTitle(title);
		dto.setCate(cateBuf.toString());
		dto.setOfile(fileName);
		dto.setSfile(newFileName);
		
		//4.DAO생성 및 저장
		MyFileDAO dao = new MyFileDAO();
		dao.insertFile(dto);
		dao.close();
	
		response.sendRedirect("FileList.jsp");
		
	}
	catch(Exception e){
		e.printStackTrace();
		request.setAttribute("errorMessage", "파일업로드 오류");
		RequestDispatcher fw = request.getRequestDispatcher("FileUploadMain.jsp");
		fw.forward(request, response);
	}
	
	
	
%> 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>