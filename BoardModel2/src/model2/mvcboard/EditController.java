package model2.mvcboard;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import fileupload.FileUtil;
import utils.JSFunction;


@WebServlet("/edit.do")
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
    public EditController() {
        super();
  
    }

	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idx = request.getParameter("idx");
		MVCBoardDAO dao = new MVCBoardDAO();
		MVCBoardDTO dto = dao.selectView(idx);
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("./Edit.jsp").forward(request, response);
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1.파일의 물리적인 경로
		String saveDirectory = request.getServletContext().getRealPath("/Uploads");
		
		// 2.파일의 용량
		int maxPostSize = 1024 * 1000;
		
		// 3.MultipartRequest를 이용한 파일 업로드
		MultipartRequest mr = FileUtil.uploadFile(request, saveDirectory, maxPostSize);	

		// 4.파일 업로드 실행 유무
		if(mr == null) {
			JSFunction.alertBack(response, "첨부 파일이 제한 용량을 초과합니다.");
			
			return;	
		}
	
		// 5.입력 정보의 파라메터 받기
		String idx = mr.getParameter("idx");
		String preOfile = mr.getParameter("preOfile");
		String preSfile = mr.getParameter("preSfile");

		String name = mr.getParameter("name");
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		
		//비밀번호는 session에서 가져옴
		HttpSession session = request.getSession(); // 서블릿에서 세션 객체를 쓰기 위한 코드
		String pass = (String)session.getAttribute("pass");
		
		// 6.DTO에 저장하기
		MVCBoardDTO dto = new MVCBoardDTO();
		dto.setIdx(idx);
		dto.setName(name);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setPass(pass);
	
		// 7.파일 변경 유무 확인
		//원본파일명과 저장된 파일 이름 설정
		String fileName = mr.getFilesystemName("ofile");
		if(fileName != null) {
			//첨부파일이 있을 경우 파일명 변경
			//새로운 파일명 생성
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = fileName.substring(fileName.lastIndexOf(".")); 
			String newFileName = now + ext;
			
			//파일명 변경
			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newFileName);	
			oldFile.renameTo(newFile);
			
			//DTO에 저장
			dto.setOfile(fileName); 
			dto.setSfile(newFileName); 
			
			//기존 파일 삭제
			FileUtil.deleteFile(request, "Uploads", preSfile);
			
		}else {
			//첨부파일이 없으면 기존 이름 유지
			dto.setOfile(preOfile);
			dto.setSfile(preSfile);
			
		}
		
		//DB에 수정 내용 반영
		MVCBoardDAO dao =  new MVCBoardDAO();
		int result = dao.updatePost(dto);
		dao.close();
		
		//성공 or 실패 여부
		if(result == 1) { //수정 성공
			session.removeAttribute("pass");
			response.sendRedirect("view.do?idx=" + idx);
		}else { // 수정 실패
			JSFunction.alertLocation(response, "비밀번호 검증을 다시 진행해주세요.", "view.do?idx=" + idx);
		}
	
	
	}

}//c
