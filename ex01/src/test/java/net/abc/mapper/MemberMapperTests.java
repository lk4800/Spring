package net.abc.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.abc.dao.MemberMapper;
import net.abc.vo.MemberVO;

@RunWith(SpringRunner.class)
//JUnit 실행클래스
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
	// root-context.xml파일을 읽어옴
})
@Log4j //로그기록
//=>db로 불러온 데이터를 출력하기 위함이래
public class MemberMapperTests {
	@Setter(onMethod_ = @Autowired)
	// setter()메서드를 통한 의존성 주입
	private MemberMapper mapper;
	
	@Test //JUnit 테스트
	public void testRead() {
		MemberVO vo = this.mapper.read("admin90");
		//admin90 아이디에 대한 회원정보와 권한을 검색
		
		log.info(vo);
		// 이클립스 콘솔에 출력
		
		vo.getAuthList().forEach(authVO -> log.info(authVO));
		// 람다식으로 아이디와 권한 목록을 이클립스 콘솔에 출력
		// forEach()메서드는 제네릭 메서드로 컬렉션에 담겨진 값authVO을 반복해줌
	}//testRead()
}
