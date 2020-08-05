package org.zerock.vo;

import lombok.Setter;
import lombok.Getter;

@Setter //setter()메서드 제공
@Getter //getter()메서드 제공
public class SampleVO {
// 데이터 저장빈 클래스
	//변수명이 JSON 데이터의 키이름이 된다
	private int mno;
	private String firstName;//성
	private String lastName;//이름
}
