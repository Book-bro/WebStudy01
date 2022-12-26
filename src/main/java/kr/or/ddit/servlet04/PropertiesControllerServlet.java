package kr.or.ddit.servlet04;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.or.ddit.servlet01.DescriptionServlet;
import kr.or.ddit.servlet04.service.PropertiesService;
import kr.or.ddit.servlet04.service.PropertiesServiceImpl;

@WebServlet("/03/props/propsView.do")
public class PropertiesControllerServlet extends HttpServlet{
	private PropertiesService service = new PropertiesServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String accept = req.getHeader("Accept"); // */*
		
		Object target = service.retrieveData();
		req.setAttribute("target", target);
		
		String path = null;
		
		if(accept.startsWith("*/*") || accept.toLowerCase().contains("html")) {
			path = "/WEB-INF/views/03/propsView.jsp";
		}else if(accept.toLowerCase().contains("json")) {
			path = "/jsonView.do";
		}else if(accept.toLowerCase().contains("xml")) {
			path = "/xmlView.do";
		}
		req.getRequestDispatcher(path).forward(req, resp);
		
	}
}







