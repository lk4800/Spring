package org.zerock.dao;

import java.util.List;

import org.zerock.vo.BoardVO;

public interface BoardDAO {

	void insertBoard(BoardVO b);

	int getTotalCount();

	List<BoardVO> getBoardList(BoardVO b);

	void updateHit(int bno);

	BoardVO getBoardCont(int bno);

	void editBoard(BoardVO b);

	void delBoard(int bno);

	void updateReplyCnt(int bno, int count);

	
}
