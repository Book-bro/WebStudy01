package kr.or.ddit.servlet02;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/now.do")
public class NowServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1요청받고, 2 인포메이션 만들기
		Date now = new Date();
		req.setAttribute("now", now);
		
		String viewPath = "/01/sample.tmpl";
		req.getRequestDispatcher(viewPath).forward(req, resp);
	}

}
