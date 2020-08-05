package net.abc.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.abc.vo.MemberVO;

@Repository //스프링에 모델DAO를 등록
public class MemberMapperDAOImpl implements MemberMapper {

	@Autowired
	private SqlSession sqlSession; //mybatis 쿼리문 수행객체 sqlSession생성
	
	@Override
	public MemberVO read(String userid) {
		
		return this.sqlSession.selectOne("read",userid);
		/* 1.mybatis는 ORM 프레임워크라고 한다. 이 mybatis의 조상프레임웍이 ibatis이다
		 * ibatis는 몇년전 온라인 기술지원이 단종된 프레임웍이다. mybatis나 ibatis는 쿼리문을
		 * xml태그로 다루는 프레임웍이다. (스프링은 아님)
		 * ORM프레임웍에서 mybatis, ibatis외 하이버네이트라는 것도 있다
		 * 
		 * 2.selectOne()메서드는 단 한개의 레코드만을 검색해준다 read는 member.xml에서
		 * 설정할 select 아이디명이다
		 * */
	}//아이디를 기준으로 회원정보 검색

}
