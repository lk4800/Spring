package org.zerock.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageVO {
//tbl_message 테이블의 컬럼명과 일치하는 변수명을 가진 데이터 저장빈 클래스를 만든다
	private int mid;
	private String targetid; //외래키로 설정되어서 tbl_user테이블의 회원아이디 값만 저장됨 =>참조 무결성
	private String sender; //보낸사람
	private String message; //보낸메시지
	private String senddate; //보낸날짜


}
