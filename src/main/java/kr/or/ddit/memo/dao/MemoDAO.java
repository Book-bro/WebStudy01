package kr.or.ddit.memo.dao;

import java.util.List;

import kr.or.ddit.vo.MemoVO;

public interface MemoDAO {
   public List<MemoVO> selectMemoList();// 모든메모 가져오기
   public int insertMemo(MemoVO memo); // count
   public int updateMemo(MemoVO memo);
   public int deleteMemo(int code);
   
}