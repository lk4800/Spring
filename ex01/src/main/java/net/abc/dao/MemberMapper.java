package net.abc.dao;

import net.abc.vo.MemberVO;

public interface MemberMapper { //modeldao

	MemberVO read(String userid); //아이디를 기준으로 회원정보 검색
}
