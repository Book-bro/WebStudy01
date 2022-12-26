package kr.or.ddit.memo.dao;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemoVO;

public class FileSystemMemoDAOImplTest {

	private MemoDAO dao = FileSystemMemoDAOImpl.getInstance();
	private MemoVO memo;
	
	@Before
	public void setUp() throws Exception {
		memo = new MemoVO();
		memo.setWriter("작성자1");
		memo.setContent("내용1");
		String date = String.format("%1$ty-%1$tm-%1$td %1$tH:%1$tM:%1$tS", LocalDateTime.now()); 
		memo.setDate(date);
	}

	@Test
	public void testSelectMemoList() {
		List<MemoVO> memoList = dao.selectMemoList();
//		System.out.println(memoList);
		memoList.stream()
				.forEach(System.out::println);  //메소드 레퍼런스라고 함, 꾸준히 발전하기에 알아둬야함, 더 간편
//				.forEach(singleMemo->{
//					System.out.println(singleMemo);
//				});
	}

//	@Test
	public void testInsertMemo() {
		dao.insertMemo(memo);
	}

}
