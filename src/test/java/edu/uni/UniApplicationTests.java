package edu.uni;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import edu.uni.userBaseInfo1.service.ApprovalMainService;
import edu.uni.userBaseInfo1.service.ApprovalStepInchargeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UniApplicationTests {

    @Autowired
    ApprovalStepInchargeService approvalStepInchargeService;

    @Autowired
    ApprovalMainService approvalMainService;


    @Test
    public void testImpl(){

        long main_id = 1;
        long step_id = 1;

//        approvalMainService.updateToInvalidById((long)5);

        System.out.println(approvalMainService.selectBySchoolId((long) 1));
        System.out.println(approvalMainService.selectBySchoolIdAndNameAndType((long) 1, "生" ,null));

//        System.out.println(approvalMainService.selectIdByName((long)2, "审批学生申请修改照片"));
//        long id = approvalMainService.selectIdByName((long)2,"审批学生申请修改照片");
//        System.out.println(approvalMainService.selectStepCntById(id));
//        System.out.println(approvalMainService.isAlreadyExist(id , "审批学生申请修改照片"));
//        System.out.println(approvalStepInchargeService.selectRoleIdByMainIdAndStep(id,2));
//        System.out.println(approvalStepInchargeService.selectByMainId(id).toString());


    }



    @Test
    public void contextLoads() throws IOException {

    }
    /**
     * 每行数据是List<String>无表头
     *
     * @throws IOException
     */
//    @Test
//    public void writeWithoutHead() throws IOException {
//        try (OutputStream out = new FileOutputStream("writeWithoutHead.xlsx");) {
//            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, false);
//            Sheet sheet1 = new Sheet(1, 0);
//            sheet1.setSheetName("sheet1");
//            List<List<String>> data = new ArrayList<>();
//            for (int i = 0; i < 100; i++) {
//                List<String> item = new ArrayList<>();
//                item.add("item0" + i);
//                item.add("item1" + i);
//                item.add("item2" + i);
//                data.add(item);
//            }
//            writer.write0(data, sheet1);
//            writer.finish();
//        }
//    }
//
//    /**
//     * 加了表头
//     * @throws IOException
//     */
//    @Test
//    public void writeWithoutHead2() throws  IOException {
//        try (OutputStream out = new FileOutputStream("writeWithoutHead2.xlsx");) {
//            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
//            Sheet sheet1 = new Sheet(1, 0);
//            sheet1.setSheetName("sheet1");
//            List<List<String>> data = new ArrayList<>();
//            for (int i = 0; i < 100; i++) {
//                List<String> item = new ArrayList<>();
//                item.add("item0" + i);
//                item.add("item1" + i);
//                item.add("item2" + i);
//                data.add(item);
//            }
//            List<List<String>> head = new ArrayList<List<String>>();
//            List<String> headCoulumn1 = new ArrayList<String>();
//            List<String> headCoulumn2 = new ArrayList<String>();
//            List<String> headCoulumn3 = new ArrayList<String>();
//            headCoulumn1.add("第一列");
//            headCoulumn2.add("第二列");
//            headCoulumn3.add("第三列");
//            head.add(headCoulumn1);
//            head.add(headCoulumn2);
//            head.add(headCoulumn3);
//            Table table = new Table(1);
//            table.setHead(head);
//            writer.write0(data, sheet1, table);
//            writer.finish();
//        }
//    }
//
//    /**
//     * 使用实体类，为其添加com.alibaba.excel.annotation.ExcelProperty注解来生成表头，实体类数据作为Excel数据
//     * @throws IOException
//     */
//    @Test
//    public void writeWithHead() throws IOException {
//        try (OutputStream out = new FileOutputStream("writeWithHead.xlsx");) {
//            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
//            Sheet sheet1 = new Sheet(1, 0, ExcelPropertyIndexModel.class);
//            sheet1.setSheetName("sheet1");
//            List<ExcelPropertyIndexModel> data = new ArrayList<>();
//            for (int i = 0; i < 100; i++) {
//                ExcelPropertyIndexModel item = new ExcelPropertyIndexModel();
//                item.name = "name" + i;
//                item.age = "age" + i;
//                item.email = "email" + i;
//                item.address = "address" + i;
//                item.sax = "sax" + i;
//                item.heigh = "heigh" + i;
//                item.last = "last" + i;
//                data.add(item);
//            }
//            writer.write(data, sheet1);
//            writer.finish();
//        }
//    }
//
//    public static class ExcelPropertyIndexModel extends BaseRowModel {
//
//        @ExcelProperty(value = "姓名", index = 0)
//        private String name;
//
//        @ExcelProperty(value = "年龄", index = 1)
//        private String age;
//
//        @ExcelProperty(value = "邮箱", index = 2)
//        private String email;
//
//        @ExcelProperty(value = "地址", index = 3)
//        private String address;
//
//        @ExcelProperty(value = "性别", index = 4)
//        private String sax;
//
//        @ExcelProperty(value = "高度", index = 5)
//        private String heigh;
//
//        @ExcelProperty(value = "备注", index = 6)
//        private String last;
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getAge() {
//            return age;
//        }
//
//        public void setAge(String age) {
//            this.age = age;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public void setEmail(String email) {
//            this.email = email;
//        }
//
//        public String getAddress() {
//            return address;
//        }
//
//        public void setAddress(String address) {
//            this.address = address;
//        }
//
//        public String getSax() {
//            return sax;
//        }
//
//        public void setSax(String sax) {
//            this.sax = sax;
//        }
//
//        public String getHeigh() {
//            return heigh;
//        }
//
//        public void setHeigh(String heigh) {
//            this.heigh = heigh;
//        }
//
//        public String getLast() {
//            return last;
//        }
//
//        public void setLast(String last) {
//            this.last = last;
//        }
//    }

}
