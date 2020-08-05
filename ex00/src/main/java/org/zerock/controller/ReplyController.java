package org.zerock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.service.ReplyService;
import org.zerock.vo.ReplyVO;

@RestController
@RequestMapping("/replies")
// replies 매핑주소 등록
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	//댓글등록
	@RequestMapping(value="",method=RequestMethod.POST)
	//post 로 접근하는 매핑주소 처리
	public ResponseEntity<String> register(@RequestBody ReplyVO vo){
	/*
	 * @RequestBody 는 전송된 JSON데이터를 ReplyVO 타입 객체로 변환한다
	 * 데이터 전송 방식은 JSON 을 이용한다
	 * => JSON으로 보내진 데이터를 ReplyVO 타입으로 변경
	 */
		ResponseEntity<String> entity = null;
		try {
			this.replyService.addReply(vo);//댓글등록
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
			// 댓글 저장 성공시 SUCCESS 성공문자열을 반환하고 상태코드는 200 OK 코드를 반환
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			// getMessage() 는 에러메시지를 전송
			// 예외 에러메시지와 나쁜 상태코드를 반환
		}//try~catch
		return entity;
	}//register()
	
	
	//게시물에 해당하는 댓글목록 가져오기
	@RequestMapping(value="/all/{bno}",method=RequestMethod.GET)
	//GET으로 접근하는 매핑주소 처리
	// {bno} 는 주소창에 입력되어진 게시물 번호값을 추출하는 용도로 사용됨
	public ResponseEntity<List<ReplyVO>> list(
			@PathVariable("bno") int bno){
		//@PathVariable 는 매핑주소/all/{bno}에서 {bno}로 전달된
		//게시물 번호값을 추출해서 int bno에 저장시킨다
		ResponseEntity<List<ReplyVO>> entity=null;
		try {
			entity = new ResponseEntity<>(this.replyService.listReply(bno),HttpStatus.OK);
			// 게시물 번호에 해당하는 댓글 목록을 반환하고 정상상태이면 200정상 상태코드를 반환
			// (listReply()메서드를 자동으로 생성하게 하면 ReplyService에서 리턴타입이 Object로 생성된다
			//	이를 List<ReplyVO> 로 바꿔줘야 에러가 안난다)
		}catch(Exception e) {
			e.printStackTrace();
			//예외 에러 족적을 남김
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			// 나쁜 상태코드 반환
		}
		return entity;
	} //List()
	
	//댓글수정
	@RequestMapping(value="/{rno}",
			method= {RequestMethod.PUT, RequestMethod.PATCH})
	//메서드 방식이 복수개가 등록됨 PUT은 전체자료를 수정, PATCH는 일부자료를 수정
	public ResponseEntity<String> update(@PathVariable("rno")
	int rno, @RequestBody ReplyVO vo){
	/* @PathValiable("rno")는 주소창에 입력되어진 댓글 번호값을 추출해서 int rno에
	 * 매개변수에 저장한다. @RequestBody ReplyVO vo는 json키, 값 쌍으로 입력되어진
	 * json데이터를 ReplyVO vo객체타입으로 변환시킨다  */

		ResponseEntity<String> entity = null;
		
		try {
			vo.setRno(rno); //댓글번호저장
			this.replyService.updateReply(vo); //댓글 수정
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
			//댓글 수정 성공시 SUCCESS문자열을 반환하고 , 200정상상태 코드를 반환한다
		}catch(Exception e) {
			e.printStackTrace(); //예외 에러 족적을 출력
			entity = new ResponseEntity<String> (e.getMessage(), HttpStatus.BAD_REQUEST);
					//예외 에러메시지를 출력, 나쁜상태코드를 반환한다
			}
		return entity;
} //update()
	
	//댓글 삭제
	@RequestMapping(value="{rno}", // /{rno}와 {rno} 차이 없음 경로이니 / 넣는걸 추천
			method=RequestMethod.DELETE)
	//DELETE 메서드 방식은 삭제시 사용
	public ResponseEntity<String> remove(@PathVariable("rno") int rno){
		ResponseEntity<String> entity = null;
		
		try {
			this.replyService.delete(rno); //댓글삭제
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
}
