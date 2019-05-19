package edu.uni.userBaseInfo1.alibaba.easyexcel.test.listen;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.bean.Class;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.AddressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@Component
public class ExcelListener extends AnalysisEventListener {

    @Autowired
    private DepartmentService department;
    @Autowired
    private ClassService cclass;
    @Autowired
    private PoliticalAffiliationService politicalAffiliation;
    @Autowired
    private AddrCountryService addrCountry;
    @Autowired
    private AddrStateService addrState;
    @Autowired
    private AddrCityService addrCity;
    @Autowired
    private AddrAreaService addrArea;
    @Autowired
    private AddrStreetService addrStreet;
    @Autowired
    private SpecialtyService specialty;

    private static AddrCountryService addrCountryService;
    private static AddrStateService addrStateService;
    private static AddrCityService addrCityService;
    private static AddrAreaService addrAreaService;
    private static AddrStreetService addrStreetService;
    private static DepartmentService departmentService;
    private static ClassService classService;
    private static PoliticalAffiliationService politicalAffiliationService;
    private static SpecialtyService specialtyService;

    @PostConstruct
    public void init() {
        departmentService = this.department;
        classService = this.cclass;
        politicalAffiliationService = this.politicalAffiliation;
        addrCountryService = this.addrCountry;
        addrStateService = this.addrState;
        addrCityService = this.addrCity;
        addrAreaService = this.addrArea;
        addrStreetService = this.addrStreet;
        specialtyService = this.specialty;
        //initData();
    }

    private List<Object> data = new ArrayList<Object>();
    int i = 0;
    int flag=0;
    StringBuffer s = new StringBuffer();
    List<String> allName = new ArrayList<>();

    @Override
    public void invoke(Object o, AnalysisContext context) {
        System.out.println("先执行这个？.....");
        i++;
        flag=0;

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
        Date userBirthday = (Date) getFieldValueByName("userBirthday", o);


        System.out.print(getFieldValueByName("userNumber", o) + " ");
        //校验学号
        String userNumber = (String) getFieldValueByName("userNumber", o);
        if (userNumber.length() != 12) {
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


        //校验学院:1.根据session里面的universityId确认上传者的学校的id,再根据学校的id查询所有的二级学院
        System.out.print(getFieldValueByName("departmentName", o) + " ");
        String departmentName = (String) getFieldValueByName("departmentName", o);
        List<Department> departments = departmentService.selectAllValidDepartment(Long.valueOf(1));
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


        //校验年级 这个可以不用校验，因为年级可以添加新的
        System.out.print(getFieldValueByName("gradeName", o) + " ");
        String gradeName = (String) getFieldValueByName("gradeName", o);


        //校验专业  前提是二级学院名称要写对
        System.out.print(getFieldValueByName("majorName", o) + " ");
        String majorName = (String) getFieldValueByName("majorName", o);
        List<Specialty> specialties = new ArrayList<>();
        flag=0;
        if(departments!=null||!departments.equals("")){
            //System.out.println("\n这是学院："+departments.toString()+"  "+majorName);
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


        //校验班级 前提是二级学院名称要写对
        System.out.print(getFieldValueByName("className", o) + " ");
        String className = (String) getFieldValueByName("className", o);
        if(departments!=null||!departments.equals("")){
            allName.clear();
            for (Department d:departments) {
                List<Class> classes = classService.selectAllClassByDepartmentId(d.getId());
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


        //校验政治面貌
        System.out.print(getFieldValueByName("political", o) + " ");
        String political = (String) getFieldValueByName("political", o);
        allName.clear();
        List<PoliticalAffiliation> politicalAffiliations = politicalAffiliationService.selectAllPoliticalAffiliations();
        for (PoliticalAffiliation po : politicalAffiliations) {
            allName.add(po.getPolitical());
        }
        if (allName.contains(political)) {
//            System.out.print(getFieldValueByName("political", o) + " ");
        } else {
            s.append("第" + i + "行第13列字段不符合，请检查\n");
        }


        //校验宿舍地址
        System.out.print(getFieldValueByName("liveRoom", o) + " ");
        String liveRoom = (String) getFieldValueByName("liveRoom", o);


        //校验家庭地址
        System.out.print(getFieldValueByName("homeAddress", o) + " ");
        String homeAddress = (String) getFieldValueByName("homeAddress", o);
        String[] split = homeAddress.split("/");
        int init=0;
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
                    s.append("第" + i + "行第15列地址的国家不正确");
                }
            }else if (init==1&&flag==1){
                System.out.println("国家："+addrCountry.toString());
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
                System.out.println("省份："+addrState.toString());
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
                System.out.println("城市："+addrCity);
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
                System.out.println("县/区："+addrArea.toString());
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
            }
            init++;
        }

        //校验通信方式
        System.out.println(getFieldValueByName("mailEcomm", o));
        String mailEcomm = (String) getFieldValueByName("mailEcomm", o);
        if (mailEcomm.length() != 11) {
            s.append("第" + i + "行第16列字段不符合，请检查\n");
        }


        System.out.println();

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("再执行这个？......");
        doSomething();
    }

    public void doSomething() {
        System.out.println(s);
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
