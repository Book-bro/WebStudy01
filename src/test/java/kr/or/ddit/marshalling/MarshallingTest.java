package kr.or.ddit.marshalling;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.developer.Serialization;

public class MarshallingTest {

   Object target;
   ObjectMapper mapper;
   
   @Before
   public void setUp() throws Exception {
      System.out.println("before");
      target = new LinkedHashMap<>();
      ((Map)target).put("key1", "SAMPLE");
      ((Map)target).put("key2", new Boolean(true));
      ((Map)target).put("key3", new Double(2.3d));
      mapper = new ObjectMapper();
   }   

   @After
   public void tearDown() throws Exception {
      System.out.println("after");
   }

   @Test
   public void test1() throws JsonProcessingException {
//     1. native -> json : marshalling - writeXXX
	   String json = mapper.writeValueAsString(target);
	   System.out.println(json);
//     2. json -> native : unmarshalling - readXXX
	   Map<String, Object> map = mapper.readValue(json,Map.class);
	   System.out.println(map);
   }   
   
   @Test
   public void test2() throws IOException {
//     1. native -> json : marshalling - writeXXX
//	   String json = mapper.writeValueAsString(target);  //마샬링
//	   1-1. Serialization : 객체의 상태를 전송이나 저장이 가능한 형태로 스트림화(바이트 배열로 변환)
	   File file = new File("d:/test.json");
	   try(
		   FileWriter writer = new FileWriter(file);
		   BufferedWriter bw = new BufferedWriter(writer);
	   ){
//		   bw.write(json); // 직렬화
		   mapper.writeValue(bw, target);
	   }
//	   2-2. deserialization : 전송이나 저장된 매체로부터 객체의 상태를 복원하는 과정.
	   try(
		   // Closable 객체(resource) 선언문.
		   FileReader reader = new FileReader(file);
		   BufferedReader br = new BufferedReader(reader);
	   ){
//		   String temp = null;
//		   StringBuffer readedJson = new StringBuffer();
//		   while((temp=br.readLine())!=null){
//			   readedJson.append(temp);
//		   }
//     2. json -> native : unmarshalling - readXXX
//		   Map<String, Object> map = mapper.readValue(readedJson.toString(),Map.class);
		   Map<String, Object> map = mapper.readValue(br, Map.class);
		   System.out.println(map);
	   }
   }   
}




