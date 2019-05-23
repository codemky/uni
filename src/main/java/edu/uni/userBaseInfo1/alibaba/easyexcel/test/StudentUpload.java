package edu.uni.userBaseInfo1.alibaba.easyexcel.test;

import edu.uni.administrativestructure.bean.Class;
import edu.uni.administrativestructure.bean.Department;
import edu.uni.administrativestructure.bean.Position;
import edu.uni.administrativestructure.bean.Subdepartment;
import edu.uni.administrativestructure.service.PositionService;
import edu.uni.administrativestructure.service.SubdepartmentService;
import edu.uni.professionalcourses.bean.Specialty;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.AddressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
    private SubdepartmentService Ssubdepartment;
    @Autowired
    private EmployeeHistoryService EemployeeHistory;
    @Autowired
    private PositionService Pposition;
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
    private static OtherSpecialtyService specialtyService;
    private static UserService userService;
    private static EcommService ecommService;
    private static StudentService studentService;
    private static SubdepartmentService subdepartmentService;
    private static EmployeeHistoryService employeeHistoryService;
    private static PositionService positionService;
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
        specialtyService = this.Sspecialty;
        userService = this.Uuser;
        ecommService = this.Eecomm;
        studentService = this.Sstudent;
        subdepartmentService = this.Ssubdepartment;
        employeeHistoryService = this.EemployeeHistory;
        positionService = this.Pposition;
        employeeService = this.Eemployee;
        //initData();
    }

    private List<Object> data = new ArrayList<Object>();
    int i = 0;//记录excel里面的是第几行
    int flag=0;//记录地址切割字符串到第几个字符
    StringBuffer s = new StringBuffer();
    List<String> allName = new ArrayList<>();
    List<Department> departments = new ArrayList<>();
    Department department = new Department();
    List<Specialty> specialties = new ArrayList<>();
    Specialty specialty = new Specialty();
    List<Class> classes = new ArrayList<>();
    Class aClass = new Class();
    List<PoliticalAffiliation> politicalAffiliations = new ArrayList<>();
    PoliticalAffiliation politicalAffiliation = new PoliticalAffiliation();
    AddressUtil addressUtil = new AddressUtil();
    List<AddrCountry> addrCountries = new ArrayList<>();
    AddrCountry addrCountry = new AddrCountry();
    List<AddrState> addrStates = new ArrayList<>();
    AddrState addrState =new AddrState();
    List<AddrCity> addrCities = new ArrayList<>();
    AddrCity addrCity = new AddrCity();
    List<AddrArea> addrAreas = new ArrayList<>();
    AddrArea addrArea = new AddrArea();
    List<AddrStreet> addrStreets = new ArrayList<>();
    AddrStreet addrStreet = new AddrStreet();
    Ecomm ecomm = new Ecomm();
    String addressDetail = new String();
    Address address = new Address();
    User user = new User();
    Student student = new Student();
    Employee employee = new Employee();
    List<Subdepartment> subdepartments = new ArrayList<>();
    Subdepartment subdepartment= new Subdepartment();
    List<EmployeeHistory> employeeHistories = new ArrayList<>();
    EmployeeHistory employeeHistory = new EmployeeHistory();
    List<Position> positions = new ArrayList<>();
    Position position = new Position();
    String userNumber =new String();
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
    /**
     * Author: chenenru 15:24 2019/5/21
     * @param
     * @return
     * @apiNote: 向数据库插入批量审批通过的学生账号
     */
    public  StringBuffer insertStudent(Object o){
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

        classes =  classService.selectClassByName(className);
         if (classes.size()>=1){
             aClass = classes.get(0);
         }

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
     * @param
     * @return
     * @apiNote: 向数据库插入批量审批通过的职员账号
     */
    public StringBuffer insertEmployee(Object o){
        s.delete(0, s.length());
        //先向user表插入新的记录，根据userId向student表插入时记录时关联到外键
        insertUser(o);
        //再向employee表插入记录
        //所属科室、系
        String subdepartmentName = (String) getFieldValueByName("subdepartmentName", o);
        subdepartments = subdepartmentService.selectAll();
        for (Subdepartment s:subdepartments) {
            if (s.getName().equals(subdepartmentName)){
                subdepartment = s;
            }
        }
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
        positions = positionService.selectAll();
        for (Position p:positions) {
            if (p.getName().equals(pposition)){
                position = p;
            }
        }

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
     * @param o
     * @return
     * @apiNote: 向用户表插入记录
     */
    public StringBuffer insertUser(Object o){
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
        if (userSex.equals("男")){
            user.setUserSex(1);
        }else{
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
        if(b==true){
            s.append("插入成功");
            //System.out.println("插入成功");
        }else{
            s.append("插入失败");
            //System.out.println("插入失败");
        }
        return s;
    }

    /**
     * Author: chenenru 0:01 2019/5/23
     * @param
     * @return
     * @apiNote: 插入相同的部分
     */
    public StringBuffer insertSame(Object o){
        //学院名
        departmentName = (String) getFieldValueByName("departmentName", o);
        departments = departmentService.selectDepartmentByName(departmentName);
        if (departments.size()>=1){
            department =  departments.get(0);
        }
        //主修专业
        majorName = (String) getFieldValueByName("majorName", o);
        specialties = specialtyService.seclectByDIdAndSName(Long.valueOf(1), department.getId(), majorName);
        if (specialties.size()>=1){
            specialty = specialties.get(0);
        }
        //政治面貌
        political = (String) getFieldValueByName("political", o);
        politicalAffiliations =  politicalAffiliationService.selectAllPoliticalAffiliations();
        for (PoliticalAffiliation po:politicalAffiliations) {
            if (po.getPolitical().equals(political)){
                politicalAffiliation = po;
                //System.out.println("-->"+political+"-->"+po+"------>"+politicalAffiliation);
            }
        }
        //家庭住址
        homeAddress = (String) getFieldValueByName("homeAddress", o);
        String[] split = homeAddress.split("/");
        int init=0;//记录字符串循环的次数
        flag = 0;
        for (String string:split) {
            if (init==0){
                addrCountries = addrCountryService.selectAllAddrCountrys();
                for (AddrCountry a:addrCountries) {
                    //System.out.println("国家："+a);
                    if (string.equals(a.getCountryZh())){
                        flag=1;
                        addrCountry=a;
                        break;
                    }
                }
                if (flag!=1){
                    s.append("插入地址时找不到国家");
                }
            }else if (init==1&&flag==1){
                //System.out.println("国家："+addrCountry.toString());
                addrStates = addrStateService.selectAllAddrStatesByCountryCode(addrCountry.getCode());
                for (AddrState a:addrStates) {
                    if (string.equals(a.getStateZh())){
                        addrState = a;
                        flag=2;
                        break;
                    }
                }
                if (flag!=2){
                    s.append("插入地址时找不到省份");
                }
            }else if (init==2&&flag==2){
                //System.out.println("省份："+addrState.toString());
                addrCities = addressUtil.SelectCities(addrState.getCode());
                for (AddrCity a:addrCities) {
                    //System.out.println("国家："+a);
                    if (string.equals(a.getCityZh())){
                        addrCity = a;
                        flag=3;
                        break;
                    }
                }
                if (flag!=3){
                    s.append("插入地址时找不到城市");
                }
            }else if (init==3&&flag==3){
                //System.out.println("城市："+addrCity);
                addrAreas = addressUtil.SelectAreas(addrCity.getCode());
                for (AddrArea a:addrAreas) {
                    //System.out.println("国家："+a);
                    if (string.equals(a.getAreaZh())){
                        addrArea = a;
                        flag=4;
                        break;
                    }
                }
                if (flag!=4){
                    s.append("插入地址时找不到县/区");
                }
            }
            else if (init==4&&flag==4){
                //System.out.println("县/区："+addrArea.toString());
                addrStreets = addressUtil.SelectStreets(addrArea.getCode());
                for (AddrStreet a:addrStreets) {
                    //System.out.println("街道："+a);
                    if (string.equals(a.getStreetZh())){
                        addrStreet = a;
                        flag=5;
                        break;
                    }
                }
                if (flag!=5){
                    s.append("插入地址时找不到街道");
                }
            }else if (init==5&&flag==5){
                addressDetail = string;
                flag=6;
                break;
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
     * @param
     * @return
     * @apiNote: 用于校验用户上传批量导入的学生模板
     */
    public StringBuffer checkoutStudent(Object o){
        //s.delete(0, s.length());
        i++;
        flag=0;
        s.delete(0, s.length());
        checkoutUser(o);

        checkoutSame(o);

        //校验年级 这个可以不用校验，因为年级可以添加新的
        System.out.print(getFieldValueByName("gradeName", o) + " ");
        String gradeName = (String) getFieldValueByName("gradeName", o);

        //校验班级 前提是二级学院名称要写对
        System.out.print(getFieldValueByName("className", o) + " ");
        String className = (String) getFieldValueByName("className", o);
        if(departments!=null||!departments.equals("")){
            allName.clear();
            for (Department d:departments) {
                classes = classService.selectAllClassByDepartmentId(d.getId());
                if(classes==null||classes.equals("")){
                    continue;
                }else{
                    //allName.clear();
                    for (Class c : classes) {
                        allName.add(c.getName());
                    }
                }
            }
            if (allName.contains(className)) {
//                System.out.print(getFieldValueByName("className", o) + " ");
            } else {
                s.append("第" + i + "行第12列字段不符合，请检查\n");
            }
        }else {
            s.append("该学院没有此班级");
        }

        //校验宿舍地址
        System.out.print(getFieldValueByName("liveRoom", o) + " ");
        String liveRoom = (String) getFieldValueByName("liveRoom", o);

        /*//校验邮政编码
        String zipCode = (String) getFieldValueByName("zipCode", o);
        System.out.print(getFieldValueByName("zipCode", o) + " ");
        if (zipCode.length() != 6) {
            s.append("第" + i + "行第16列字段不符合，请检查\n");
        }*/

        System.out.println();
        return s;
    }
    /**
     * Author: chenenru 12:34 2019/5/23
     * @param
     * @return
     * @apiNote: 用于校验用户上传批量导入的教师模板
     */
    public StringBuffer checkoutEmployee(Object o){
        i++;
        flag=0;
        s.delete(0, s.length());
        checkoutUser(o);

        checkoutSame(o);

        //校验所属科室、系
        String subdepartmentName = (String) getFieldValueByName("subdepartmentName", o);
        System.out.print(subdepartmentName+" ");
        subdepartments = subdepartmentService.selectAll();
        flag=0;
        for (Subdepartment s:subdepartments) {
            if (s.getName().equals(subdepartmentName)){
                subdepartment = s;
                flag=1;
            }
        }
        if (flag==0){
            s.append("第" + i + "行第10列字段不符合，请检查\n");
        }
        //校验职员简历，不用校验
        String employeehistory = (String) getFieldValueByName("employeehistory", o);
        System.out.print(employeehistory+" ");
        //校验行政岗位
        String pposition = (String) getFieldValueByName("position", o);
        System.out.print(pposition+" ");
        positions = positionService.selectAll();
        flag=0;
        for (Position p:positions) {
            if (p.getName().equals(pposition)){
                position = p;
                flag=1;
            }
        }
        if (flag==0){
            s.append("第" + i + "行第14列字段不符合，请检查\n");
        }

        System.out.println();

        return s;
    }

    /**
     * Author: chenenru 23:30 2019/5/22
     * @param o
     * @return StringBuffer
     * @apiNote: 校验user表
     */
    public StringBuffer checkoutUser(Object o){
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


        System.out.print(getFieldValueByName("userNumber", o) + " ");
        //校验学号
        String userNumber = (String) getFieldValueByName("userNumber", o);
        //System.out.println("长度：---》"+userNumber.length());
        if (userNumber.length() != 12 && userNumber.length()!=10) {
            s.append("第").append(i).append("行第5列字段长度不符合，请检查\n");
            //System.exit(0);
        }


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
     * @param o
     * @return StringBuffer
     * @apiNote: 校验共同部分
     */
    public StringBuffer checkoutSame(Object o){

        //校验学院:1.根据session里面的universityId确认上传者的学校的id,再根据学校的id查询所有的二级学院
        System.out.print(getFieldValueByName("departmentName", o) + " ");
        departmentName = (String) getFieldValueByName("departmentName", o);
        departments = departmentService.selectAllValidDepartment(Long.valueOf(1));
        allName.clear();
        for (Department department : departments) {
            allName.add(department.getName());
        }
        //System.out.println(allName.contains(departmentName));
        if (allName.contains(departmentName)) {
//            System.out.print(getFieldValueByName("departmentName", o) + " ");
        } else {
            s.append("第" + i + "行第9列字段不符合，请检查\n");
        }

        //校验专业  前提是二级学院名称要写对
        System.out.print(getFieldValueByName("majorName", o) + " ");
        majorName = (String) getFieldValueByName("majorName", o);
        //specialties = new ArrayList<>();
        flag=0;
        if(departments!=null||!departments.equals("")){
           // System.out.println("\n这是学院："+departments.toString()+"  --->"+majorName);
            for (Department d:departments) {
                specialties = specialtyService.seclectByDIdAndSName(Long.valueOf(1), d.getId(), majorName);
                for (Specialty sp:specialties) {
                    //System.out.println("\n这是专业："+specialties.toString());
                    if(sp!=null||!sp.equals("")){
                        //System.out.println(" --->"+flag);
                        flag=1;
                        //System.out.println(" --->>"+flag);
                        break;
                    }
                }
                if (flag==1){
                    break;
                }
            }
        }
        if (flag==0){
            s.append("第" + i + "行第11列字段不符合，请检查\n");
        }

        //校验政治面貌
        System.out.print(getFieldValueByName("political", o) + " ");
        political = (String) getFieldValueByName("political", o);
        allName.clear();
        politicalAffiliations = politicalAffiliationService.selectAllPoliticalAffiliations();
        for (PoliticalAffiliation po : politicalAffiliations) {
            allName.add(po.getPolitical());
        }
        if (allName.contains(political)) {
//            System.out.print(getFieldValueByName("political", o) + " ");
        } else {
            s.append("第" + i + "行第13列字段不符合，请检查\n");
        }

        //校验家庭地址
        System.out.print(getFieldValueByName("homeAddress", o) + " ");
        homeAddress = (String) getFieldValueByName("homeAddress", o);
        String[] split = homeAddress.split("/");
        int init=0;
        flag = 0;
        for (String string:split) {
            if (init==0){
                addrCountries = addrCountryService.selectAllAddrCountrys();
                for (AddrCountry a:addrCountries) {
                    //System.out.println("国家："+a.getCountryZh()+"  -->"+string);
                    if (string.equals(a.getCountryZh())){
                        flag=1;
                        addrCountry=a;
                        break;
                    }
                }
                if (flag!=1){
                    s.append("第" + i + "行第15列地址的国家不正确");
                }
            }else if (init==1&&flag==1){
                //System.out.println("国家："+addrCountry.toString());
                addrStates = addrStateService.selectAllAddrStatesByCountryCode(addrCountry.getCode());
                for (AddrState a:addrStates) {
                    if (string.equals(a.getStateZh())){
                        addrState = a;
                        flag=2;
                        break;
                    }
                }
                if (flag!=2){
                    s.append("第" + i + "行第15列地址的省份不正确");
                }
            }else if (init==2&&flag==2){
                //System.out.println("省份："+addrState.toString());
                addrCities = addressUtil.SelectCities(addrState.getCode());
                for (AddrCity a:addrCities) {
                    //System.out.println("国家："+a);
                    if (string.equals(a.getCityZh())){
                        addrCity = a;
                        flag=3;
                        break;
                    }
                }
                if (flag!=3){
                    s.append("第" + i + "行第15列地址的城市不正确");
                }
            }else if (init==3&&flag==3){
                //System.out.println("城市："+addrCity);
                addrAreas = addressUtil.SelectAreas(addrCity.getCode());
                for (AddrArea a:addrAreas) {
                    //System.out.println("国家："+a);
                    if (string.equals(a.getAreaZh())){
                        addrArea = a;
                        flag=4;
                        break;
                    }
                }
                if (flag!=4){
                    s.append("第" + i + "行第15列地址的县/区不正确");
                }
            }
            else if (init==4&&flag==4){
                //System.out.println("县/区："+addrArea.toString());
                addrStreets = addressUtil.SelectStreets(addrArea.getCode());
                for (AddrStreet a:addrStreets) {
                    //System.out.println("街道："+a);
                    if (string.equals(a.getStreetZh())){
                        addrStreet = a;
                        flag=5;
                        break;
                    }
                }
                if (flag!=5){
                    s.append("第" + i + "行第15列地址的街道不正确");
                }
            }/*else if (init==5&&flag==5){

            }*/
            init++;
        }

        //校验邮政编码
        String zipCode = (String) getFieldValueByName("zipCode", o);
        System.out.print(getFieldValueByName("zipCode", o) + " ");
        if (zipCode.length() != 6) {
            s.append("第" + i + "行第16列字段不符合，请检查\n");
        }

        //校验通信方式
        System.out.print(getFieldValueByName("mailEcomm", o)+" ");
        mailEcomm = (String) getFieldValueByName("mailEcomm", o);
        if (mailEcomm.length() != 11) {
            s.append("第" + i + "行第17列字段不符合，请检查\n");
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
