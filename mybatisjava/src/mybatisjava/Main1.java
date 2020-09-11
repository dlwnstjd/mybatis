package mybatisjava;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.Member;

public class Main1 {
	public static void main(String[] args) {
		SqlSessionFactory sqlMap = null;
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader
					("mapper/mybatis-config.xml");
			sqlMap = new SqlSessionFactoryBuilder().build(reader);
		}catch(IOException e) {
			e.printStackTrace();
		}
		int x = 0;
		SqlSession session = sqlMap.openSession();
		//selectOne: 조회되는 레코드가 한개인 경우
		x = (Integer)session.selectOne("member.count");
		System.out.println("member 테이블의 레코드 갯수:" + x);
		
		//member 테이블의 정보 읽어서 화면에 출력
		System.out.println("member 테이블 정보==================");
		//selectList: 조회된 레코드가 여러건인 경우 리스트로 받아야함
		List<Member> list = session.selectList("member.list");
		for(Member m : list) {
			System.out.println(m);
		}
		//admin정보 조회
		System.out.println("admin 정보==============");
		Member mem = session.selectOne("member.selectid","admin");
		System.out.println(mem);
		//이름으로 정보 조회
		System.out.println("name 정보==============");
		list = session.selectList("member.selectname","김");//'%${value}%'
		for(Member m : list) {
			System.out.println(m);
		}
		System.out.println("name2 정보==============");
		list = session.selectList("member.selectname2","%김%");//#{value}
		for(Member m : list) {
			System.out.println(m);
		}
		//두개의 컬럼을 조건으로 사용
		System.out.println("이름 정보와 성별로 조회하기==============");
		//이름이 김 자가 있고, 여자인 정보 조회
		Map<String,Object> map = new HashMap<>();
		map.put("name", "%김%");
		map.put("gender", 1);
		list = session.selectList("member.selectnamegender",map);
		for(Member m : list) {
			System.out.println(m);
		}
	}
}














