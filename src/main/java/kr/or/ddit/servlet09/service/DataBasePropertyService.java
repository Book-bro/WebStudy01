package kr.or.ddit.servlet09.service;

import java.util.List;

import kr.or.ddit.vo.DataBasePropertyVO;

public interface DataBasePropertyService {
	public List<DataBasePropertyVO> retrievePropertyList(String propertyName);
}
