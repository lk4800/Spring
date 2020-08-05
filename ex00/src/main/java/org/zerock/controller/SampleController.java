package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //@Controller 애너테이션을 사용하면 해당 컨트롤러 클래스는 스프링에서
//인식하게 한다.
public class SampleController {

	@RequestMapping("doA") //doA매핑주소 등록. GET OR POST 양쪽다 실행됨.
	public void doA() {//리턴타입이 없는 void형이면 해당 매핑주소가 뷰페이지 
		//파일명이 된다. 뷰페이지 경로는 /WEB-INF/views/doA.jsp 이다.하지만
		//해당 경로에 뷰페이지 파일명이 없기 때문에 404 파일없음 에러가 발생한다.
		System.out.println("doA 매핑주소가 실행됨.");//이클립스 콘솔모드로
		//출력됨.
	}
	
	@RequestMapping("doB")
	public void doB() {
		System.out.println("doB 매핑주소가 실행됨.");
	}
}














