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
		//selectOne: ��ȸ�Ǵ� ���ڵ尡 �Ѱ��� ���
		x = (Integer)session.selectOne("member.count");
		System.out.println("member ���̺��� ���ڵ� ����:" + x);
		
		//member ���̺��� ���� �о ȭ�鿡 ���
		System.out.println("member ���̺� ����==================");
		//selectList: ��ȸ�� ���ڵ尡 �������� ��� ����Ʈ�� �޾ƾ���
		List<Member> list = session.selectList("member.list");
		for(Member m : list) {
			System.out.println(m);
		}
		//admin���� ��ȸ
		System.out.println("admin ����==============");
		Member mem = session.selectOne("member.selectid","admin");
		System.out.println(mem);
		//�̸����� ���� ��ȸ
		System.out.println("name ����==============");
		list = session.selectList("member.selectname","��");//'%${value}%'
		for(Member m : list) {
			System.out.println(m);
		}
		System.out.println("name2 ����==============");
		list = session.selectList("member.selectname2","%��%");//#{value}
		for(Member m : list) {
			System.out.println(m);
		}
		//�ΰ��� �÷��� �������� ���
		System.out.println("�̸� ������ ������ ��ȸ�ϱ�==============");
		//�̸��� �� �ڰ� �ְ�, ������ ���� ��ȸ
		Map<String,Object> map = new HashMap<>();
		map.put("name", "%��%");
		map.put("gender", 1);
		list = session.selectList("member.selectnamegender",map);
		for(Member m : list) {
			System.out.println(m);
		}
	}
}














