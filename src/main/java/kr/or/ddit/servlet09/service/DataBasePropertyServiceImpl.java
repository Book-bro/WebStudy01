package kr.or.ddit.servlet09.service;

import java.util.List;

import kr.or.ddit.servlet09.dao.DataBasePropertyDAO;
import kr.or.ddit.servlet09.dao.DataBasePropertyDAOImpl;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyServiceImpl implements DataBasePropertyService{
	private DataBasePropertyDAO dao = new DataBasePropertyDAOImpl();
	
	@Override
	public List<DataBasePropertyVO> retrievePropertyList(String propertyName) {
		List<DataBasePropertyVO> list = dao.selectPropertyList(propertyName);
		list.stream()
			.forEach(System.out::println); //메소드 레퍼런스, 함수의 파라미터로 어떤메소드만 쓰겠다
		return list;
	}
	
}
