package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller //@Controller 애너테이션으로 스프링 컨트롤로 클래스로 작동된다.
public class SampleController2 {

	@GetMapping("doC") //요즘 들어와서 @GetMapping애너테이션을 사용하면 get
	//방식으로만 접근 가능함. doC url-pattern  매핑주소 등록
	public String doC(@ModelAttribute("msg2") String msg) {
/* @ModelAttribute("msg2")는 doC?msg2=값 형태의 웹브라우저 주소창에 노출되는
 * get방식으로 msg2 네임피라미터 이름에 값을 담아서 전달한다.		
 */
		return "result";// 뷰페이지 경로=>/WEB-INF/views/result.jsp
		//문자열 반환값 result가 jsp파일명이 된다.그래서 result.jsp
	}
}








