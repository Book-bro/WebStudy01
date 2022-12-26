package kr.or.ddit.servlet09.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyDAOImpl implements DataBasePropertyDAO {

	@Override
	public List<DataBasePropertyVO> selectPropertyList(String propertyName) {
		// driver class loading : oracle.jdbc.driver.OracleDriver
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION ");
		sql.append(" FROM DATABASE_PROPERTIES ");
		if(StringUtils.isNoneBlank(propertyName)) {
			sql.append(" WHERE PROPERTY_NAME = ? ");
		}
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
//			Statement stmt = conn.createStatement();
		){
//			ResultSet rs = stmt.executeQuery(sql.toString());
			if(StringUtils.isNoneBlank(propertyName)) {
				pstmt.setString(1, propertyName);
			}
			
			ResultSet rs = pstmt.executeQuery();
			List<DataBasePropertyVO> list = new ArrayList<>();
			while(rs.next()){
				DataBasePropertyVO vo = new DataBasePropertyVO();
				list.add(vo);
				vo.setPropertyName(rs.getString("PROPERTY_NAME"));
				vo.setPropertyValue(rs.getString("PROPERTY_VALUE"));
				vo.setDescription(rs.getString("DESCRIPTION"));
			}
			return list;
		}catch(SQLException e){
			throw new RuntimeException(e); //예외 바꿔주기
		}
	}

}
