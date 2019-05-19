package edu.uni.userBaseInfo1.alibaba.easyexcel.test;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import edu.uni.userBaseInfo1.alibaba.easyexcel.test.listen.ExcelListener;
import edu.uni.userBaseInfo1.alibaba.easyexcel.test.model.StudentModel;
import edu.uni.userBaseInfo1.alibaba.easyexcel.test.util.FileUtil;
import edu.uni.userBaseInfo1.utils.WDWUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ReadTest {


    public void  printExecl() throws IOException {
        if(WDWUtil.isExcel2007("try.xlsx")) {
            System.out.println(WDWUtil.isExcel2007("try.xlsx")+" 1111");
            InputStream inputStream = FileUtil.getResourcesFileInputStream("try.xlsx");
            ExcelListener excelListener = new ExcelListener();
            //EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1), excelListener);
//            List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
            //List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 1, StudentModel.class));
            EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1, StudentModel.class), excelListener);
            inputStream.close();
            //print(data);
        }else{
            System.out.println(WDWUtil.isExcel2003("try.xlsx"+"22222"));
            InputStream inputStream = FileUtil.getResourcesFileInputStream("try.xls");
            List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 1));
            inputStream.close();
            print(data);
        }
    }


    public void print(List<Object> datas){
        int i=0;
        for (Object ob:datas) {
            System.out.println(i++);
            System.out.println(ob);
        }
    }

}
