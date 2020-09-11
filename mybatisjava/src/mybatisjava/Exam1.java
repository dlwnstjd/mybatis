package mybatisjava;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.Student;

/*
 * 1. �л����̺��� ��ϵ� ���ڵ��� �Ǽ��� ���
 * 2. �л����̺� ��ϵ� ���ڵ� ���� ���
 * 3. �л����̺� ��ϵ� ���ڵ� �г⺰ ���� ���
 * 4. �л����̺� ��ϵ� ���ڵ� �̸��� ������ ������ ���
 */
public class Exam1 {
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

		System.out.println("[ ��� ]\n");
		System.out.println("* 1. �л����̺��� ��ϵ� ���ڵ��� �Ǽ��� ����ϱ�");
		x = (Integer)session.selectOne("student.count");
		System.out.println("student ���̺��� ���ڵ� ����:" + x);
		
		System.out.println("* 2. �л����̺� ��ϵ� ���ڵ� ��� ������ ����ϱ�");
		List<Student> list = session.selectList("student.list");
		for(Student s : list) {
			System.out.println(s);
		}
		
		System.out.println("* 3. �л����̺� ��ϵ� ���ڵ� �г⺰ ������ ����ϱ�");
		list = session.selectList("student.gradelist","3");
		for(Student s : list) {
			System.out.println(s);
		}
		
		System.out.println("* 4. �л����̺� ��ϵ� ���ڵ� �̸��� ������ ������ ����ϱ�");
		list = session.selectList("student.namelist");
		for(Student s : list) {
			System.out.println(s);
		}
	}

}
