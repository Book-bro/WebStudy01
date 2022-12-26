package kr.or.ddit.servlet09.dao;

import java.util.List;

import kr.or.ddit.vo.DataBasePropertyVO;

public interface DataBasePropertyDAO {
	public List<DataBasePropertyVO> selectPropertyList(String propertyName);
}
