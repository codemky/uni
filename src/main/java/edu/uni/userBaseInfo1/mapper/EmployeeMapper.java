package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.PageBean.ClassBean;
import edu.uni.userBaseInfo1.PageBean.ClassmateBean;
import edu.uni.userBaseInfo1.PageBean.EmployeeBean;
import edu.uni.userBaseInfo1.bean.Employee;
import edu.uni.userBaseInfo1.bean.EmployeeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface EmployeeMapper {
    int countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
    List<ClassmateBean> selectClassmateBean (@Param("userId") Long userId);
    List<ClassmateBean> selectClassmateBeanByFilter(
            @Param("userId") Long userId,
            @Param("classNamesList") List<String> classNamesList,
            @Param("cyearsList") List<String> cyearsList,
            @Param("specialtysList") List<String> specialtysList,
            @Param("user_sex") Integer user_sex,
            @Param("studentName") String studentName,
            @Param("studentNo") String studentNo,
            @Param("politicalsList") List<String> politicalsList,
            @Param("positionsList") List<String> positionsList
    );
    List<EmployeeBean> selectEmployeeBean (@Param("uniId") Long uniId);
    List<EmployeeBean> selectEmployeeBeanByFilter(@Param("uniId")  Long uniId,@Param("employeeName") String employeeName,
                                                  @Param("departmentNameList") List<String> departmentNameList,
                                                  @Param("subDepartmentNameList") List<String> subDepartmentNameList,
                                                  @Param("positionNameList") List<String> positionNameList);
}