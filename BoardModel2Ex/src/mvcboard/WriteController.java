package mvcboard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/write.do") //<-해당 매핑 주소로 요청이 들어옴
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public WriteController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Write.jsp").forward(request, response); // 단순히 "Write.jsp" 페이지로 이동 역할
	} // get방식으로 요청을 받으면 실행되는 메소드

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
