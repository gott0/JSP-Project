package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DirectServletPrint.do")
public class DirectServletPrint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DirectServletPrint() {
        super();
     
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); //post방식에서 한글이 안깨지도록 하는 법
		
		PrintWriter writer = response.getWriter();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		writer.println("<html>");
		writer.println("<body>");
		writer.println("<h2>Post 방식의 요청입니다.</h2>");
		writer.println("<p>아이디: " + id + "</p>");
		writer.println("<p>비밀번호: " + pwd + "</p>");
		writer.println("</body>");
		writer.println("</html>");
		writer.close();
	}

}
