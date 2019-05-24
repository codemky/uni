package edu.uni.userBaseInfo1.alibaba.easyexcel.test.util;

import java.io.InputStream;

public class FileUtil {

    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("excel/example/" + fileName);
    }
}
