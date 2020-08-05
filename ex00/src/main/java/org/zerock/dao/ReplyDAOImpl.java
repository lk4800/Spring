package org.zerock.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zerock.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void addReply(ReplyVO vo) {
		this.sqlSession.insert("r_in",vo);
		//r_in 은 reply.xml 에서 설정할 유일한 insert 아이디명
	}//댓글 저장

	@Override
	public List<ReplyVO> listReply(int bno) {
		return this.sqlSession.selectList("r_list",bno);
		// mybatis에서 selectList()는 하나 이상의 레코드를 검색해서 컬렉션 List 로 반환
		// r_list 는 reply.xml 에서 설정할 유일한 아이디명
	}//댓글 목록

	@Override
	public void updateReply(ReplyVO vo) {
		this.sqlSession.update("r_edit",vo);
		//mybatis에서 update()는 레코드 수정메서드, r_edit은 reply.xml에서 설정할
		//update 아이디명
	}//댓글수정

	@Override
	public void delete(int rno){ //deleteReply도 가능
		this.sqlSession.delete("r_del",rno);
		//mybatis에서 delete()는 레코드 삭제, r_del은 reply.xml에서 설정할 delete아이디명
	}//댓글삭제

	@Override
	public int getBno(int rno) {
		return this.sqlSession.selectOne("r_bno",rno);
		//mybatis에서 selectOne()메서드는 단 한개의 레코드만 검색
		//r_bno는 reply.xml에서 설정할 유일한 select 아이디명
	}//댓
	
	
	
}
