package net.abc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample") //컨트롤러 자체 매핑주소 /sample등록
public class SampleController {

	@GetMapping("/all") // all매핑주소 등록=>get 으로 접근하는 매핑주소 처리
	//로그인 하지 않은 사용자도 접근가능
	public void doAll() {
/* 1.리턴타입이 없는 void형이면 매핑주소 all이 뷰페이지 파일명이 된다.
 * 2. 뷰페이지 경로 :/WEB-INF/views/sample/all.jsp */
		System.out.println("do all can access everybody");
	}//doAll()
	
	@GetMapping("/member") // member매핑주소등록
    //로그인 한 사용자들만 접근할 수 있는 매핑주소
	public void doMember() {//뷰페이지 파일명은 member.jsp
		System.out.println("logined member");
	}//doMember()
	
	@GetMapping("/admin") //로그인 한 사용자들 중에서 관리자 권한을 가진 사용자만
	//접근할 수 있는 매핑주소
	public void doAdmin() {
		System.out.println("admin only");
	}//doAdmin()
}






