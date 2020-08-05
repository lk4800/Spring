package net.abc.vo;


import java.util.Date;
import java.util.List;

import lombok.Data;

@Data //setter(), getter(), toString()등 메서드를 자동으로 만들어준다(제공한다)
public class MemberVO {
//tbl_member2테이블의 컬럼 레코드값을 저장하기 위한 데이터 저장빈 클래스
	
	private String userid; //아이디
	private String userpw; //비번
	private String userName; //이름
	private boolean enabled; //'1'
	private Date regDate; //가입날짜
	private Date updateDate; //수정날짜
	
	private List<AuthVO> authList; //권한부여목록
}
