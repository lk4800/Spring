package net.abc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Setter;
import net.abc.dao.MemberMapper;
import net.abc.security.domain.CustomUser;
import net.abc.vo.MemberVO;

public class CustomUserDetailsService implements UserDetailsService {
	/* CustomUserDetailsService 별도의 인증/권한 체크를 하는 이유는 jsp등에서 단순히 사용자 아이디
	 * (스프링 시큐리티에서는 username) 정도가 아닌 사용자의 이름이나 이메일
	 * 같은 추가적인 정보를 이용하기 위해서 이다. 
	 */
	@Setter(onMethod_ = { @Autowired }) //자동의존성 주입
	private MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		//전달되어진 아이디값을 출력
		System.out.println("Load User By UserName : " + userName);

		//아이디를 기준으로 회원정보 검색
		MemberVO vo = memberMapper.read(userName);

		//데이터베이스로 부터 가져온 회원정보를 출력
		System.out.println("queried by member mapper: " + vo);

		return vo == null ? null : new CustomUser(vo);//생성자를 호출해서 검색된 회원정보값을 넘김.그런다음
		//생성된 객체를 반환.
		//return null;
	} 

}
