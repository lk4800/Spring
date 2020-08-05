package net.abc.security;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;

@RunWith(SpringJUnit4ClassRunner.class) //스프링 JUnit 테스트 실행 클래스
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"
})
/* 1.root-context.xml파일에는 스프링 jdbc,mybatis관련 
 * xml,aop+트랜잭션,모델DAOImpl,서비스가 들어간다
 * 2.security-context.xml에는 스프링 시큐리티 관련 xml파일이 들어간다
 * 이 2개 파일을 동시에 읽어들임
 * 3.src/main/webapp경로는 이클립스에서 보는 경로이고 실제 서버에서 작동되는 경로는 아니다
 * */

public class MemberTests {

	@Setter(onMethod_ = @Autowired) //setter() 메서드 의존성 (DI)주입
	private PasswordEncoder pwencoder; //비번 암호화
	
	@Setter(onMethod_ = @Autowired)
	private DataSource ds; //커넥션 풀 관리 ds
	
	@Test //JUnit 테스트
	public void testinsertMember() {
		String sql = "insert into tbl_member2 (userid,userpw,username"
				+ ") values(?,?,?)";
		for(int i=0; i<100; i++) {
			Connection con = null; //오라클 연결 con
			PreparedStatement pstmt = null; //쿼리문 수행 pstmt
			
			try {
				con = ds.getConnection(); //디비 연결 con
				pstmt = con.prepareStatement(sql); //쿼리문 수행 pstmt생성
				pstmt.setString(2, pwencoder.encode("pw"+i));
				//쿼리문의 2번째 물음표에 비번 암호화
				if(i<80) { //0~79
					pstmt.setString(1, "user"+i); //아이디가 user0~user79
					pstmt.setString(3, "일반사용자"+i);
					//쿼리문 3번째 물음표에 일반사용자 0~79
				}else if(i<90) { //80~89
					pstmt.setString(1, "manager"+i); //manager80~89
					pstmt.setString(3, "운영자"+i); //운영자80~운영자89
				}else { //90~99
					pstmt.setString(1, "admin"+i); //admin90~99
					pstmt.setString(3, "관리자"+i); //관리자90~99
				}//if~else~if
				
				pstmt.executeUpdate(); //insert쿼리문 수행
			}catch(Exception e) {e.printStackTrace();}
			finally {
				try {
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				}catch(Exception e) {e.printStackTrace();}
			}
		}//0부터 99까지 반복
	}//회원저장
	
	@Test
	public void testInsertAuth() {
		String sql = "insert into tbl_member2_auth(userid,auth) "
				+"values(?,?)";
		
		for(int i=0; i<100; i++) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				
				if(i < 80) {
					pstmt.setString(1,"user"+i);
					pstmt.setString(2,"ROLE_USER");//일반 사용자
				}else if(i<90) {
					pstmt.setString(1,"manager"+i);
					pstmt.setString(2,"ROLE_MEMBER");//회원인 경우
				}else {//90~99
					pstmt.setString(1,"admin"+i);
					pstmt.setString(2,"ROLE_ADMIN");//관리자
				} //if else if
				pstmt.executeUpdate();
			}catch(Exception e) {e.printStackTrace();}
			finally {
				try {
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				}catch(Exception e) {e.printStackTrace();}
			}
			
		}
	}
}
