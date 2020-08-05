package org.zerock.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zerock.vo.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@Autowired
	private SqlSession sqlSession; //mybatis쿼리문 수행객체 sqlSession

	@Override
	public void create(MessageVO vo) {
		this.sqlSession.insert("m_in2", vo);
	//insert()는 mybatis에서 레코드 저장메서드, m_in2는 message.xml에서 설정할 유일한 insert 아이디명
	}//메시지 추가
	
	
	
}
