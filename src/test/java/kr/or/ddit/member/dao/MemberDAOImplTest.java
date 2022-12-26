package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOImplTest {

	private MemberDAO dao = new MemberDAOImpl();
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInsertMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectMemberList() {
		List<MemberVO> memberList = dao.selectMemberList();
		memberList.stream()
				.forEach(System.out::println);
	}

	@Test
	public void testSelectMember() {
		MemberVO member = dao.selectMember("a001");
		System.out.println(member);
		member = dao.selectMember("12345a");
		assertNull(member);
	}

	@Test
	public void testUpdateMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteMember() {
		fail("Not yet implemented");
	}

}
