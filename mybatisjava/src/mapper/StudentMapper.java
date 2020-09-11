package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/*
 * interface 방식으로 mybatis 사용하기
 * 	네임스페이스: 인터페이스의 이름 => mapper.StudentMapper
 * 	id값: sql 구문을 저장하기 위한 값
 * 		   메서드의 이름으로 구문이 저장됨.
 * 		   메서드 오버로딩이 되면 실행시 오류 발생.
 * 	parameterType 속성값: 매개변수. 
 * 						두개이상의 값을 매개변수로 지정할 경우 map, 빈객체를 사용함.
 * 						=> 어노테이션을 이용하여 두개이상의 값을 매개변수로 지정할 수 있음.
 * 	resultType 속성값: 리턴타입으로 지정
 */
public interface StudentMapper {
	//@Select("select * from student")
	@Select({"<script>",
			"select * from student",
			"<if test='grade!=null'>where grade=#{grade}</if>",
			"<if test='studno!=null'>where studno=#{studno}</if>",
			"<if test='weight!=null'>where weight>=#{weight}</if>",
			"</script>"})
	List<Student> select(Map map);
	//여러건인경우 List<Student>
	//한건일경우 Student
	
	@Insert("insert into student(studno, name, jumin, id) "
			+ "values(#{studno},#{name},#{jumin},#{id})")
	int insert(Student st);

	@Update("update student set grade=#{grade}, weight=#{weight}, height=#{height}"
			+ " where studno=#{studno}")
	int update(Student st);

	@Delete("delete from student where name=#{name} and studno=#{studno}")
	int delete(@Param("name") String name,@Param("studno") int studno);

	
	
}
