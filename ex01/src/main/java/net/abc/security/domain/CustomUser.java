package net.abc.security.domain;


import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import net.abc.vo.MemberVO;

@Getter //getter()메서드 자동제공
public class CustomUser extends User {

	private static final long serialVersionUID = 1L;

	private MemberVO member;

	public CustomUser(String username, String password, 
			Collection<? extends GrantedAuthority> authorities) {
		//GrantedAuthority 상속받은 자손타입으로만 제네릭타입 형변환을 허용하면서 권한 목록을 구함.
		super(username, password, authorities);//조상의 오버로딩 된 생성자를 호출하면서 아이디,비번,권한목록
		//을 전달한다.
	}

	public CustomUser(MemberVO vo) {

		super(vo.getUserid(), vo.getUserpw(), vo.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(
						Collectors.toList()));
		/* 검색된 아이디,비번, 아이디와 아이디에 해당하는 권한정보  SimpleGrantedAuthority 생성자를 
		 * 호출하여 값을 전달하고 객체화 한다. 람다식으로 목록을 수집한 다음 User 클래스의 오버로딩 된 
		  * 생성자를 호출해서 3개의 인자값을 전달한다. 
		 */

		this.member = vo;
	}
}
