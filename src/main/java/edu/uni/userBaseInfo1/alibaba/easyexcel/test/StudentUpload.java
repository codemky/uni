package edu.uni.userBaseInfo1.alibaba.easyexcel.test;

import com.alibaba.excel.metadata.BaseRowModel;
import edu.uni.administrativestructure.bean.Class;
import edu.uni.administrativestructure.bean.Department;
import edu.uni.administrativestructure.bean.Position;
import edu.uni.administrativestructure.bean.Subdepartment;
import edu.uni.administrativestructure.service.PositionService;
import edu.uni.administrativestructure.service.SubdepartmentService;
import edu.uni.professionalcourses.bean.Specialty;
import edu.uni.userBaseInfo1.alibaba.easyexcel.test.model.EmployeeModel;
import edu.uni.userBaseInfo1.alibaba.easyexcel.test.model.StudentModel;
import edu.uni.userBaseInfo1.alibaba.easyexcel.test.util.ExcelUtil;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.AddressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import sun.java2d.loops.MaskFill;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

/**
 * @Author chenenru
 * @ClassName StudentUpload
 * @Description
 * @Date 2019/5/21 20:46
 * @Version 1.0
 **/
@Component
public class StudentUpload {
    @Autowired
    private OtherDepartmentService Ddepartment;
    @Autowired
    private OtherClassService Cclass;
    @Autowired
    private PoliticalAffiliationService PpoliticalAffiliation;
    @Autowired
    private AddressService Aaddress;
    @Autowired
    private AddrCountryService AaddrCountry;
    @Autowired
    private AddrStateService AaddrState;
    @Autowired
    private AddrCityService AaddrCity;
    @Autowired
    private AddrAreaService AaddrArea;
    @Autowired
    private AddrStreetService AaddrStreet;
    @Autowired
    private OtherSpecialtyService Sspecialty;
    @Autowired
    private UserService Uuser;
    @Autowired
    private EcommService Eecomm;
    @Autowired
    private StudentService Sstudent;
    @Autowired
    private OtherSubdepartmentService Ssubdepartment;
    @Autowired
    private EmployeeHistoryService EemployeeHistory;
    @Autowired
    private OtherPositionService Pposition;
    @Autowired
    private EmployeeService Eemployee;

    private static AddrCountryService addrCountryService;
    private static AddrStateService addrStateService;
    private static AddrCityService addrCityService;
    private static AddrAreaService addrAreaService;
    private static AddrStreetService addrStreetService;
    private static AddressService addressService;
    private static OtherDepartmentService departmentService;
    private static OtherClassService classService;
    private static PoliticalAffiliationService politicalAffiliationService;
    private static OtherSpecialtyService otherSpecialtyService;
    private static UserService userService;
    private static EcommService ecommService;
    private static StudentService studentService;
    private static OtherSubdepartmentService otherSubdepartmentService;
    private static EmployeeHistoryService employeeHistoryService;
    private static OtherPositionService otherPositionService;
    private static EmployeeService employeeService;

    @PostConstruct
    public void init() {
        departmentService = this.Ddepartment;
        classService = this.Cclass;
        politicalAffiliationService = this.PpoliticalAffiliation;
        addrCountryService = this.AaddrCountry;
        addrStateService = this.AaddrState;
        addrCityService = this.AaddrCity;
        addrAreaService = this.AaddrArea;
        addrStreetService = this.AaddrStreet;
        addressService = this.Aaddress;
        otherSpecialtyService = this.Sspecialty;
        userService = this.Uuser;
        ecommService = this.Eecomm;
        studentService = this.Sstudent;
        otherSubdepartmentService = this.Ssubdepartment;
        employeeHistoryService = this.EemployeeHistory;
        otherPositionService = this.Pposition;
        employeeService = this.Eemployee;
        //initData();
    }

    int i = 0;//记录excel里面的是第几行
    int flag = 0;//记录地址切割字符串到第几个字符
    StringBuffer s = new StringBuffer();
    List<Department> departments = new ArrayList<>();
    Department department = new Department();
    List<Specialty> specialties = new ArrayList<>();
    Specialty specialty = new Specialty();
    Class aClass = new Class();
    PoliticalAffiliation politicalAffiliation = new PoliticalAffiliation();
    AddrCountry addrCountry = new AddrCountry();
    AddrState addrState = new AddrState();
    AddrCity addrCity = new AddrCity();
    AddrArea addrArea = new AddrArea();
    AddrStreet addrStreet = new AddrStreet();
    Ecomm ecomm = new Ecomm();
    String addressDetail = new String();
    Address address = new Address();
    User user = new User();
    Student student = new Student();
    Employee employee = new Employee();
    Subdepartment subdepartment = new Subdepartment();
    EmployeeHistory employeeHistory = new EmployeeHistory();
    Position position = new Position();
    String userNumber = new String();
    Date beginLearnDate = new Date();
    String userName = new String();
    String identification = new String();
    String userSex = new String();
    String userBirthday = new String();
    String userPassword = new String();
    String usersecretKey = new String();
    String departmentName = new String();
    String majorName = new String();
    String political = new String();
    String homeAddress = new String();
    String mailEcomm = new String();
    String zipCode = new String();
    static Set<String> douStuNo = new HashSet<>();
    static Set<String> douIden = new HashSet<>();

    public static Set<String> getDouStuNo() {
        return douStuNo;
    }

    public static void setDouStuNo(Set<String> douStuNo) {
        StudentUpload.douStuNo = douStuNo;
    }

    public static Set<String> getDouIden() {
        return douIden;
    }

    public static void setDouIden(Set<String> douIden) {
        StudentUpload.douIden = douIden;
    }

    /**
     * Author: chenenru 15:24 2019/5/21
     *
     * @param
     * @return
     * @apiNote: 向数据库插入批量审批通过的学生账号
     */
    public StringBuffer insertStudent(Object o) {
        s.delete(0, s.length());
        //先向user表插入新的记录，根据userId向student表插入时记录时关联到外键
        insertUser(o);
        //再向student表插入记录

        //年级
        String gradeName = (String) getFieldValueByName("gradeName", o);

        //班级
        String className = (String) getFieldValueByName("className", o);

        //宿舍
        String liveRoom = (String) getFieldValueByName("liveRoom", o);

        aClass = classService.selectClassByName(className);

        //插入其他相同的部分
        insertSame(o);

        student.setUserId(user.getId());
        student.setUniversityId(Long.valueOf(1));//到时候要改为session里面获取
        student.setStuNo(userNumber);
        student.setBeginLearnDate(beginLearnDate);
        student.setGrade(gradeName);
        student.setSpecialtyId(specialty.getId());
        student.setClassId(aClass.getId());
        student.setPoliticalId(politicalAffiliation.getId());
        student.setLiveRoom(Long.valueOf(1));//宿舍的地址
        student.setHomeAddressId(address.getId());
        student.setPhoneEcommId(ecomm.getId());
        student.setDatetime(new Date());
        student.setByWho((long) 1);//应该读取上传者的userId
        student.setDeleted(false);
        //System.out.println(student);
        studentService.insert(student);
        return s;
    }

    /**
     * Author: chenenru 12:36 2019/5/23
     *
     * @param
     * @return
     * @apiNote: 向数据库插入批量审批通过的职员账号
     */
    public StringBuffer insertEmployee(Object o) {
        s.delete(0, s.length());
        //先向user表插入新的记录，根据userId向student表插入时记录时关联到外键
        insertUser(o);
        //再向employee表插入记录
        //所属科室、系
        String subdepartmentName = (String) getFieldValueByName("subdepartmentName", o);
        subdepartment = otherSubdepartmentService.selectBySubdepartmentName(subdepartmentName);
        //职员简历，不用校验
        String eemployeehistory = (String) getFieldValueByName("employeehistory", o);
        employeeHistory.setUserId((long) 1);
        employeeHistory.setDeleted(false);
        employeeHistory.setBeginTime(new Date());
        employeeHistory.setByWho((long) 1);
        employeeHistory.setDescript(eemployeehistory);
        employeeHistory.setDatetime(new Date());
        employeeHistoryService.insert(employeeHistory);
        //行政岗位
        String pposition = (String) getFieldValueByName("position", o);
        position = otherPositionService.selectPositionByPositionName(pposition);

        insertSame(o);

        employee.setUserId(user.getId());
        employee.setUniversityId((long) 1);//应该从session读取上传者的university_id
        employee.setEmpNo(userNumber);
        employee.setDepartmentId(department.getId());//subdepartment.getDepartmentId()
        employee.setSubdepartmentId(subdepartment.getId());
        employee.setEmployHistoryId(employeeHistory.getId());
        employee.setDisciplineId(specialty.getId());
        employee.setPoliticalId(politicalAffiliation.getId());
        employee.setPositionId(position.getId());
        employee.setHomeAddressId(address.getId());
        employee.setPhoneEcommId(ecomm.getId());
        employee.setDatetime(new Date());
        employee.setByWho((long) 1);//应该从session读取上传者的user_id
        employee.setDeleted(false);
        employeeService.insertEmployee(employee);
        return s;
    }

    /**
     * Author: chenenru 0:00 2019/5/23
     *
     * @param o
     * @return
     * @apiNote: 向用户表插入记录
     */
    public StringBuffer insertUser(Object o) {
        //先向user表插入新的记录，根据userId向student表插入时记录时关联到外键
        //学生的真实姓名
        userName = (String) getFieldValueByName("userName", o);
        //学生身份证
        identification = (String) getFieldValueByName("identification", o);
        //学生性别
        userSex = (String) getFieldValueByName("userSex", o);
        //学生出生日期
        userBirthday = (String) getFieldValueByName("userBirthday", o);
        //学生学号
        userNumber = (String) getFieldValueByName("userNumber", o);
        //学生密码
        userPassword = (String) getFieldValueByName("userPassword", o);
        //学生秘钥
        usersecretKey = (String) getFieldValueByName("usersecretKey", o);
        //学生注册时间
        //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        beginLearnDate = (Date) getFieldValueByName("beginLearnDate", o);
        //System.out.println("注册时间："+beginLearnDate);
        user.setName(userNumber);
        user.setIdentification(identification);
        if (userSex.equals("男")) {
            user.setUserSex(1);
        } else {
            user.setUserSex(0);
        }
        user.setUserBirthday(userBirthday);
        user.setUserName(userName);
        user.setPwd(userPassword);
        user.setSalt(usersecretKey);
        user.setUniversityId((long) 1);//应该从session里面获取用户的university_id
        user.setUserType(1);
        user.setStatus(0);
        user.setRegist(beginLearnDate);
        boolean b = userService.insertUser(user);
        //Long id = user.getId();
        //System.out.println("自增的id为：" +id);
        if (b == true) {
            s.append("插入成功");
            //System.out.println("插入成功");
        } else {
            s.append("插入失败");
            //System.out.println("插入失败");
        }
        return s;
    }

    /**
     * Author: chenenru 0:01 2019/5/23
     *
     * @param
     * @return
     * @apiNote: 插入相同的部分
     */
    public StringBuffer insertSame(Object o) {
        //学院名
        departmentName = (String) getFieldValueByName("departmentName", o);
        departments = departmentService.selectDepartmentByName(departmentName);
        if (departments.size() >= 1) {
            department = departments.get(0);
        }
        //主修专业
        majorName = (String) getFieldValueByName("majorName", o);
        specialties = otherSpecialtyService.seclectByDIdAndSName(Long.valueOf(1), department.getId(), majorName);
        if (specialties.size() >= 1) {
            specialty = specialties.get(0);
        }
        //政治面貌
        political = (String) getFieldValueByName("political", o);
        politicalAffiliation = politicalAffiliationService.selectByPoliticalAffiliationName(political);
        //家庭住址
        homeAddress = (String) getFieldValueByName("homeAddress", o);
        String[] split = homeAddress.split("/");
        int init = 0;//记录字符串循环的次数
        flag = 0;
        for (String string : split) {
            if (init == 0) {
                addrCountry = addrCountryService.selectCountryByName(string);
                if (addrCountry == null) {
                    s.append("插入地址时找不到国家");
                }
            } else if (init == 1) {
                addrState = addrStateService.selectByAddrStateName(string);
                if (addrState == null) {
                    s.append("插入地址时找不到省份");
                }
            } else if (init == 2) {
                addrCity = addrCityService.selectByCityName(string);
                if (addrCity == null) {
                    s.append("插入地址时找不到城市");
                }
            } else if (init == 3) {
                addrArea = addrAreaService.selectByAreaName(string);
                if (addrArea == null) {
                    s.append("插入地址时找不到县/区");
                }
            } else if (init == 4) {
                addrStreet = addrStreetService.selectByAddrStreetName(string);
                if (addrStreet == null) {
                    s.append("插入地址时找不到街道");
                }
            } else if (init == 5) {
                addressDetail = string;
            }
            init++;
        }
        //邮政编码
        zipCode = (String) getFieldValueByName("zipCode", o);

        address.setUserId(user.getId());
        address.setCountry(addrCountry.getId());
        address.setState(addrState.getId());
        address.setCity(addrCity.getId());
        address.setArea(addrArea.getId());
        address.setStreet(addrStreet.getId());
        address.setDetail(addressDetail);
        address.setZipCode(zipCode);//批量导入也要加学生的邮政编码
        address.setTelephone(mailEcomm);
        address.setFlag(0);
        address.setDatetime(new Date());
        address.setByWho((long) 1);//应该从session读取上传者的userId
        address.setDeleted(false);
        addressService.insert(address);
        //通讯方式
        mailEcomm = (String) getFieldValueByName("mailEcomm", o);
        ecomm.setUserId(user.getId());
        ecomm.setContent(mailEcomm);
        ecomm.setFlag(3);
        ecomm.setDatetime(new Date());
        ecomm.setByWho((long) 1);
        ecomm.setDeleted(false);
        ecomm.setId(Long.valueOf(1));
        ecommService.insert(ecomm);
        return s;
    }

    /**
     * Author: chenenru 14:48 2019/5/21
     *
     * @param
     * @return
     * @apiNote: 用于校验用户上传批量导入的学生模板
     */
    public StringBuffer checkoutStudent(Object o) {
        i++;
        flag = 0;
        s.delete(0, s.length());
        checkoutUser(o);

        checkoutSame(o);

        //校验学号
        String userNumber = (String) getFieldValueByName("userNumber", o);
        System.out.print(getFieldValueByName("userNumber", o) + " ");
        if (douStuNo != null) {
            for (String string : douStuNo) {
                if (string.equals(userNumber)) {
                    s.append("第").append(i).append("行第5列字段出现重复，请检查\n");
                }
            }
        }
        douStuNo.add(userNumber);
        if (studentService.selectValidStuByStuNo(userNumber) != null) {
            s.append("第").append(i).append("行第5列字段的账号已存在，请检查\n");
        }
        if (userNumber.length() != 12 && userNumber.length() != 10) {
            s.append("第").append(i).append("行第5列字段长度不符合，请检查\n");
            //System.exit(0);
        }

        //校验年级 这个可以不用校验，因为年级可以添加新的
        System.out.print(getFieldValueByName("gradeName", o) + " ");
        String gradeName = (String) getFieldValueByName("gradeName", o);

        //校验班级 前提是二级学院名称要写对
        System.out.print(getFieldValueByName("className", o) + " ");
        String className = (String) getFieldValueByName("className", o);
        if (department != null) {
            aClass = classService.selectClassByName(className);
            if (!aClass.getDepartmentId().equals(department.getId())) {
                s.append("第" + i + "行第12列字段不符合，请检查\n");
            }
        }

        //校验宿舍地址
        System.out.print(getFieldValueByName("liveRoom", o) + " ");
        String liveRoom = (String) getFieldValueByName("liveRoom", o);

        System.out.println();
        return s;
    }

    /**
     * Author: chenenru 12:34 2019/5/23
     *
     * @param
     * @return
     * @apiNote: 用于校验用户上传批量导入的教师模板
     */
    public StringBuffer checkoutEmployee(Object o) {
        i++;
        flag = 0;
        s.delete(0, s.length());
        checkoutUser(o);

        checkoutSame(o);

        //校验教工号
        String userNumber = (String) getFieldValueByName("userNumber", o);
        System.out.print(getFieldValueByName("userNumber", o) + " ");
        if (douStuNo != null) {
            for (String string : douStuNo) {
                if (string.equals(userNumber)) {
                    s.append("第").append(i).append("行第5列字段出现重复，请检查\n");
                }
            }
        }
        douStuNo.add(userNumber);
        employeeService.selectEmployeeByEmpNo(userNumber);
        if (employeeService.selectEmployeeByEmpNo(userNumber) != null) {
            s.append("第").append(i).append("行第5列字段的账号已存在，请检查\n");
        }
        if (userNumber.length() != 12 && userNumber.length() != 10) {
            s.append("第").append(i).append("行第5列字段长度不符合，请检查\n");
            //System.exit(0);
        }

        //校验所属科室、系
        String subdepartmentName = (String) getFieldValueByName("subdepartmentName", o);
        System.out.print(subdepartmentName + " ");
        subdepartment = otherSubdepartmentService.selectBySubdepartmentName(subdepartmentName);
        flag = 0;
        if (subdepartment == null) {
            s.append("第" + i + "行第10列字段不符合，请检查\n");
        }
        //校验职员简历，不用校验
        String employeehistory = (String) getFieldValueByName("employeehistory", o);
        System.out.print(employeehistory + " ");
        //校验行政岗位
        String pposition = (String) getFieldValueByName("position", o);
        System.out.print(pposition + " ");
        position = otherPositionService.selectPositionByPositionName(pposition);
        if (position == null) {
            s.append("第" + i + "行第14列字段不符合，请检查\n");
        }
        System.out.println();

        return s;
    }

    /**
     * Author: chenenru 23:30 2019/5/22
     *
     * @param o
     * @return StringBuffer
     * @apiNote: 校验user表
     */
    public StringBuffer checkoutUser(Object o) {
        System.out.print(getFieldValueByName("userName", o) + " ");
        //校验姓名
        String userName = (String) getFieldValueByName("userName", o);
        if (userName.length() < 0) {
            s.append("第").append(i).append("行第1列字段长度不能为空，请检查\n");
            //System.out.println("第"+i+"行第1列字段长度不能为空，请检查");
        }
        System.out.print(getFieldValueByName("identification", o) + " ");
        //校验身份证号
        String identification = (String) getFieldValueByName("identification", o);

        if (douIden != null) {
            for (String string : douIden) {
                if (string.equals(identification)) {
                    s.append("第").append(i).append("行第2列字段出现重复，请检查\n");
                }
            }
        }
        douIden.add(identification);

        if (userService.selectByIden(identification) != null) {
            s.append("第").append(i).append("行第2列字段的账号已存在，请检查\n");
        }
        if (identification.length() < 18 || identification.length() > 22) {
            s.append("第").append(i).append("行第2列字段长度不符合，请检查\n");
            //System.out.println("第"+i+"行第2列字段长度不符合，请检查");
        }


        System.out.print(getFieldValueByName("userSex", o) + " ");
        //校验性别
        String userSex = (String) getFieldValueByName("userSex", o);


        System.out.print(getFieldValueByName("userBirthday", o) + " ");
        //校验出生日期
        String userBirthday = (String) getFieldValueByName("userBirthday", o);


        System.out.print(getFieldValueByName("userPassword", o) + " ");
        //校验密码
        String userPassword = (String) getFieldValueByName("userPassword", o);


        System.out.print(getFieldValueByName("usersecretKey", o) + " ");
        //校验秘钥
        String usersecretKey = (String) getFieldValueByName("usersecretKey", o);


        System.out.print(getFieldValueByName("beginLearnDate", o) + " ");
        //校验入学日期
        Date beginLearnDate = (Date) getFieldValueByName("beginLearnDate", o);
        return s;
    }

    /**
     * Author: chenenru 23:31 2019/5/22
     *
     * @param o
     * @return StringBuffer
     * @apiNote: 校验共同部分
     */
    public StringBuffer checkoutSame(Object o) {

        //校验学院:1.根据session里面的universityId确认上传者的学校的id,再根据学校的id查询所有的二级学院
        System.out.print(getFieldValueByName("departmentName", o) + " ");

        departmentName = (String) getFieldValueByName("departmentName", o);
        departments = departmentService.selectDepartmentByName(departmentName);
        if (departments.size() >= 1) {
            department = departments.get(0);
        } else {
            s.append("第" + i + "行第9列字段不符合，请检查\n");
        }

        //校验专业  前提是二级学院名称要写对
        System.out.print(getFieldValueByName("majorName", o) + " ");
        majorName = (String) getFieldValueByName("majorName", o);
        if (department != null) {
            specialties = otherSpecialtyService.seclectByDIdAndSName(Long.valueOf(1), department.getId(), majorName);
            if (specialties.size() >= 1) {
                specialty = specialties.get(0);
            } else {
                s.append("第" + i + "行第11列字段不符合，请检查\n");
            }
        }

        //校验政治面貌
        System.out.print(getFieldValueByName("political", o) + " ");
        political = (String) getFieldValueByName("political", o);
        politicalAffiliation = politicalAffiliationService.selectByPoliticalAffiliationName(political);
        if (politicalAffiliation == null) {
            s.append("第" + i + "行第13列字段不符合，请检查\n");
        }

        //家庭住址
        homeAddress = (String) getFieldValueByName("homeAddress", o);
        System.out.println(homeAddress + " ");
        String[] split = homeAddress.split("/");
        int init = 0;//记录字符串循环的次数
        flag = 0;
        for (String string : split) {
            if (init == 0) {
                addrCountry = addrCountryService.selectCountryByName(string);
                if (addrCountry == null) {
                    s.append("第" + i + "行第15列地址的国家不正确");
                }
            } else if (init == 1) {
                addrState = addrStateService.selectByAddrStateName(string);
                if (addrState == null) {
                    s.append("第" + i + "行第15列地址的省份不正确");
                }
            } else if (init == 2) {
                addrCity = addrCityService.selectByCityName(string);
                if (addrCity == null) {
                    s.append("第" + i + "行第15列地址的城市不正确");
                }
            } else if (init == 3) {
                addrArea = addrAreaService.selectByAreaName(string);
                if (addrArea == null) {
                    s.append("第" + i + "行第15列地址的县/区不正确");
                }
            } else if (init == 4) {
                addrStreet = addrStreetService.selectByAddrStreetName(string);
                if (addrStreet == null) {
                    s.append("第" + i + "行第15列地址的街道不正确");
                }
            }
            init++;
        }

        //校验邮政编码
        String zipCode = (String) getFieldValueByName("zipCode", o);
        System.out.print(getFieldValueByName("zipCode", o) + " ");
        if (zipCode.length() != 6) {
            s.append("第" + i + "行第16列字段不符合，请检查\n");
        }

        //校验通信方式
        System.out.print(getFieldValueByName("mailEcomm", o) + " ");
        mailEcomm = (String) getFieldValueByName("mailEcomm", o);
        if (mailEcomm.length() != 11) {
            s.append("第" + i + "行第17列字段不符合，请检查\n");
        }

        return s;
    }

    /**
     * Author: chenenru 10:16 2019/6/18
     *
     * @param
     * @return
     * @apiNote: 校验批量更新的职员账号
     */
    public StringBuffer checkUpdateEmployee(MultipartFile file) throws IOException {
        List<EmployeeModel> employeeModels = ExcelUtil.readExcel(file.getInputStream(), EmployeeModel.class);
        int i = 0;
        int flag = 0;
        s.delete(0, s.length());
        douStuNo.clear();
        douIden.clear();
        for (EmployeeModel e : employeeModels) {
            i++;
            if (douStuNo != null) {
                for (String string : douStuNo) {
                    if (string.equals(e.getUserNumber())) {
                        s.append("第").append(i).append("行第5列字段出现重复，请检查\n");
                        flag = 1;
                    }
                }
            }
            douStuNo.add(e.getUserNumber());

            if (douIden != null) {
                for (String string : douIden) {
                    if (string.equals(e.getIdentification())) {
                        s.append("第").append(i).append("行第2列字段出现重复，请检查\n");
                        flag = 1;
                    }
                }
            }
            douIden.add(e.getIdentification());
            if (e.getUserNumber() == null) {
                s.append("第").append(i).append("行第5列字段长度不能为空，请检查\n");
                flag = 1;
            }
            if (e.getIdentification() == null) {
                s.append("第").append(i).append("行第2列字段长度不能为空，请检查\n");
                flag = 1;
            }
            if (e.getIdentification().length() < 18 || e.getIdentification().length() > 22) {
                s.append("第").append(i).append("行第2列字段长度不符合，请检查\n");
            }
/*            if (flag == 1) {
                continue;
            }*/
            //根据学号+身份证号获取唯一的学生，就是该学生的学号+身份证号不能为空
            if (e.getUserNumber() != null && e.getIdentification() != null) {
                Employee employee = employeeService.selectValidEmployeeByEmpNoAndUniId(e.getUserNumber(), Long.valueOf(1));
                User user = userService.selectUserByUniIdAndIde(Long.valueOf(1), e.getIdentification());
                if (employee.getUserId().equals(user.getId())) {
                    if (e.getDepartmentName() != null) {
                        //为专业服务的
                        departments = departmentService.selectDepartmentByName(e.getDepartmentName());
                        System.out.println(departments.size());
                        if (departments.size() >= 1) {
                            department = departments.get(0);
                        }
                    }
                    if (e.getSubdepartmentName() != null) {
                        if (otherSubdepartmentService.selectBySubdepartmentName(e.getSubdepartmentName())==null) {
                            s.append("第" + i + "行第10列不正确，请检查\n");
                        }
                    }
                    //一个教师的专业你怎么校验？
                    if (e.getMajorName() != null) {
                        if (department.getId()==null) {
                            s.append("第" + i + "行第9列不正确，请检查\n");
                            flag = 1;
                        } else {
                            specialties = otherSpecialtyService.seclectByDIdAndSName(Long.valueOf(1), department.getId(), e.getMajorName());
                            if (specialties.size() < 1) {
                                s.append("第" + i + "行第11列字段不正确，请检查\n");
                            }
                        }
                    }
                    //简历不用校验
                    if (e.getPolitical() != null) {
                        politicalAffiliation = politicalAffiliationService.selectByPoliticalAffiliationName(e.getPolitical());
                        if (politicalAffiliation == null) {
                            s.append("第" + i + "行第13列字段不正确，请检查\n");
                        }
                    }
                    if (e.getPosition()!=null){
                        if (otherPositionService.selectPositionByPositionName(e.getPosition())==null){
                            s.append("第" + i + "行第14列字段不正确，请检查\n");
                        }
                    }
                    if (e.getHomeAddress() != null) {
                        String[] split = e.getHomeAddress().split("/");
                        List<Address> addresses = addressService.selectByUserId(user.getId());
                        if (addresses.size() > 0) {
                            if (e.getZipCode() == null) {
                                //student.set 地址需要zip
                                s.append("第" + i + "行第16列字段是15列的前提，不能为空\n");
                            }
                            int init = 0;//记录字符串循环的次数
                            for (String string : split) {
                                if (init == 0) {
                                    addrCountry = addrCountryService.selectCountryByName(string);
                                    if (addrCountry == null) {
                                        s.append("第" + i + "行第15列地址的国家不正确");
                                        flag = 1;
                                    }
                                } else if (init == 1) {
                                    addrState = addrStateService.selectByAddrStateName(string);
                                    if (addrState == null) {
                                        s.append("第" + i + "行第15列地址的省份不正确");
                                        flag = 1;
                                    }
                                } else if (init == 2) {
                                    addrCity = addrCityService.selectByCityName(string);
                                    if (addrCity == null) {
                                        s.append("第" + i + "行第15列地址的城市不正确");
                                        flag = 1;
                                    }
                                } else if (init == 3) {
                                    addrArea = addrAreaService.selectByAreaName(string);
                                    if (addrArea == null) {
                                        s.append("第" + i + "行第15列地址的县/区不正确");
                                        flag = 1;
                                    }
                                } else if (init == 4) {
                                    addrStreet = addrStreetService.selectByAddrStreetName(string);
                                    if (addrStreet == null) {
                                        s.append("第" + i + "行第15列地址的街道不正确");
                                        flag = 1;
                                    }
                                }
                                init++;
                            }
                        }
                    }
                    if (e.getZipCode() != null) {
                        if (e.getZipCode().length() < 6) {
                            s.append("第" + i + "行第16列字段长度不正确，请检查\n");
                        }
                    }
                    if (e.getMailEcomm() != null) {
                        if (e.getMailEcomm().length() != 11) {
                            s.append("第" + i + "行第17列字段不正确，请检查\n");
                        }
                    }
                } else {
                    s.append("第" + i + "行第2、5列参数不正确，请改正");
                }
            } else {
                s.append("第" + i + "行第2、5列参数不能为空，请改正");
            }
        }
        return s;
    }

    /**
     * Author: chenenru 17:07 2019/6/20
     * @param
     * @return
     * @apiNote: 更新批量的职员信息
     */
    @Transactional(rollbackFor = {Exception.class})
    public StringBuffer UpdateEmployee(MultipartFile file) throws IOException {
        List<EmployeeModel> employeeModels = ExcelUtil.readExcel(file.getInputStream(), EmployeeModel.class);
        int i = 0;
        int flag = 0;
        s.delete(0, s.length());
        douStuNo.clear();
        douIden.clear();
        for (EmployeeModel e : employeeModels) {
            flag = 0;
            i++;
            if (douStuNo != null) {
                for (String string : douStuNo) {
                    if (string.equals(e.getUserNumber())) {
                        s.append("第").append(i).append("行第5列字段出现重复，请检查\n");
                        flag = 1;
                    }
                }
            }
            douStuNo.add(e.getUserNumber());

            if (douIden != null) {
                for (String string : douIden) {
                    if (string.equals(e.getIdentification())) {
                        s.append("第").append(i).append("行第2列字段出现重复，请检查\n");
                        flag = 1;
                    }
                }
            }
            douIden.add(e.getIdentification());
            if (flag == 1) {
                continue;
            }
            //根据学号+身份证号获取唯一的学生，就是该学生的学号+身份证号不能为空
            if (e.getUserNumber() != null && e.getIdentification() != null) {
                Employee employee = employeeService.selectValidEmployeeByEmpNoAndUniId(e.getUserNumber(), Long.valueOf(1));
                User user = userService.selectUserByUniIdAndIde(Long.valueOf(1), e.getIdentification());
                if (student.getUserId().equals(user.getId())) {
                    if (e.getUserName() != null) {
                        user.setUserName(e.getUserName());
                    }
                    if (e.getIdentification() != null) {
                        user.setIdentification(e.getIdentification());
                    }
                    if (e.getUserSex() != null) {
                        if (e.getUserSex().equals("男")) {
                            user.setUserSex(0);
                        } else {
                            user.setUserSex(1);
                        }
                    }
                    if (e.getUserBirthday() != null) {
                        user.setUserBirthday(e.getUserBirthday());
                    }
                    if (e.getUserNumber() != null) {
                        user.setName(e.getUserNumber());
                    }
                    if (e.getUserPassword() != null) {
                        user.setPwd(e.getUserPassword());
                    }
                    if (e.getUsersecretKey() != null) {
                        user.setSalt(e.getUsersecretKey());
                    }
                    if (e.getBeginLearnDate() != null) {
                        user.setRegist(e.getBeginLearnDate());
                    }
                    //employee表：
                    //departmentName gradeName majorName className political
                    //liveRoom homeAddress zipCode mailEcomm
                    if (e.getDepartmentName() != null) {
                        //为专业服务的
                        departments = departmentService.selectDepartmentByName(e.getDepartmentName());
                        if (departments.size() >= 1) {
                            department = departments.get(0);
                            employee.setDepartmentId(department.getId());
                        }
                    }
                    if (e.getSubdepartmentName()!=null){
                        Subdepartment subdepartment = otherSubdepartmentService.selectBySubdepartmentName(e.getSubdepartmentName());
                        employee.setSubdepartmentId(subdepartment.getId());
                    }
                    if (e.getEmployeehistory()!=null){
                        List<EmployeeHistory> employeeHistories = employeeHistoryService.selectByUserId(user.getId());
                        if (employeeHistories.size()>0){
                            employeeHistories.get(0).setDescript(e.getEmployeehistory());
                            employeeHistory = employeeHistories.get(0);
                        }
                        employee.setEmployHistoryId(employeeHistory.getId());
                    }
                    if (e.getMajorName() != null) {
                        //student.setSpecialtyId();
                        if (e.getDepartmentName() == null) {
                            s.append("第" + i + "9行第列前提是不能为空");
                            flag = 1;
                        } else {
                            specialties = otherSpecialtyService.seclectByDIdAndSName(Long.valueOf(1), department.getId(), e.getMajorName());
                            if (specialties.size() >= 1) {
                                specialty = specialties.get(0);
                            }
                            student.setSpecialtyId(specialty.getId());
                        }
                    }
                    if (e.getPolitical() != null) {
                        //student.setPoliticalId();
                        politicalAffiliation = politicalAffiliationService.selectByPoliticalAffiliationName(e.getPolitical());
                        if (politicalAffiliation != null) {
                            student.setPoliticalId(politicalAffiliation.getId());
                        }
                    }
                    if (e.getPosition()!=null){
                        Position position = otherPositionService.selectPositionByPositionName(e.getPosition());
                    }
                    if (e.getHomeAddress() != null) {
                        String[] split = e.getHomeAddress().split("/");
                        List<Address> addresses = addressService.selectByUserId(user.getId());
                        if (addresses.size() > 0) {
                            if (e.getZipCode() != null) {
                                //student.set 地址需要zip
                                addresses.get(0).setZipCode(e.getZipCode());
                            }
                            int init = 0;//记录字符串循环的次数
                            for (String string : split) {
                                if (init == 0) {
                                    addrCountry = addrCountryService.selectCountryByName(string);
                                    if (addrCountry == null) {
                                        s.append("第" + i + "行第15列地址的国家不正确");
                                        flag = 1;
                                    } else {
                                        addresses.get(0).setCountry(addrCountry.getId());
                                    }
                                } else if (init == 1) {
                                    addrState = addrStateService.selectByAddrStateName(string);
                                    if (addrState == null) {
                                        s.append("第" + i + "行第15列地址的省份不正确");
                                        flag = 1;
                                    } else {
                                        addresses.get(0).setState(addrState.getId());
                                    }
                                } else if (init == 2) {
                                    addrCity = addrCityService.selectByCityName(string);
                                    if (addrCity == null) {
                                        s.append("第" + i + "行第15列地址的城市不正确");
                                        flag = 1;
                                    } else {
                                        addresses.get(0).setCity(addrCity.getId());
                                    }
                                } else if (init == 3) {
                                    addrArea = addrAreaService.selectByAreaName(string);
                                    if (addrArea == null) {
                                        s.append("第" + i + "行第15列地址的县/区不正确");
                                        flag = 1;
                                        addresses.get(0).setArea(addrArea.getId());
                                    }
                                } else if (init == 4) {
                                    addrStreet = addrStreetService.selectByAddrStreetName(string);
                                    if (addrStreet == null) {
                                        s.append("第" + i + "行第15列地址的街道不正确");
                                        flag = 1;
                                    } else {
                                        addresses.get(0).setStreet(addrStreet.getId());
                                    }
                                } else if (init == 5) {
                                    addressDetail = string;
                                    addresses.get(0).setDetail(addressDetail);
                                }
                                init++;
                            }
                        }
                    }
                    if (e.getMailEcomm() != null) {
//                        student.setPhoneEcommId();
                        //先查询出该用户的通讯方式
                        //再修改对应的通讯方式后再更新
                        List<Ecomm> ecomms = ecommService.selectValidEcomByUserId(user.getId());
                        if (ecomms.size() > 0) {
                            ecomms.get(0).setContent(e.getMailEcomm());
                            ecomm = ecomms.get(0);
                            //ecommService.update(ecomms.get(0));
                        }
                    }
                    if (flag != 1) {
                        //System.out.println(ecomm.toString());
                        try {
                            ecommService.update(ecomm);
                            addressService.update(address);
                            userService.updateUser(user);
                            employeeHistoryService.update(employeeHistory);
                            employeeService.updateEmployee(employee);
                            s.append("更新成功");
                        } catch (Exception ee) {
                            ee.printStackTrace();
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        }
                    }
                } else {
                    s.append("第" + i + "行第2、5列参数不正确，请改正");
                }
            } else {
                s.append("第" + i + "行第2、5列参数不能为空，请改正");
            }
        }
        return s;
    }

    /**
     * user:
     * userName identification userSex userBirthday userNumber userPassword usersecretKey
     * <p>
     * student:
     * beginLearnDate departmentName subdepartmentName majorName employeehistory political
     * position homeAddress zipCode mailEcomm
     */
    /**
     * Author: chenenru 17:06 2019/6/20
     * @param
     * @return
     * @apiNote: 校验批量更新的学生信息
     */
    public StringBuffer checkUpdateStudent(MultipartFile file) throws IOException {
        List<StudentModel> studentModels = ExcelUtil.readExcel(file.getInputStream(), StudentModel.class);
        int i = 0;
        int flag = 0;
        s.delete(0, s.length());
        douStuNo.clear();
        douIden.clear();
        for (StudentModel st : studentModels) {
            flag = 0;
            i++;
            if (douStuNo != null) {
                for (String string : douStuNo) {
                    if (string.equals(st.getUserNumber())) {
                        s.append("第").append(i).append("行第5列字段出现重复，请检查\n");
                        flag = 1;
                    }
                }
            }
            douStuNo.add(st.getUserNumber());

            if (douIden != null) {
                for (String string : douIden) {
                    if (string.equals(st.getIdentification())) {
                        s.append("第").append(i).append("行第2列字段出现重复，请检查\n");
                        flag = 1;
                    }
                }
            }
            douIden.add(st.getIdentification());
            if (st.getUserNumber() == null) {
                s.append("第").append(i).append("行第5列字段长度不能为空，请检查\n");
                flag = 1;
            }
            if (st.getIdentification() == null) {
                s.append("第").append(i).append("行第2列字段长度不能为空，请检查\n");
                flag = 1;
            }
            if (st.getIdentification().length() < 18 || st.getIdentification().length() > 22) {
                s.append("第").append(i).append("行第2列字段长度不符合，请检查\n");
            }
/*            if (flag == 1) {
                continue;
            }*/
            //根据学号+身份证号获取唯一的学生，就是该学生的学号+身份证号不能为空
            if (st.getUserNumber() != null && st.getIdentification() != null) {
                Student student = studentService.selectValidStuByStuNoAndUniId(st.getUserNumber(), Long.valueOf(1));
                User user = userService.selectUserByUniIdAndIde(Long.valueOf(1), st.getIdentification());
                if (student.getUserId().equals(user.getId())) {
                    if (st.getDepartmentName() != null) {
                        //为专业服务的
                        departments = departmentService.selectDepartmentByName(st.getDepartmentName());
                        System.out.println(departments.size());
                        if (departments.size() >= 1) {
                            department = departments.get(0);
                        }
                    }
                    System.out.println(department+"****");
                    if (st.getGradeName() != null) {
                        if (studentService.selectByGrade(st.getGradeName()).size() < 1) {
                            s.append("第" + i + "行第10列不正确，请检查\n");
                        }
                    }
                    System.out.println((department.getId()==null)+"<<======");
                    if (st.getMajorName() != null) {
                        if (department.getId()==null) {
                            s.append("第" + i + "行第9列不正确，请检查\n");
                            flag = 1;
                        } else {
                            specialties = otherSpecialtyService.seclectByDIdAndSName(Long.valueOf(1), department.getId(), st.getMajorName());
                            if (specialties.size() < 1) {
                                s.append("第" + i + "行第11列字段不正确，请检查\n");
                            }
                        }
                    }
                    if (st.getClassName() != null) {
                        aClass = classService.selectClassByName(st.getClassName());
                        if (aClass == null) {
                            s.append("第" + i + "行第12列字段不正确，请检查\n");
                        }
                    }
                    if (st.getPolitical() != null) {
                        politicalAffiliation = politicalAffiliationService.selectByPoliticalAffiliationName(st.getPolitical());
                        if (politicalAffiliation == null) {
                            s.append("第" + i + "行第13列字段不正确，请检查\n");
                        }
                    }
                    if (st.getHomeAddress() != null) {
                        String[] split = st.getHomeAddress().split("/");
                        List<Address> addresses = addressService.selectByUserId(user.getId());
                        if (addresses.size() > 0) {
                            if (st.getZipCode() == null) {
                                //student.set 地址需要zip
                                s.append("第" + i + "行第16列字段是15列的前提，不能为空\n");
                            }
                            int init = 0;//记录字符串循环的次数
                            for (String string : split) {
                                if (init == 0) {
                                    addrCountry = addrCountryService.selectCountryByName(string);
                                    if (addrCountry == null) {
                                        s.append("第" + i + "行第15列地址的国家不正确");
                                        flag = 1;
                                    }
                                } else if (init == 1) {
                                    addrState = addrStateService.selectByAddrStateName(string);
                                    if (addrState == null) {
                                        s.append("第" + i + "行第15列地址的省份不正确");
                                        flag = 1;
                                    }
                                } else if (init == 2) {
                                    addrCity = addrCityService.selectByCityName(string);
                                    if (addrCity == null) {
                                        s.append("第" + i + "行第15列地址的城市不正确");
                                        flag = 1;
                                    }
                                } else if (init == 3) {
                                    addrArea = addrAreaService.selectByAreaName(string);
                                    if (addrArea == null) {
                                        s.append("第" + i + "行第15列地址的县/区不正确");
                                        flag = 1;
                                    }
                                } else if (init == 4) {
                                    addrStreet = addrStreetService.selectByAddrStreetName(string);
                                    if (addrStreet == null) {
                                        s.append("第" + i + "行第15列地址的街道不正确");
                                        flag = 1;
                                    }
                                }
                                init++;
                            }
                        }
                    }
                    if (st.getZipCode() != null) {
                        if (st.getZipCode().length() < 6) {
                            s.append("第" + i + "行第16列字段长度不正确，请检查\n");
                        }
                    }
                    if (st.getMailEcomm() != null) {
                        if (st.getMailEcomm().length() != 11) {
                            s.append("第" + i + "行第17列字段不正确，请检查\n");
                        }
                    }
                } else {
                    s.append("第" + i + "行第2、5列参数不正确，请改正");
                }
            } else {
                s.append("第" + i + "行第2、5列参数不能为空，请改正");
            }
        }
        if (s == null) {
            s.append("校验通过");
        }
        return s;
    }


    /**
     * Author: chenenru 17:07 2019/6/20
     * @param
     * @return
     * @apiNote: 更新批量的学生信息
     */
    @Transactional(rollbackFor = {Exception.class})
    public StringBuffer UpdateStudent(MultipartFile file) throws IOException {
        List<StudentModel> studentModels = ExcelUtil.readExcel(file.getInputStream(), StudentModel.class);
        int i = 0;
        int flag = 0;
        s.delete(0, s.length());
        douStuNo.clear();
        douIden.clear();
        for (StudentModel st : studentModels) {
            flag = 0;
            i++;
            if (douStuNo != null) {
                for (String string : douStuNo) {
                    if (string.equals(st.getUserNumber())) {
                        s.append("第").append(i).append("行第5列字段出现重复，请检查\n");
                        flag = 1;
                    }
                }
            }
            douStuNo.add(st.getUserNumber());

            if (douIden != null) {
                for (String string : douIden) {
                    if (string.equals(st.getIdentification())) {
                        s.append("第").append(i).append("行第2列字段出现重复，请检查\n");
                        flag = 1;
                    }
                }
            }
            douIden.add(st.getIdentification());
            if (flag == 1) {
                continue;
            }
            //根据学号+身份证号获取唯一的学生，就是该学生的学号+身份证号不能为空
            if (st.getUserNumber() != null && st.getIdentification() != null) {
                Student student = studentService.selectValidStuByStuNoAndUniId(st.getUserNumber(), Long.valueOf(1));
                User user = userService.selectUserByUniIdAndIde(Long.valueOf(1), st.getIdentification());
                if (student.getUserId().equals(user.getId())) {
                    if (st.getUserName() != null) {
                        user.setUserName(st.getUserName());
                    }
                    if (st.getIdentification() != null) {
                        user.setIdentification(st.getIdentification());
                    }
                    if (st.getUserSex() != null) {
                        if (st.getUserSex().equals("男")) {
                            user.setUserSex(0);
                        } else {
                            user.setUserSex(1);
                        }
                    }
                    if (st.getUserBirthday() != null) {
                        user.setUserBirthday(st.getUserBirthday());
                    }
                    if (st.getUserNumber() != null) {
                        user.setName(st.getUserNumber());
                    }
                    if (st.getUserPassword() != null) {
                        user.setPwd(st.getUserPassword());
                    }
                    if (st.getUsersecretKey() != null) {
                        user.setSalt(st.getUsersecretKey());
                    }
                    //student表：
                    //beginLearnDate departmentName gradeName majorName className political
                    //liveRoom homeAddress zipCode mailEcomm
                    if (st.getBeginLearnDate() != null) {
                        student.setBeginLearnDate(st.getBeginLearnDate());
                    }
                    if (st.getDepartmentName() != null) {
                        //为专业服务的
                        departments = departmentService.selectDepartmentByName(st.getDepartmentName());
                        if (departments.size() >= 1) {
                            department = departments.get(0);
                        }
                    }
                    if (st.getGradeName() != null) {
                        student.setGrade(st.getGradeName());
                    }
                    if (st.getMajorName() != null) {
                        //student.setSpecialtyId();
                        if (st.getDepartmentName() == null) {
                            s.append("第" + i + "9行第列前提是不能为空");
                            flag = 1;
                        } else {
                            specialties = otherSpecialtyService.seclectByDIdAndSName(Long.valueOf(1), department.getId(), st.getMajorName());
                            if (specialties.size() >= 1) {
                                specialty = specialties.get(0);
                            }
                            student.setSpecialtyId(specialty.getId());
                        }
                    }
                    if (st.getClassName() != null) {
                        aClass = classService.selectClassByName(st.getClassName());
                        student.setClassId(aClass.getId());
                        //student.setClassId();
                    }
                    if (st.getPolitical() != null) {
                        //student.setPoliticalId();
                        politicalAffiliation = politicalAffiliationService.selectByPoliticalAffiliationName(st.getPolitical());
                        if (politicalAffiliation != null) {
                            student.setPoliticalId(politicalAffiliation.getId());
                        }
                    }
                    if (st.getLiveRoom() != null) {
                        student.setLiveRoom(Long.valueOf(1));//先写死
                    }
                    if (st.getHomeAddress() != null) {
                        String[] split = st.getHomeAddress().split("/");
                        List<Address> addresses = addressService.selectByUserId(user.getId());
                        if (addresses.size() > 0) {
                            if (st.getZipCode() != null) {
                                //student.set 地址需要zip
                                addresses.get(0).setZipCode(st.getZipCode());
                            }
                            int init = 0;//记录字符串循环的次数
                            for (String string : split) {
                                if (init == 0) {
                                    addrCountry = addrCountryService.selectCountryByName(string);
                                    if (addrCountry == null) {
                                        s.append("第" + i + "行第15列地址的国家不正确");
                                        flag = 1;
                                    } else {
                                        addresses.get(0).setCountry(addrCountry.getId());
                                    }
                                } else if (init == 1) {
                                    addrState = addrStateService.selectByAddrStateName(string);
                                    if (addrState == null) {
                                        s.append("第" + i + "行第15列地址的省份不正确");
                                        flag = 1;
                                    } else {
                                        addresses.get(0).setState(addrState.getId());
                                    }
                                } else if (init == 2) {
                                    addrCity = addrCityService.selectByCityName(string);
                                    if (addrCity == null) {
                                        s.append("第" + i + "行第15列地址的城市不正确");
                                        flag = 1;
                                    } else {
                                        addresses.get(0).setCity(addrCity.getId());
                                    }
                                } else if (init == 3) {
                                    addrArea = addrAreaService.selectByAreaName(string);
                                    if (addrArea == null) {
                                        s.append("第" + i + "行第15列地址的县/区不正确");
                                        flag = 1;
                                        addresses.get(0).setArea(addrArea.getId());
                                    }
                                } else if (init == 4) {
                                    addrStreet = addrStreetService.selectByAddrStreetName(string);
                                    if (addrStreet == null) {
                                        s.append("第" + i + "行第15列地址的街道不正确");
                                        flag = 1;
                                    } else {
                                        addresses.get(0).setStreet(addrStreet.getId());
                                    }
                                } else if (init == 5) {
                                    addressDetail = string;
                                    addresses.get(0).setDetail(addressDetail);
                                }
                                init++;
                            }
                        }
                    }
                    if (st.getMailEcomm() != null) {
//                        student.setPhoneEcommId();
                        //先查询出该用户的通讯方式
                        //再修改对应的通讯方式后再更新
                        List<Ecomm> ecomms = ecommService.selectValidEcomByUserId(user.getId());
                        if (ecomms.size() > 0) {
                            ecomms.get(0).setContent(st.getMailEcomm());
                            ecomm = ecomms.get(0);
                            //ecommService.update(ecomms.get(0));
                        }
                    }
                    if (flag != 1) {
                        //System.out.println(ecomm.toString());
                        try {
                            ecommService.update(ecomm);
                            addressService.update(address);
                            userService.updateUser(user);
                            studentService.update(student);
                            s.append("更新成功");
                        } catch (Exception e) {
                            e.printStackTrace();
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        }
                    }
                } else {
                    s.append("第" + i + "行第2、5列参数不正确，请改正");
                }
            } else {
                s.append("第" + i + "行第2、5列参数不能为空，请改正");
            }
        }
        return s;
    }

    /**
     * 根据属性名获取属性值
     */
    private Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new java.lang.Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取属性名数组
     */
    private String[] getFiledName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            System.out.print(fields[i].getType());
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     */
    private List getFiledsInfo(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        List list = new ArrayList();
        Map infoMap = null;
        for (int i = 0; i < fields.length; i++) {
            infoMap = new HashMap();
            infoMap.put("type", fields[i].getType().toString());
            infoMap.put("name", fields[i].getName());
            infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
            list.add(infoMap);
        }
        return list;
    }

    /**
     * 获取对象的所有属性值，返回一个对象数组
     */
    public Object[] getFiledValues(Object o) {
        String[] fieldNames = this.getFiledName(o);
        Object[] value = new Object[fieldNames.length];
        for (int i = 0; i < fieldNames.length; i++) {
            value[i] = this.getFieldValueByName(fieldNames[i], o);
        }
        return value;
    }
}
