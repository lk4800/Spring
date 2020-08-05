package net.abc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommonController {

	@GetMapping("/accessError") //accessError매핑주소 등록
	public void accessDenied(Model m) {
		//리턴타입이 없는 void형이면 매핑주소 accessError가 뷰페이지 파일명이 된다
		System.out.println("access Deined");
		m.addAttribute("msg","Access Denied"); 
		//msg 키이름에 access Denied문자열을 저장함 해당 뷰페이지에서 
		//msg키이름을 EL로 참조해서 값을 가져온다
	}//accessError
	
	@GetMapping("/customLogin") //GET방식으로 접근할때 처리
	//매핑주소 customLogin
	public void loginInput(String error,String logout,Model model) {
		//error에는 에러가 발생했을때, logout은 로그아웃 했을때, 리턴타입이 없는형이면
		//매핑주소가 jsp파일명이 된다. 뷰페이지명은 customLogin.jsp
		System.out.println("error:"+error);
		System.out.println("logout:"+logout);
		
		if(error != null) { //에러가 발생했을때 실행
			model.addAttribute("error", "Login Error Check Your Account");
			//error키이름에 값을 저장
		}
		if(logout != null) {
			model.addAttribute("logout", "Logout!");
		}
	}//loginInput()

	@GetMapping("/customLogout") //GET방식으로 접근하는 매핑주소 처리
	//customLogout 매핑주소 등록
	public void logoutGET() {}
	//리턴타입이 없는 void형이면 매핑주소인 customLogout이 jsp뷰페이지 파일명이 된다
	
	@PostMapping("/customLogout") //post방식으로 접근하는 매핑주소 처리
	public void logoutPost() {} 
	//각각 매핑주소가 같아도 method방식이 get,post에 의해서 구분된다
	
}
