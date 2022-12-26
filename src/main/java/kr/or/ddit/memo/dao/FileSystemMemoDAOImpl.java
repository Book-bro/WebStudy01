package kr.or.ddit.memo.dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.MemoVO;

public class FileSystemMemoDAOImpl implements MemoDAO {
   private static FileSystemMemoDAOImpl instance;

   public static FileSystemMemoDAOImpl getInstance() {
      if (instance == null)
         instance = new FileSystemMemoDAOImpl(); // 나밖에 만들지 못하는
      return instance; // 그대로 반환하라
   }

   // 매체 만들기 -> 파일로 만들기
   private File dataBase = new File("d:/memos.dat");
   private Map<Integer, MemoVO> memoTable; // 복원해서 여기다 만들기

   private FileSystemMemoDAOImpl() {
	   try(
		   FileInputStream fis = new FileInputStream(dataBase);
		   BufferedInputStream bis = new BufferedInputStream(fis);
		   ObjectInputStream ois = new ObjectInputStream(bis);
	   ){
		   memoTable = (Map<Integer, MemoVO>) ois.readObject();
	   }catch(Exception e) {
		   System.err.println(e.getMessage());
		   this.memoTable = new HashMap<>();
	   }
   }

   // 반환할 때는 list 타입으로
   @Override
   public List<MemoVO> selectMemoList() {
      return new ArrayList<>(memoTable.values());
   }

   // memo가 코드를 가지고 있다는 전제하에
   @Override
   public int insertMemo(MemoVO memo) {
      // 코드값 생성 - 기존메모 코드값, 일련번호로, 가장 큰 값을 찾음 + 1
      // 만약 한 건도 없었을 경우 0을 먼저 만들어야함
      // option, striming

      // int형으로 바꿈 -> 제일 큰 값 찾기
      int maxCode = memoTable.keySet().stream()
					    		  .mapToInt(key -> key.intValue())
					    		  .max()
					    		  .orElse(0);
      memo.setCode(maxCode + 1);
      memoTable.put(memo.getCode(), memo);
      serializeMemoTable();
      return 1; // 성공했을 경우
   }

   private void serializeMemoTable() {
	   try(
		   FileOutputStream fos = new FileOutputStream(dataBase);
		   ObjectOutputStream oos = new ObjectOutputStream(fos);
	   ){
		   oos.writeObject(memoTable);
	   }catch (Exception e) {
		   throw new RuntimeException(e); //예외종류 변경시 원본예외 상태 유지
	}
   }

@Override
public int updateMemo(MemoVO memo) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int deleteMemo(int code) {
	// TODO Auto-generated method stub
	return 0;
}
}









