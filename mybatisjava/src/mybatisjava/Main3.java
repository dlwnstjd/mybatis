package mybatisjava;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.Student;

public class Main3 {
	private final static String NS = "student2.";	//���
	private static SqlSessionFactory sqlMap;	//Ŭ��������
	static {	//static �ʱ�ȭ��: Ŭ���� ������ �ʱ�ȭ ���
		InputStream input = null;
		try {
			input = Resources.getResourceAsStream
					("mapper/mybatis-config.xml");
		}catch(IOException e) {
			e.printStackTrace();
		}
		sqlMap = new SqlSessionFactoryBuilder().build(input);
	}
	public static void main(String[] args) {
		SqlSession session = sqlMap.openSession();
		System.out.println("�л� ��ü ���ڵ� ��ȸ�ϱ�");
		List<Student> list = session.selectList(NS+"select1");
		for(Student s : list) System.out.println(s);
		
		System.out.println("1�г� �л��� ���ڵ� ��ȸ");
		Map<String,Object> map = new HashMap<>();
		map.put("grade", 1);
		list = session.selectList(NS+"select1",map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("�й��� 981213�л��� ���ڵ� ��ȸ");
		map.clear();
		map.put("studno", 981213);
		Student stu = session.selectOne(NS+"select1",map);
		System.out.println(stu);
		
		System.out.println("Ű�� 180�̻��� ���ڵ� ��ȸ");
		map.clear();
		map.put("height", 180);
		list = session.selectList(NS+"select2",map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("1�г��� Ű�� 180�̻��� ���ڵ� ��ȸ");
		map.clear();
		map.put("grade", 1);
		map.put("height", 180);
		list = session.selectList(NS+"select2",map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("1�г��� Ű�� 180�̻��̰�, ������ 80�̻��� ���ڵ� ��ȸ");
		map.clear();
		map.put("grade", 1);
		map.put("height", 180);
		map.put("weight", 80);
		list = session.selectList(NS+"select3",map);
		for(Student s : list) System.out.println(s);
		
		
		System.out.println("101,201,301 �а��� ���� ���ڵ� ��ȸ");
		List<Integer> mlist = Arrays.asList(101,201,301);
		map.clear();
		map.put("column", "major1");
		map.put("datas", mlist);
		list = session.selectList(NS+"select4", map);
		for(Student s : list) System.out.println(s);

		System.out.println("1�г�, 4�г��л��� ���ڵ� ��ȸ");
		mlist = Arrays.asList(1, 4);
		map.clear();
		map.put("column", "grade");
		map.put("datas", mlist);
		list = session.selectList(NS+"select4", map);
		for(Student s : list) System.out.println(s);
	}
}
