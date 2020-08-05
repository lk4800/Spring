package org.zerock.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.dao.MemberDAO;
import org.zerock.vo.Member7VO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
//root-context.xml파일 위치 경로지정=>스프링 JDBC,mybatis설정, 모델DAOImpl,
public class MemberDAOTest {

	@Inject //자동의존성 주입
	private MemberDAO memberDao;
	
	@Test //JUnit 테스트
	public void testInsert() throws Exception{
		Member7VO m = new Member7VO();
		m.setUserid("kkkk"); //회원아이디 저장
		m.setUserpw("8888"); //비번저장
		m.setUsername("홍길동"); //회원이름 저장
		m.setEmail("hong@naver.com"); //회원 이메일 저장
		
		this.memberDao.insertMember(m);
	}
	
}
