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
 * 1. 학생테이블의 등록된 레코드의 건수를 출력
 * 2. 학생테이블에 등록된 레코드 정보 출력
 * 3. 학생테이블에 등록된 레코드 학년별 정보 출력
 * 4. 학생테이블에 등록된 레코드 이름이 두자인 정보를 출력
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

		System.out.println("[ 결과 ]\n");
		System.out.println("* 1. 학생테이블의 등록된 레코드의 건수를 출력하기");
		x = (Integer)session.selectOne("student.count");
		System.out.println("student 테이블의 레코드 갯수:" + x);
		
		System.out.println("* 2. 학생테이블에 등록된 레코드 모든 정보를 출력하기");
		List<Student> list = session.selectList("student.list");
		for(Student s : list) {
			System.out.println(s);
		}
		
		System.out.println("* 3. 학생테이블에 등록된 레코드 학년별 정보를 출력하기");
		list = session.selectList("student.gradelist","3");
		for(Student s : list) {
			System.out.println(s);
		}
		
		System.out.println("* 4. 학생테이블에 등록된 레코드 이름이 두자인 정보를 출력하기");
		list = session.selectList("student.namelist");
		for(Student s : list) {
			System.out.println(s);
		}
	}

}
