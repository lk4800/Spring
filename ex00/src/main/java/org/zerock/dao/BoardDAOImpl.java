package org.zerock.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zerock.vo.BoardVO;

/*
 * mybatis 쿼리문 수행 메서드 종류
 * 	1. insert() : 레코드 저장
 *  2. delete() : 레코드 삭제
 *  3. update() : 레코드 수정
 *  4. selectList() : 하나 이상의 레코드를 검색해서 컬렉션 List로 반환
 *  5. selectOne() : 단 한개의 레코드만 반환
 */

@Repository //스프링에 Model DAOImpl이라는것을 알려준다
public class BoardDAOImpl implements BoardDAO {

	@Autowired //자동의존성 DI 설정
	private SqlSession sqlSession; //mybatis 쿼리문 수행객체 생성

	@Override
	public void insertBoard(BoardVO b) {
		this.sqlSession.insert("b_in",b);
		/*mybatis 쿼리문 수행 메소드)
		  1.insert()메소드는 레코드 저장, b_in은 board.xml에서 설정할 유일 아이디명 */
	} //게시물 저장

	@Override
	public int getTotalCount() {
		return this.sqlSession.selectOne("b_count");
		
		/* 1.mybatis에서 selectOne()메서드는 단 한개의 레코드만 반환하고, b_count는
		 * 	board.xml에서 설정할 유일한 select 아이디명	
		 */
	} //총레코드 개수=총게시물수

	@Override
	public List<BoardVO> getBoardList(BoardVO b) {
		return this.sqlSession.selectList("b_list",b);
/* 1.mybatis에서 selectList()메서드는 하나이상의 레코드를 검색해서 컬렉션 List로 반환한다
 * 	b_list는 board.xml에서 설정한 유일한 select 아이디명이다 */
	} //게시물 목록
	
	@Override
	public void updateHit(int bno) {
		this.sqlSession.update("b_hit",bno);
/* 1.update()는 mybatis에서 레코드 수정메서드이다. b_hit는 board.xml에서 설정
 * 할 유일한 update 아이디명이다. 		
 */
	}//조회수 증가

	@Override
	public BoardVO getBoardCont(int bno) {
		return this.sqlSession.selectOne("b_cont",bno);
/* 1.mybatis에서   selectOne()메서드는 단 한개의 레코드값만 반환하고, b_cont는
 * board.xml 에서 설정할 유일한 select 아이디명이다. 
 */
	}//내용보기

	@Override
	public void editBoard(BoardVO b) {
		this.sqlSession.update("b_edit",b);
	}

	@Override
	public void delBoard(int bno) {
		this.sqlSession.delete("b_del",bno);
		/*
		 * mybatis 에서 delete() 는 레코드 삭제,
		 * 	b_del 은 board.xml에서 설정할 유일한 아이디명이 된다
		 */
		
	}

	@Override
	public void updateReplyCnt(int bno, int count) {
		Map<String,Object> pm = new HashMap<>(); 
		//뒷부분 <> 제네릭 타입을 생략할 수 있는것은 자바 7이히루부터 가능하다
		//키,값 쌍으로 저장하는 컬렉션 맵
		pm.put("bno", bno); //bno키이름에 번호값 저장
		pm.put("count",count);
		this.sqlSession.update("r_cnt", pm);
		//r_cnt는 board.xml에 설정 할 유일한 update id명
	}//댓글 카운트 증가
}
