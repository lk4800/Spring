package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.vo.ProductVO;

@Controller
//@Controller 어노테이션에 의해서 스프링 컨트롤러로 작동됨
public class SampleController3 {

	@RequestMapping("/nameprice") //nameprice매핑주소 등록
	public String nameprice(Model m) {
		ProductVO p = new ProductVO("냉장고",2520000);
		m.addAttribute("p", p);
		return "shop/price" ;
		//뷰페이지 경로 => /WEB-INF/views/shop/
		//price.jsp =>shop폴더를 생성하고 price.jsp를 만든다.

	}

}
