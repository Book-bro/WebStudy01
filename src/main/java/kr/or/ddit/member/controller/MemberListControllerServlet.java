package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberList.do")
public class MemberListControllerServlet extends HttpServlet{
   private MemberService service = new MemberServiceImpl();
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
      List<MemberVO> memberList = service.retrieveMemberList();
      req.setAttribute("memberList", memberList);
      String viewName = "/WEB-INF/views/member/memberList.jsp";
//      5.
      if(viewName.startsWith("redirect:")) {
         viewName = viewName.substring("redirect:".length());
         resp.sendRedirect(req.getContextPath() + viewName);
      }else {
         req.getRequestDispatcher(viewName).forward(req, resp);
      }
   }
}