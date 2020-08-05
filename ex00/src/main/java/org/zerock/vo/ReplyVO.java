package org.zerock.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyVO {
//tbl_reply 테이블 컬럼명과 일치하는 변수명을 정의
	private int rno;//댓글번호
	private int bno;//게시판 번호
	private String replyer;//댓글 작성자
	private String replytext;// 댓글 내용
	private String regdate;	//작성날짜
	private String updatedate;//수정날짜
	
}
