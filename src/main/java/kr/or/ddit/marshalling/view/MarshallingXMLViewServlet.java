package kr.or.ddit.marshalling.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@WebServlet("/xmlView.do")
public class MarshallingXMLViewServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//	     native(DataStore.properties)->json : marshalling
//	     {"prop1":"value1", ...}
		   Object target = req.getAttribute("target");
		   ObjectMapper mapper = new XmlMapper();
//	     1. marshalling
//		   String json = mapper.writeValueAsString(target);
		   resp.setContentType("application/xml;charset=UTF-8");
		   try(
				   PrintWriter out = resp.getWriter();
		   ){
//	     	2. serialization
//		      out.print(json);
		   mapper.writeValue(out, target);
		   }
	}

}
