package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dao.MessageDAO;
import org.zerock.dao.PointDAO;
import org.zerock.vo.MessageVO;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDao;
	
	@Autowired
	private PointDAO pointDao;

	@Transactional //데이터 일치성을 유지하기 위해서 트랜잭션 적용
	@Override
	public void addMessage(MessageVO vo) {
// <-떠있는건 이클립스 개발툴에서 왼쪽화면에 보이는 위아래 화살표 방향은
			//가장 광범위 하게 적용되는 aop around타입을 뜻한다
		
		this.messageDao.create(vo); //메시지 추가
		this.pointDao.updatPoint(vo.getSender(),10);
		//메시지 보낸사람에게 포인트 점수 10점 up
	}
} //AOP를 통한 트랜잭션 적용
