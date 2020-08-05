package org.zerock.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	/* Get방식으로 접근하는 매핑주소를 처리 /은 루트 매핑 주소
	 * 루트매핑주소는 http://localhost:8002/controller/ */
	public String home(Locale locale, Model model) {
		//Locale은 특정 지리적, 국가적, 또는 문화적, 시간대를 지역에 따라 나타내게 한다
		logger.info("Welcome home! The client locale is {}.", locale);
		//이클립스 콜솔모드에 출력
		Date date = new Date(); //날짜 객체를 생성
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		//날짜 포맷객체 생성, 정적메서드 인자값으로 날짜스타일, 시간스타일, 해당국가 날짜/시간대
		//locale
		String formattedDate = dateFormat.format(date);
		//날짜객체를 문자열로 반환
		model.addAttribute("serverTime", formattedDate );
		//serverTime키이름에 날짜 문자열 값을 저장
		return "home";
		//뷰페이지 경로 => /WEB-INF/views/home.jsp
	}

	//아작스 댓글 뷰페이지
	@RequestMapping("/test") //test매핑주소 등록
	public ModelAndView test() {
		ModelAndView m = new ModelAndView();
		m.setViewName("reply"); //뷰페이지 경로 => /WEB-INF/views/reply.jsp
		return m;
	}
}
























