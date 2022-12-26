package kr.or.ddit.vo;

import java.io.Serializable;
// 전송이나 저장을 위해서 직렬화를 사용함
// 직렬화를 하려면 그 객체가 가지고 있는 프로퍼티도 직렬화해야함
// 하나만 직렬화 할 수 없다면 직렬화할 수 없음 => 만약 직렬화 할 수 없는 프로퍼티가 있다면 제외하면 됨
// transient 사용하여 배제시킴
// : 민감한 데이터에 사용, 보호해야하는 데이터가 있을 경우 사용

public class MemoVO implements Serializable{
//   private transient Object prop;
//   public Object getProp() {
//      return prop;
//   }
//   public void setProp(Object prop) {
//      this.prop = prop;
//   }
   
   private Integer code;
   private String writer;
   private String content;
   private String date;

   public Integer getCode() {
      return code;
   }

   public void setCode(Integer code) {
      this.code = code;
   }

   public String getWriter() {
      return writer;
   }

   public void setWriter(String writer) {
      this.writer = writer;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }

   @Override
   public String toString() {
      return "MemoVO [code=" + code + ", writer=" + writer + ", content=" + content + ", date=" + date + "]";
   }

}