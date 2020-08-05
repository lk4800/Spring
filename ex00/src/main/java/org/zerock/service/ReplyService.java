package org.zerock.service;

import java.util.List;

import org.zerock.vo.ReplyVO;

public interface ReplyService {

	void addReply(ReplyVO vo);

	List<ReplyVO> listReply(int bno);

	void updateReply(ReplyVO vo);

	void delete(int rno);

}
