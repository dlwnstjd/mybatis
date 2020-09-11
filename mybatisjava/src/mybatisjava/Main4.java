package mybatisjava;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.Student;
import mapper.StudentMapper;

public class Main4 {
	private final static String NS = "mapper.StudentMapper.";	//���
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
		
		System.out.println("xml ������� ��ȸ�ϱ�");
		List<Student> list = session.selectList(NS+"select");
		for(Student s : list) System.out.println(s);
		
		System.out.println("�������̽� ������� ȣ���ϱ�");
		//StudentMapper Ŭ������ Class��ü ����.
		Class<StudentMapper> css = StudentMapper.class;
		list = session.getMapper(css).select(null);
		for(Student s : list) System.out.println(s);
		
		System.out.println("1�г� �л� ���� ��ȸ");
		Map<String,Object> map = new HashMap<>();
		map.put("grade", 1);
		list = session.getMapper(StudentMapper.class).select(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("981213 �л� ���� ��ȸ");
		map.clear();
		map.put("studno", 981213);
		list = session.getMapper(StudentMapper.class).select(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("�����԰� 80�̻��� �л� ���� ��ȸ");
		map.clear();
		map.put("weight", 80);
		list = session.getMapper(StudentMapper.class).select(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("��� �л����� �߰��ϱ�");
		Student st = new Student();
		st.setStudno(1001);
		st.setName("���");
		st.setJumin("1234561234567");
		st.setId("Kimsk");
		int result = session.getMapper(StudentMapper.class).insert(st);
		System.out.println(result + "�� �߰�");
		
		System.out.println("��� �л����� ��ȸ�ϱ�");
		map.clear();
		map.put("studno",1001);
		list = session.getMapper(StudentMapper.class).select(map);
		for(Student s : list)  System.out.println(s);
		
		System.out.println("��� �л��� �г�:1, ������:80, Ű:175 �����ϱ�");
		st.setGrade(1);
		st.setWeight(80);
		st.setHeight(175);
		result = session.getMapper(StudentMapper.class).update(st);
		System.out.println(result + "�� ����");
		
		System.out.println("��� �л����� ��ȸ�ϱ�");
		map.clear();
		map.put("studno",1001);
		list = session.getMapper(StudentMapper.class).select(map);
		for(Student s : list)  System.out.println(s);
		
		System.out.println("��� �л����� �����ϱ�");
		result = session.getMapper(StudentMapper.class).delete("���",1001);
		System.out.println(result + "�� ����");
		
		System.out.println("��� �л����� ��ȸ�ϱ�");
		map.clear();
		map.put("studno",1001);
		list = session.getMapper(StudentMapper.class).select(map);
		for(Student s : list)  System.out.println(s);
	}
}
