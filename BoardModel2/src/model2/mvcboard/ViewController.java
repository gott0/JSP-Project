package model2.mvcboard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/view.do")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public ViewController() {
        super();
    }

    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		// service:get과 post 등 어떤 방식이든 다 받을 수 있다.
		
		//게시물 불러오기
		MVCBoardDAO dao = new MVCBoardDAO();
		String idx = request.getParameter("idx");
		dao.updateVisitCount(idx); // 조회수 1증가 실행 메소드
		MVCBoardDTO dto = dao.selectView(idx); // 해당 글에 대한 상세보기 실행 메소드
		dao.close();
		
		//줄바꿈 처리
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br>")); //내용 작성 시 엔터(줄바꿈)는 '\r\n'로 표시 됨(브라우저에서는 안 보임) 이를 그대로 
								                                   //작성하면 실질적인 줄바꿈이 적용되지 않음 따라서 '<br>'로 바꿔주어 실질적인 줄바꿈을 적용	
		//게시물(dto) 저장 후 뷰로 포워드
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("./View.jsp").forward(request, response);// View.jsp로 이동
	}
	
	

}
