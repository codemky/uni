package edu.uni.userBaseInfo1.alibaba.easyexcel.test.listen;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import edu.uni.userBaseInfo1.bean.Class;
import edu.uni.userBaseInfo1.bean.Department;
import edu.uni.userBaseInfo1.bean.PoliticalAffiliation;
import edu.uni.userBaseInfo1.service.*;
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

    private static AddrCountryService addrCountryService;
    private static AddrStateService addrStateService;
    private static AddrCityService addrCityService;
    private static AddrAreaService addrAreaService;
    private static AddrStreetService addrStreetService;
    private static DepartmentService departmentService;
    private static ClassService classService;
    private static PoliticalAffiliationService politicalAffiliationService;

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
        //initData();
    }

    private List<Object> data = new ArrayList<Object>();
    int i = 0;
    StringBuffer s = new StringBuffer();
    List<String> allName = new ArrayList<>();

    @Override
    public void invoke(Object o, AnalysisContext context) {
        //System.out.println(context.getCurrentSheet());
        System.out.println("先执行这个？.....");
        /*for (Object o:data) {
            System.out.println("000000"+o.toString());
        }*/
        //data.add(object);
        /*if(data.size()>=100){
            doSomething();
            data = new ArrayList<Object>();
        }*/
        //doSomething();
        i++;
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
            //System.out.print("第"+i+"行第5列字段长度不符合，请检查");
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
        System.out.print(getFieldValueByName("departmentName", o) + " ");
        //校验学院:1.根据session里面的universityId确认上传者的学校的id,再根据学校的id查询所有的二级学院
        String departmentName = (String) getFieldValueByName("departmentName", o);
        List<Department> departments = departmentService.selectAllValidDepartment(Long.valueOf(1));
        for (Department department : departments) {
            allName.add(department.getName());
        }
        //System.out.println(allName.contains(departmentName));
        if (allName.contains(departmentName)) {
            System.out.print(getFieldValueByName("gradeName", o) + " ");
        } else {
            s.append("第" + i + "行第9列字段不符合，请检查\n");
        }
        //校验年级
        String gradeName = (String) getFieldValueByName("gradeName", o);
        System.out.print(getFieldValueByName("gradeName", o) + " ");
        List<Department> departmentss = departmentService.selectDepartmentByName(departmentName);
        List<Class> classes = classService.selectAllClassByDepartmentId(departmentss.get(0).getId());
        if (departmentss != null&&!departmentss.equals("")) {
            allName.clear();
            for (Class cclass : classes) {
                allName.add(cclass.getCyear().toString());
            }
            //System.out.println("11111"+departmentss);
            if (allName.contains(gradeName)) {
                System.out.print(getFieldValueByName("majorName", o) + " ");
            } else {
                s.append("第" + i + "行第10列字段不符合，请检查\n");
            }
        } else {
            s.append("第" + i + "行第10列字段不符合，请检查\n");
        }
        //校验专业
        String majorName = (String) getFieldValueByName("majorName", o);
        System.out.print(getFieldValueByName("className", o) + " ");
        //校验班级
        String className = (String) getFieldValueByName("className", o);
        List<Class> classes1 = classService.selectClassByName(className);
        allName.clear();
        for (Class cclass : classes) {
            allName.add(cclass.getName());
        }
        if (allName.contains(className)) {
            System.out.print(getFieldValueByName("political", o) + " ");
        } else {
            s.append("第" + i + "行第12列字段不符合，请检查\n");
        }
        //校验政治面貌
        String political = (String) getFieldValueByName("political", o);
        allName.clear();
        List<PoliticalAffiliation> politicalAffiliations = politicalAffiliationService.selectAllPoliticalAffiliations();
        for (PoliticalAffiliation po : politicalAffiliations) {
            allName.add(po.getPolitical());
        }
        if (allName.contains(political)) {
            System.out.print(getFieldValueByName("liveRoom", o) + " ");
        } else {
            s.append("第" + i + "行第13列字段不符合，请检查\n");
        }
        //校验宿舍地址
        String liveRoom = (String) getFieldValueByName("liveRoom", o);
        System.out.print(getFieldValueByName("homeAddress", o) + " ");
        //校验家庭地址
        String homeAddress = (String) getFieldValueByName("homeAddress", o);
        System.out.println(getFieldValueByName("mailEcomm", o));
        //校验通信方式
        String mailEcomm = (String) getFieldValueByName("mailEcomm", o);
        if (mailEcomm.length() != 11) {
            s.append("第" + i + "行第16列字段不符合，请检查\n");
        }
        System.out.println();
//           System.out.print(getFiledValues(o));
        //System.out.println(getFiledName(o).toString());
        //System.out.println(o);

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
