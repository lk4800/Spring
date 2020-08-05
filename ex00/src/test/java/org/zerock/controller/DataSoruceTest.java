package org.zerock.controller;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//JUnit실행 클래스 context xml파일 읽어올때 사용
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
//root-context.xml파일경로 설정
public class DataSoruceTest {

	@Inject //자동의존성 주입
	private DataSource ds;
	
	@Test //JUnit 연습용 테스트 어노테이션
	public void testCon() throws Exception{
		try(Connection con = ds.getConnection()){
			//커넥션 풀 관라 ds로 디비 연결 con을 생성
			System.out.println(con);
		}catch(Exception e) {e.printStackTrace();}
	}
}
