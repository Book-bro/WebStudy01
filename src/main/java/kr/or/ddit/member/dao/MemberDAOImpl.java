package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemoVO;

public class MemberDAOImpl extends AbstractJDBCDAO implements MemberDAO{
	private Map<String, Statement> statementMap;
	
	
	@Override
	public int insertMember(MemberVO member) {
		
		return 0;
	}

	@Override
	public List<MemberVO> selectMemberList() {
		StringBuffer sql = new StringBuffer();
//		1.
		sql.append(" SELECT MEM_ID, MEM_NAME, MEM_MAIL, MEM_HP, MEM_ADD1, MEM_MILEAGE");
		sql.append(" FROM  MEMBER");
		
		return selectList(sql.toString(), MemberVO.class);
		
	}

	@Override
	public MemberVO selectMember(String memId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT                                                    ");
	    sql.append(" MEM_ID,MEM_PASS, MEM_NAME,                                ");
	    sql.append(" MEM_REGNO1,    MEM_REGNO2,                                ");
	    sql.append(" TO_CHAR(MEM_BIR,'YYYY-MM-DD') MEM_BIR,                    ");
	    sql.append(" MEM_ZIP,    MEM_ADD1,    MEM_ADD2,                        ");
	    sql.append(" MEM_HOMETEL,    MEM_COMTEL,    MEM_HP,                    ");
	    sql.append(" MEM_MAIL,    MEM_JOB,    MEM_LIKE,                        ");
	    sql.append(" MEM_MEMORIAL,                                             ");
	    sql.append(" TO_CHAR(MEM_MEMORIALDAY, 'YYYY-MM-DD')MEM_MEMORIALDAY,    ");
	    sql.append(" MEM_MILEAGE,                                              ");
	    sql.append(" MEM_DELETE                                                ");
		sql.append(" FROM    MEMBER                                            ");
		sql.append(" WHERE MEM_ID = ?                                          ");
		
		return selectOne(sql.toString(), MemberVO.class, memId);
	}

	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
