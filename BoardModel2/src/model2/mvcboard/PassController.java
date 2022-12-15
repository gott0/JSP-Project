package model2.mvcboard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import fileupload.FileUtil;
import utils.JSFunction;

@WebServlet("/pass.do")
public class PassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpServletResponse resp;
      
    public PassController() {
        super();
      
    }

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 비밀번호 확인 화면을 띄움
		
		//view.jsp에서 받아오는 값(get방식 - 쿼리스트링(querystring)형태로 받음 (참고 - view.jsp의 하단메뉴 파트에서 수정,삭제 버튼에 쿼리스트링문있음)
		String mode = request.getParameter("mode"); // mode의 값에 따라 수정하기와 삭제하기로 나뉨
		request.setAttribute("mode", mode);
		request.getRequestDispatcher("./Pass.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//비밀번호가 맞는지 확인
		
		//pass.jsp에서 받아오는 값(post방식 - form형태를 받음)
		String idx = request.getParameter("idx");   
		String mode = request.getParameter("mode");
		String pass = request.getParameter("pass");

		MVCBoardDAO dao = new MVCBoardDAO();
		boolean confirmed = dao.confirmPassword(pass, idx);  //비밀번호 & 글번호 확인하는 메소드
		dao.close();
		
		if(confirmed) { // 비밀번호가 일치하는 경우, 수정인지 삭제인지 mode값으로 구분
			if(mode.equals("edit")) {
				
				HttpSession session = request.getSession();
				session.setAttribute("pass", pass);
				response.sendRedirect("edit.do?idx=" + idx);
				
			
			}else if(mode.equals("delete")) {
				dao = new MVCBoardDAO();
				MVCBoardDTO dto = dao.selectView(idx); //해당 글번호로 idx값을 넘겨서 글의 존재여부 확인
				int result = dao.deletePost(idx); //확인이 완료되면 글 삭제하는 메소드
				
				if(result == 1) { // 반환값이 1이면 게시글 삭제가 잘 이루어졌다는 것
					String saveFileName = dto.getSfile();
					FileUtil.deleteFile(request, "/Uploads", saveFileName);  //서버에 남은 업로드 됬던 파일 삭제(글이 삭제되면 서버의 잔여 첨부파일도 따로 삭제해야됨)
				}
				
				JSFunction.alertLocation(response, "삭제되었습니다.", "list.do");
			
			}
		}else { // 비밀번호가 틀릴 시
			JSFunction.alertBack(response, "비밀번호 불일치");
		}
	}

}//c
