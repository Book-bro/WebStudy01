package kr.or.ddit.servlet04.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import kr.or.ddit.servlet01.DescriptionServlet;

public class PropertiesServiceImpl implements PropertiesService {

	@Override
	public Properties retrieveData() {
		Properties properties = new Properties();
		try(
			InputStream is = DescriptionServlet.class.getResourceAsStream("/kr/or/ddit/props/DataStore.properties");
		){
			properties.load(is);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		return properties;
	}

}
