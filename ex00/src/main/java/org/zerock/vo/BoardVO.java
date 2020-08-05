package org.zerock.vo;

import lombok.Getter;
import lombok.Setter;

@Setter //setter()메서드 제공
@Getter //getter()메서드 제공
public class BoardVO { 
//tbl_board 테이블 컬럼명과 일치하는 변수명을 가진 데이터 저장빈 클래스 생성 

	private int bno; //int타입 멤버변수 기본값은 0 =>번호
	private String writer; //작성자
	private String title; //제목
	private String content; //내용
	private int viewcnt; //조회수
	private String regdate; //등록날짜
	private int startrow;
	private int endrow;
	private int replycnt;
	
}
