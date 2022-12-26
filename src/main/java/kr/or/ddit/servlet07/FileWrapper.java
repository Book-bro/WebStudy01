package kr.or.ddit.servlet07;

import java.io.File;
import java.util.Optional;

import javax.servlet.ServletContext;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FileWrapper {
	@JsonIgnore
	private File adaptee;

	public FileWrapper(File adaptee, ServletContext application) {
		super();
		this.adaptee = adaptee;
		this.name = adaptee.getName(); // ex) test.hwp
		this.contentType = Optional.ofNullable(application.getMimeType(name))
									.orElse("application/octet-stream");
				
	}
	
	private String name;
	private String contentType;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
}
