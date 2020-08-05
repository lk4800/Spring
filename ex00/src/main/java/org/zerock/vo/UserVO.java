package org.zerock.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {
//tbl_user테이블의 컬럼명과 일치하는 변수명을 가진 데이터 저장빈 클래스를 만든다
	private String uid2; //회원아이디
	private String upw; //비번
	private String uname; //회원이름
	private int upoint; //포인트 점수
}
