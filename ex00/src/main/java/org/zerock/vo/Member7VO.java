package org.zerock.vo;

import lombok.Getter;
import lombok.Setter;

@Setter //setter()메서드 자동 제공
@Getter //getter()메서드 자동 제공
public class Member7VO { 
	//tbl_member테이블 컬럼명과 일치되는 변수명을 가진 데이터 저장빈 클O래스

	private String userid;
	private String userpw;
	private String username;
	private String email;
	private String regdate;
	private String updatedate;

}