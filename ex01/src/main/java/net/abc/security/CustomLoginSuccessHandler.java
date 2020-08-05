package net.abc.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
/* 1.로그인 성공후 특정한 동작을 수행하도록 하기 위해서 스프링 시큐리티에서 제공하는
 * AuthenticationSuccessHandler 인터페이스를 구현상속 받는다
 * 2.만들어진 CustomLoginSuccessHandler는 security-context.xml에 
 * 빈아이디로 등록해야 사용할 수 있다
 * */
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		System.out.println("Login Success");
		List<String> roleNames = new ArrayList<>(); 
		//문자열 값만 저장할 수 있는 컬렉션 String 제네릭 roleNames 생성
		auth.getAuthorities().forEach(authority ->{
			//forEach()제네릭 메서드로 반복
			roleNames.add(authority.getAuthority());
		//권한을 가져와(getAuthority) 컬렉션에 추가
		//컬렉션 제네릭(roleName)에  추가(add())
		});
		/* 1.자바 8에서 추가된 람다식으로 로그인 한 사용자에게 부여한 권한을 Authentication을 이용해서
		 * 사용자가 가진 모든 권한을 문자열로 체크해서 권한을 가져와 컬렉션에 추가한다.*/

		System.out.println("권한 출력:"+roleNames);

		if(roleNames.contains("ROLE_ADMIN")) { 
			//로그인한 사용자중에서 관리자 권한을 가졌다면 실행
			response.sendRedirect("/sample/admin");
			// /sample/admin매핑주소로 이동
			return; //메서드 종료
		}//if
		if(roleNames.contains("ROLE_MEMBER")) {
			//로그인 한 사용자 중에서 일반회원이 실행 
			//contains()메서드는 컬렉션에 메서드 인자값이 포함되어있다면 true
			response.sendRedirect("/sample/member");
			return; //메서드 종료
		}
		response.sendRedirect("/");
	}
}
