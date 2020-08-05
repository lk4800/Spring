package net.abc.vo;

import lombok.Data;

@Data
public class AuthVO { 
	//권한부여 테이블인 tbl_member2_auth 컬럼 레코드값을 저장하기 위한 데이터 저장빈클래스
	
	private String userid; //아이디
	private String auth; //권한
	
}
