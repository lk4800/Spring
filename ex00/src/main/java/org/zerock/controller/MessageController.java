package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.service.MessageService;
import org.zerock.vo.MessageVO;

@RestController
@RequestMapping("/message") //컨트롤러 자체에 매핑주소로 message등록
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	//메시지 추가
	@RequestMapping(value="/",method=RequestMethod.POST)
	//메서드 방식이 post일때 매핑주소를 처리
	public ResponseEntity<String> addMessage(
			@RequestBody MessageVO vo){
		//@RequestBody MessageVO vo는 json데이터를 vo객체 타입으로 변경한다
		ResponseEntity<String> entity = null;
		
		try {
			this.messageService.addMessage(vo); //메시지 추가
			entity = new ResponseEntity<>("SUCCESS",HttpStatus.OK);
			//메시지 추가가 성공하면 SUCCESS성공 문자열과 200정상 상태코드를 반환
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); 
			//예외 에러 메시지와 나쁜 상태코드를 반환
		}
		return entity;
	}
	
	
	
	
}
