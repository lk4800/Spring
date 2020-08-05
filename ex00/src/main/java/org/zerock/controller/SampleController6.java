package org.zerock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.vo.SampleVO;

@RestController
// 스프링 4.0 이후부터 @RestController를 사용하면
// jsp 뷰페이지를 만들지 않고도, REST 방식의 데이터 처리를 위한
// 문자열,json 등의 데이터를 만들 수 있다
// (cf: LOD 개념)
public class SampleController6 {
	@RequestMapping("/hello")//매핑주소 hello 등록
	public String hello() {
		return "Rest Begin";//문자열 객체를 반환
	}
	
	@RequestMapping("/sendVO")// 매핑주소 sendVO 등록
	// json 데이터를 확인하려면 매핑주소.json (sendVO.json)으로 JSON 확장자를 사용해야 한다
	public SampleVO sendVO() {
		// 리턴타입이 SampleVO이면
		// 변수명이 JSON의 키이름이 된다
		
		SampleVO vo = new SampleVO();
		vo.setFirstName("고");
		vo.setLastName("소미");
		vo.setMno(10);
		
		return vo;
	}//sendVO()
	
	@RequestMapping("/sendList")
	public List<SampleVO> sendList(){
		List<SampleVO> list = new ArrayList<>();
		// <>으로 제네릭 타입을 생략할 수 있는건 자바1.7버전에서 추가된 기능
		
		for(int i=1; i<=5; i++) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName("아이스");
			vo.setLastName("브레이커스");
			
			list.add(vo);//컬렉션에 추가
		}// for
		return list;
		// 컬렉션 키,값 쌍의 json 데이터가 반환됨
		// => 변수이름,값
	}//sendList()
	
	@RequestMapping("/sendMap")
	// 키,값 쌍의 Map타입 JSON
	public Map<Integer,SampleVO> sendMap(){
		Map<Integer,SampleVO> map=new HashMap<>();
		for(int i=1; i<=3; i++) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName("백");
			vo.setLastName("순대");
			
			map.put(i,vo);
			//맵 컬렉션에 키,값 추가
		}//for
		return map;
	}//sendMap();
	
	@RequestMapping("/sendError")
	// sendError 라는 매핑주소 등록
	public ResponseEntity<Void> sendError(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	/*
	 * @RestController 는 별도의 jsp 뷰페이지를 만들지 않고 REST 서비스를 실행하기 때문에
	 * 결과 데이터에 대한 예외적인 에러 상황이 발생할 수 있다.
	 * 스프링에서 제공하는 ResponseEntity 타입은 개발자가 문제가 되는 나쁜상태,
	 * 예를 들어 404,500 같은 HTTP 나쁜 상태코드를 데이터와 함께 브라우저로 전송할 수 있기 때문에
	 * 좀 더 세밀한 제어가 필요한 경우 사용된다
	 * 
	 * 이 실습코드에서는 나쁜 상태코드 BAD_REQUEST(HTTP ERROR 400)가 브라우저로 전송된다
	 * 
	 * cf) 정상적인 경우는 200상태코드가 반환된다
	 * 
	 */
	}// sendError()
	
	//정상적인 JSON데이터와 404(NOT_FOUND 해당 파일을 못찾을때) 나쁜상태코드를 함께 반환
	@RequestMapping("/sendErrorNot")
	public ResponseEntity<List<SampleVO>> sendErrorNot(){
	/*
	 * ResponseEntity 타입 스프링API 는 REST 서비스에서 JSP 파일을 만들지 않고도
	 * 서버프로그램을 개발할때 좀 더 쉽게 에러를 제어해서 사용하기 위해 활용된다
	 */
		List<SampleVO> list = new ArrayList<>();
		
		for(int i=1; i<=2; i++) {
			SampleVO vo=new SampleVO();
			vo.setMno(i);
			vo.setFirstName("볶음");
			vo.setLastName("면");
			
			list.add(vo);//컬렉션에 추가
		}//for
		return new ResponseEntity<List<SampleVO>>(list,HttpStatus.NOT_FOUND);
		//정상적인 컬렉션 json데이터와 404나쁜상태코드가 반환(f12로 개발자도구 열어서 console 확인해보기)
	}//sendErrorNot()
}
