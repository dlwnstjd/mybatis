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
	private final static String NS = "mapper.StudentMapper.";	//상수
	private static SqlSessionFactory sqlMap;	//클래스변수
	static {	//static 초기화블럭: 클래스 변수의 초기화 담당
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
		System.out.println("학생 전체 레코드 조회하기");
		
		System.out.println("xml 방식으로 조회하기");
		List<Student> list = session.selectList(NS+"select");
		for(Student s : list) System.out.println(s);
		
		System.out.println("인터페이스 방식으로 호출하기");
		//StudentMapper 클래스의 Class객체 리턴.
		Class<StudentMapper> css = StudentMapper.class;
		list = session.getMapper(css).select(null);
		for(Student s : list) System.out.println(s);
		
		System.out.println("1학년 학생 정보 조회");
		Map<String,Object> map = new HashMap<>();
		map.put("grade", 1);
		list = session.getMapper(StudentMapper.class).select(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("981213 학생 정보 조회");
		map.clear();
		map.put("studno", 981213);
		list = session.getMapper(StudentMapper.class).select(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("몸무게가 80이상인 학생 정보 조회");
		map.clear();
		map.put("weight", 80);
		list = session.getMapper(StudentMapper.class).select(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("김삿갓 학생정보 추가하기");
		Student st = new Student();
		st.setStudno(1001);
		st.setName("김삿갓");
		st.setJumin("1234561234567");
		st.setId("Kimsk");
		int result = session.getMapper(StudentMapper.class).insert(st);
		System.out.println(result + "건 추가");
		
		System.out.println("김삿갓 학생정보 조회하기");
		map.clear();
		map.put("studno",1001);
		list = session.getMapper(StudentMapper.class).select(map);
		for(Student s : list)  System.out.println(s);
		
		System.out.println("김삿갓 학생의 학년:1, 몸무게:80, 키:175 변경하기");
		st.setGrade(1);
		st.setWeight(80);
		st.setHeight(175);
		result = session.getMapper(StudentMapper.class).update(st);
		System.out.println(result + "건 변경");
		
		System.out.println("김삿갓 학생정보 조회하기");
		map.clear();
		map.put("studno",1001);
		list = session.getMapper(StudentMapper.class).select(map);
		for(Student s : list)  System.out.println(s);
		
		System.out.println("김삿갓 학생정보 삭제하기");
		result = session.getMapper(StudentMapper.class).delete("김삿갓",1001);
		System.out.println(result + "건 삭제");
		
		System.out.println("김삿갓 학생정보 조회하기");
		map.clear();
		map.put("studno",1001);
		list = session.getMapper(StudentMapper.class).select(map);
		for(Student s : list)  System.out.println(s);
	}
}
