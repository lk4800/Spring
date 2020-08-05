package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller 
public class SampleController4 {
	
	@RequestMapping("/doE") //doE 매핑주소 등록
	public String doE(RedirectAttributes rttr) {
		rttr.addFlashAttribute("msg", "saven");
		/* 백엔드 서버에서 다른 매핑주소로 이동할 때 msg키 이름에 saven값을 담아서 전달한다.
		 * 서버상에서 실행되기 때문에 웹브라우저 주소창에 노출되지 않는다
		 * 보안상 좋다*/
		
		return "redirect:/doF"; 
		// 브라우저 주소창에서 /doE매핑주소가 실행되면 다른 매핑주소
		// /doF로 이동해서 주소창 매핑주소가 /doE에서 /doF로 변경된다
	} //doE
	
	@GetMapping("/doF") //doF 매핑주소 등록, @GetMapping은 get방식인 경우만 실행
	public void doF(@ModelAttribute("msg") String name) {
		//리턴타입이 없는 void형이면 매핑주소 doF가 jsp파일명이 된다
		//msg파라미터로 전달된 값을 @ModelAttribute("msg")에서 가져와
		//매개변수 name에 저장한다
		System.out.println("msg파라미터로 전달된 값 : "+name);
	} //doF
}
