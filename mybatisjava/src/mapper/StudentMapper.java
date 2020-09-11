package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/*
 * interface ������� mybatis ����ϱ�
 * 	���ӽ����̽�: �������̽��� �̸� => mapper.StudentMapper
 * 	id��: sql ������ �����ϱ� ���� ��
 * 		   �޼����� �̸����� ������ �����.
 * 		   �޼��� �����ε��� �Ǹ� ����� ���� �߻�.
 * 	parameterType �Ӽ���: �Ű�����. 
 * 						�ΰ��̻��� ���� �Ű������� ������ ��� map, ��ü�� �����.
 * 						=> ������̼��� �̿��Ͽ� �ΰ��̻��� ���� �Ű������� ������ �� ����.
 * 	resultType �Ӽ���: ����Ÿ������ ����
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
	//�������ΰ�� List<Student>
	//�Ѱ��ϰ�� Student
	
	@Insert("insert into student(studno, name, jumin, id) "
			+ "values(#{studno},#{name},#{jumin},#{id})")
	int insert(Student st);

	@Update("update student set grade=#{grade}, weight=#{weight}, height=#{height}"
			+ " where studno=#{studno}")
	int update(Student st);

	@Delete("delete from student where name=#{name} and studno=#{studno}")
	int delete(@Param("name") String name,@Param("studno") int studno);

	
	
}
