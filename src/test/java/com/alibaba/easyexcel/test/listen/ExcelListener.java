package com.alibaba.easyexcel.test.listen;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelListener extends AnalysisEventListener {


    private List<Object>  data = new ArrayList<Object>();

    @Override
    public void invoke(Object object, AnalysisContext context) {
        //System.out.println(context.getCurrentSheet());
        data.add(object);
        if(data.size()>=100){
            doSomething();
            data = new ArrayList<Object>();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        doSomething();
    }
    public void doSomething(){
        for (Object o:data) {
            System.out.print(getFieldValueByName("userName", o)+" ");
            System.out.print(getFieldValueByName("identification", o)+" ");
            System.out.print(getFieldValueByName("userSex", o)+" ");
            System.out.print(getFieldValueByName("userBirthday", o)+" ");
            System.out.print(getFieldValueByName("userNumber", o)+" ");
            System.out.print(getFieldValueByName("userPassword", o)+" ");
            System.out.print(getFieldValueByName("usersecretKey", o)+" ");
            System.out.print(getFieldValueByName("beginLearnDate", o)+" ");
            System.out.print(getFieldValueByName("departmentName", o)+" ");
            System.out.print(getFieldValueByName("gradeName", o)+" ");
            System.out.print(getFieldValueByName("majorName", o)+" ");
            System.out.print(getFieldValueByName("className", o)+" ");
            System.out.print(getFieldValueByName("political", o)+" ");
            System.out.print(getFieldValueByName("liveRoom", o)+" ");
            System.out.print(getFieldValueByName("homeAddress", o)+" ");
            System.out.print(getFieldValueByName("mailEcomm", o)+" ");
            System.out.println();
//           System.out.print(getFiledValues(o));
            //System.out.println(getFiledName(o).toString());
            //System.out.println(o);
        }
    }
    /**
     * 根据属性名获取属性值
     * */
    private Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取属性名数组
     * */
    private String[] getFiledName(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        for(int i=0;i<fields.length;i++){
           System.out.print(fields[i].getType());
            fieldNames[i]=fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     * */
    private List getFiledsInfo(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        List list = new ArrayList();
        Map infoMap=null;
        for(int i=0;i<fields.length;i++){
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
     * */
    public Object[] getFiledValues(Object o){
        String[] fieldNames=this.getFiledName(o);
        Object[] value=new Object[fieldNames.length];
        for(int i=0;i<fieldNames.length;i++){
            value[i]=this.getFieldValueByName(fieldNames[i], o);
        }
        return value;
    }
}
