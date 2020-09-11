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
	private final static String NS = "student2.";	//상수
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
		List<Student> list = session.selectList(NS+"select1");
		for(Student s : list) System.out.println(s);
		
		System.out.println("1학년 학생의 레코드 조회");
		Map<String,Object> map = new HashMap<>();
		map.put("grade", 1);
		list = session.selectList(NS+"select1",map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("학번이 981213학생의 레코드 조회");
		map.clear();
		map.put("studno", 981213);
		Student stu = session.selectOne(NS+"select1",map);
		System.out.println(stu);
		
		System.out.println("키가 180이상인 레코드 조회");
		map.clear();
		map.put("height", 180);
		list = session.selectList(NS+"select2",map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("1학년중 키가 180이상인 레코드 조회");
		map.clear();
		map.put("grade", 1);
		map.put("height", 180);
		list = session.selectList(NS+"select2",map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("1학년중 키가 180이상이고, 몸무게 80이상인 레코드 조회");
		map.clear();
		map.put("grade", 1);
		map.put("height", 180);
		map.put("weight", 80);
		list = session.selectList(NS+"select3",map);
		for(Student s : list) System.out.println(s);
		
		
		System.out.println("101,201,301 학과에 속한 레코드 조회");
		List<Integer> mlist = Arrays.asList(101,201,301);
		map.clear();
		map.put("column", "major1");
		map.put("datas", mlist);
		list = session.selectList(NS+"select4", map);
		for(Student s : list) System.out.println(s);

		System.out.println("1학년, 4학년학생의 레코드 조회");
		mlist = Arrays.asList(1, 4);
		map.clear();
		map.put("column", "grade");
		map.put("datas", mlist);
		list = session.selectList(NS+"select4", map);
		for(Student s : list) System.out.println(s);
	}
}
