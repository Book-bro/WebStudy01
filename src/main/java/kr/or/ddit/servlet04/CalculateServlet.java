package kr.or.ddit.servlet04;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.CalculateVO;

@WebServlet("/04/calculate")
public class CalculateServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "/WEB-INF/views/03/calculateForm.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CalculateVO calculateVO = null;
		try(
			InputStream is = req.getInputStream();
		){
			calculateVO = new ObjectMapper().readValue(is, CalculateVO.class);
		}
		
		req.setAttribute("expression", calculateVO.getExpression());
		req.setAttribute("message", calculateVO.getExpression());
		
		String accept = req.getHeader("Accept");
		String viewName = null;
		if(accept.contains("json")) {
			viewName = "/jsonView.do";
		}else {
			viewName = "/WEB-INF/views/04/plainView.jsp";
		}
		req.getRequestDispatcher(viewName).forward(req, resp);
		
	}
}
