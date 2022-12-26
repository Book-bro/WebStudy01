package kr.or.ddit.memo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.or.ddit.memo.dao.DataBaseMemoDAOImpl;
import kr.or.ddit.memo.dao.FileSystemMemoDAOImpl;
import kr.or.ddit.memo.dao.MemoDAO;
import kr.or.ddit.vo.MemoVO;

@WebServlet("/memo")
public class MemoControllerServlet extends HttpServlet{
	
//	private MemoDAO dao = FileSystemMemoDAOImpl.getInstance();
	private MemoDAO dao = DataBaseMemoDAOImpl.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("Accept");
		if(accept.contains("xml")) {
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
			return;
		}
		
		List<MemoVO> memoList = dao.selectMemoList();
		req.setAttribute("memoList", memoList);
		String viewName = "/jsonView.do";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Post-Redirect-Get : PRG pattern
		MemoVO memo = getMemoFromRequest(req);
		dao.insertMemo(memo);
		resp.sendRedirect(req.getContextPath()+"/memo"); //다시 돌아가서 리스트 갱신
	}
	
	private MemoVO getMemoFromRequest(HttpServletRequest req) throws IOException{
		String contentType = req.getContentType();
		MemoVO memo = null;
		if(contentType.contains("json")) {
			try(
					BufferedReader br = req.getReader(); //body content read 용 입력 스트림
					){
				memo = new ObjectMapper().readValue(br, MemoVO.class);
			}
			
		}else if(contentType.contains("xml")) {
			try(
					BufferedReader br = req.getReader(); //body content read 용 입력 스트림
					){
				memo = new XmlMapper().readValue(br, MemoVO.class);
			}
		}else {
			memo = new MemoVO();
			memo.setWriter(req.getParameter("writer"));
			memo.setDate(req.getParameter("date"));
			memo.setContent(req.getParameter("content"));
		}
		return memo;
		
//		MemoVO memo = new MemoVO();
//		memo.setWriter(req.getParameter("writer"));
//		memo.setDate(req.getParameter("date"));
//		memo.setContent(req.getParameter("content"));
		
//		try(
//				BufferedReader br = req.getReader();
//				){
//			String tmp = null;
//			StringBuffer strJson = new StringBuffer();
//			while((tmp=br.readLine()) != null) {
//				strJson.append(tmp);
//			}
//			ObjectMapper mapper = new ObjectMapper();
//			memo = mapper.readValue(strJson.toString(), MemoVO.class);
//			
//		}catch(IOException e) {
//			
//		}
//		return memo;
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
