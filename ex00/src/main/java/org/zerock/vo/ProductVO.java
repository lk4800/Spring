package org.zerock.vo;

import lombok.Getter;

@Getter //getter()메소드 자동제공
public class ProductVO {

	private String name; //상품이름 String 참조타입 멤버변수 기본값은 null
	private double price; //상품가격 double 기본타입 멤버변수 기본값은 0.0

	public ProductVO(String name, double price) {
		this.name = name; this.price = price;
	} //생성자 오버로딩
}
