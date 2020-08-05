package org.zerock.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.service.BoardService;
import org.zerock.vo.BoardVO;

/*
 *  get 방식인 경우(주소창에 주소가 그대로 노출된다)
 *    1. 자바스크립트 location 객체에 의해서 이동하는 경우
 *    2. 하이퍼링크로 이동하는 경우
 *    3. 서블릿의 response.sendRedirect(경로) 로 이동하는 경우
 *    4. 폼태그의 method="get" 인 경우 (form 태그의 method속성은 생략하면 디폴트값이 get)
 *    5. 아작스ajax 에서 get으로 설정할 경우
 *    6. 스프링의 redirect로 이동하는 경우
 *    
 *  post 방식인 경우
 *    1.폼태그의 method=post 인 경우
 *    2. 아작스ajax post방식인 경우
 */

@Controller //스프링에서 컨트롤러 클래스를 만들때는 @Controller를 사용한다.
@RequestMapping("/board/*") //컨트롤러 클래스 자체에 매핑주소를 등록
public class BoardController {

	@Autowired //자동 의존성 주입
	private BoardService boardService;
	
	//스프링 게시판 글쓰기 폼으로 이동
	@RequestMapping(value="/board_write",
			method=RequestMethod.GET) //GET으로 접근하는 매핑주소 
	//board_write실행
	public void board_write() {//리턴타입이 없는 void형이면 매핑주소인 
		//board_write가 뷰페이지 파일명인 board_write.jsp가 된다.
		//뷰페이지 경로=>/WEB-INF/views/board/board_write.jsp
	}//board_write()
	
	//글저장
	@RequestMapping(value="/board_write_ok",
			method=RequestMethod.POST) //post방식으로 접근하는 매핑주소
	//board_write_ok을 처리한다. 폼태그 액션속성을 생략하면 이전 매핑주소인
	//board_write가 기본매핑주소가 된다.
 public String board_write_ok(BoardVO b,RedirectAttributes rttr) {
 /* BoardVO b는 board_write.jsp의 네임피라미터이름과 BoardVO빈클래스 변수명이
  * 같으면  b에 작성자,제목,내용이 저장되어 있다. 		
  */
		this.boardService.insertBoard(b);//게시물 저장
		rttr.addFlashAttribute("msg","SUCCESS");
/* msg키이름에 SUCCESS문자열을 담아서 다른 매핑주소로 전달한다. 서버상에서 실해되기 때
 * 	문에 브라우저 주소창에 노출되지 않는다.보안성 좋다.	
 */
		return "redirect:/board/board_list";// 다른 매핑주소인 /board/
		//board_list로 이동
	}//board_write_ok()
	
	//목록보기
	@GetMapping("/board_list") //get방식인 경우만 board_list매핑주소처리
	public String board_list(Model m,HttpServletRequest request,
			@ModelAttribute BoardVO b) throws Exception{
/* @ModelAttribute BoardVO b는  BoardVO b객체 생성효과가 난다. 		
 */
		/* 페이징=> 쪽나누기 코드 추가*/
		int page=1;//현재 쪽번호
		int limit=10;//한페이지에 보여지는 목록개수
		if(request.getParameter("page") != null) {
//get으로 전달된 쪽번호가 있는 경우 실행=>board_list?page=쪽번호
page=Integer.parseInt(request.getParameter("page"));//전달받은 페이지
//번호(쪽번호)를 정수숫자로 바꿔서 저장시킴
		}
		b.setStartrow((page-1)*10+1);//시작행 번호
		b.setEndrow(b.getStartrow()+limit-1);//끝행번호
		
	    int totalCount = this.boardService.getTotalCount();
	    //총게시물수 
	    List<BoardVO> blist=this.boardService.getBoardList(b);
	    //게시물 목록을 가져옴
	    
	    /*페이징 연산*/
	    //총페이지 수
	    int maxpage=(int)((double)totalCount/limit+0.95);
	    //현재페이지에 보여질 시작 페이지
	    int startpage=(((int)((double)page/10+0.9))-1)*10+1;
	    //현재페이지에 보여질 마지막 페이지
	    int endpage=maxpage;
	    if(endpage>startpage+10-1) endpage=startpage+10-1;
	    
	    m.addAttribute("totalCount",totalCount);
	    //Model은 스프링 api로 totalCount 키이름에 총 레코드 수를 저장. 
	    //서블릿 자바로 표현하면 request.setAttribute("totalCount",
	    //totalCount)와 같은 기능을 한다.	 
	    request.setAttribute("blist",blist);//blist키이름에 목록을 저장
	    m.addAttribute("startpage",startpage);//시작페이지
	    m.addAttribute("endpage",endpage);//마지막페이지
	    m.addAttribute("maxpage",maxpage);//최대페이지
	    m.addAttribute("page",page);//page키이름에 현재쪽번호 저장
	    
		return "./board/board_list";
		//뷰페이지 경로=>/WEB-INF/views/board/board_list.jsp
	}//board_list()
	
    //게시물 내용보기+조회수증가
	@RequestMapping("/board_cont") //get or post 방식일때 모두 실행
	public ModelAndView board_cont(@RequestParam("bno") int bno,
			HttpServletRequest request)
	throws Exception{
/* @RequestParam("bno") int bno를 서블릿 변경하면 int bno=
 * Integer.parseInt(request.getParameter("bno"));와 같은 기능이다. 		
 */
		int page=Integer.parseInt(request.getParameter("page"));
		//get으로 전달된 쪽번호를 정수숫자로 바꿔서 변수에 저장
	//	this.boardService.updateHit(bno);//조회수 증가
		BoardVO b=this.boardService.getBoardCont(bno);//번호에 해당하
		//는 오라클 디비 레코드값을 가져오고, 조회수 증가
		
		ModelAndView cm=new ModelAndView();
		/* ModelAndView 스프링 api특징)
		 * 1.addObject(키이름, 값) 키이름에 값을 저장할 수 있다.
		 * 이것은 서블릿의 request.setAttribute(키이름, 값)과 기능이 유사하다
		 * 2.setViewName()메서드 인자값으로 뷰페이지 경로도 설정할 수있고,
		 * 매핑주소 경로도 redirect:/설정할 수 있다.
		 * 3.ModelAndView 생성자 인자값으로 뷰페이지 경로도 설정 가능하고,
		 * 매핑주소 경로도 redirect:/로 설정 할 수 있다. */
		
		cm.addObject("b",b);//b키이름에 b 객체를 저장
		cm.addObject("page",page);//page키이름에 쪽번호 저장
		cm.setViewName("./board/board_cont");//뷰페이지 경로(뷰리졸브
		//경로) => /WEB-INF/views/board/board_cont.jsp
		return cm;
	}//board_cont()
	
	//게시판 수정폼
	@RequestMapping("/board_edit")
	public ModelAndView board_edit(int bno, int page) throws Exception{
	/* int bno, int page라고 매개변수만 사용해도 get으로 전달된 번호값과 쪽번호를 받을 수 있다
	 * 이것은 스프링의 @RequestParam("bno") int bno,
	 * 서블릿의 int page = Integer.parseInt(request.getParameter("page))와 기능이 같다 */
		
		BoardVO eb = this.boardService.getBoardCont(bno);
		//번호에 해당하는 디비 레코드를 가져온다
		ModelAndView em = new ModelAndView("board/board_edit");
		//생성자 인자값으로 뷰페이지 경로를 설정함 => /WEB-INF/views/board/board_edit.jsp이다
		
		em.addObject("b",eb); //b키 이름에 ed객체를 저장
		em.addObject("page", page);
		return em;
	} //board_edit

	//post방식으로 접근하는 수정완료
	@PostMapping("/board_edit_ok") //post로 접근하는 매핑주소만 처리
	//board_edit_ok 매핑주소 등록
	public ModelAndView board_edit_ok(@ModelAttribute BoardVO b, int page, RedirectAttributes rttr) throws Exception{
	/* 1.@ModelAttribute 스프링 api를 사용한 BoardVO b는 page쪽번호만 빼고 나머지 네임파라미터 이름과
	 * BoardVO빈클래스 변수명이 같을때 bno, writer, title, content 값이 b 객체에 저장되어 있다
	 * 하지만 page는 BoardVO빈클래스의 멤버변수명에 없기 때문에 별도의 int page매개변수로 받아온다
	 * */
		this.boardService.editBoard(b); //게시물 수정
		//문제)board.xml에 update 유일 아이디명이 b_edit로 지정해서 번호를 기준으로 글쓴이, 제목, 내용을 수정한다
		
		rttr.addFlashAttribute("msg","SUCCESS");
		
		
		return new ModelAndView("redirect:/board/board_list?page="+page);
		//ModelAndView생성자 인자값으로 redirect를 사용해서 다른 매핑주소로 이동
	} //board_edit_ok
	
	//게시물 삭제
	@RequestMapping("/board_del") //get 방식
	public ModelAndView board_del(@RequestParam("page") int page, int bno, RedirectAttributes rttr) throws Exception{
		
		this.boardService.delBoard(bno);
		// 번호를 기준으로 게시물 삭제
		rttr.addFlashAttribute("msg","SUCCESS");
		ModelAndView dm=new ModelAndView();
		dm.setViewName("redirect:/board/board_list");
		// setViewName() 메서드 인자값으로 redirect:/ 를 사용한 매핑주소 경로
		dm.addObject("page",page);//page키이름에 페이지 번호를 저장
		// 주소창에 board_list?page=쪽번호 get방식으로 책갈피 기능을 구현
		return dm;
	}//board_del()
	
	/* 메서드 리턴타입이 String인 경우
	 * 1.리턴 문자열이 뷰페이지 경로로 하는 경우는 기존 디비에 저장된 레코드값을 뷰페이지로 출력하는 경우이다
	 * 목록보기, 수정폼, 삭제폼, 내용보기 경우이다
	 * 2.반환문자열이 "redirect:/매핑주소"가 되는 경우는 레코드를 저장/수정/삭제후 변경된
	 * 디비 레코드값을 다시 확인하고자 하는 경우 사용한다 서블릿 자바의 sendRedirect()와 기능이 같다
	 * */
}

















