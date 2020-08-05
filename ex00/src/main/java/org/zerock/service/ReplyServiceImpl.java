package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dao.BoardDAO;
import org.zerock.dao.ReplyDAO;
import org.zerock.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired//의존성 주입
	private ReplyDAO replyDAO; 

	@Autowired
	private BoardDAO boardDao;

	@Transactional //트랜잭션 적용
	@Override
	public void addReply(ReplyVO vo) {
		this.replyDAO.addReply(vo); //댓글추가
		this.boardDao.updateReplyCnt(vo.getBno(),1);
		//게시판 번호값을 구해서 댓글이 추가되면 댓글카운터 1증가
	} //댓글이 추가되면 댓글 카운터는 증가 => AOP를 통한 트랜잭션 적용대상

	@Override
	public List<ReplyVO> listReply(int bno) {
		return replyDAO.listReply(bno);
	}

	@Override
	public void updateReply(ReplyVO vo) {
		this.replyDAO.updateReply(vo);
	}

	@Override
	public void delete(int rno) {
		int bno = this.replyDAO.getBno(rno);
		//먼저 댓글번호에 해당하는 게시물 번호값을 구함
		this.replyDAO.delete(rno);
		this.boardDao.updateReplyCnt(bno, -1);
		//게시물 삭제시 카운트도 1down
	}//댓글이 삭제되면 댓글수를 -1로 카운터 => AOP를 통한 트랜잭션 적용대상
}

