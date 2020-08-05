package net.abc.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler  implements AccessDeniedHandler{
/* 1.403접근제한이 된 경우 스프링 api에서 제공하는 인터페이스 
 * AccessDeniedHandler를 구현해서 이 에러를 처리한다
 * 2.HttpServletRequest 서블릿 api는 사용자 폼에서 입력한 정보를 서버로 가져옴
 * 3.HttpServletResponse 서블릿 api는 서버의 정보를 사용자 웹브라우저에 응답하는 역할을 한다 */
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		//조상 인터페이스의 추상메서드가 오버라이딩 되었다.
		System.out.println("Access Denied Handler");
		System.out.println("Redirect..");
		response.sendRedirect("/accessError"); //accessError매핑주소로 이동
	}

}
