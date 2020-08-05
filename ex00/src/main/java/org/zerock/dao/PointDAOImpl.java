package org.zerock.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void updatPoint(String sender, int point) {
		Map<String, Object> pm = new HashMap<>();
		//복수개의 값을 전달하기 위해 컬렉션 map의 키, 값 쌍으로 저장
		pm.put("sender", sender);
		//키, 값 쌍으로 저장, sender키이름에 보낸사람을 저장하고 이 키이름을 point.xml에서 참조한다
		pm.put("point", point);
		this.sqlSession.update("pointUp", pm);
		//sqlSession에 id명과 같이 전달할 수 있는 매개변수의 개수는 하나밖에 없기 때문에 Map(컬렉션에 저장)을 사용하여 묶음
		//pointUp은 point.xml에서 설정할 유일한 update
	}
}
