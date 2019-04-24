package edu.uni.example.config;

import edu.uni.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/example.properties")
@ConfigurationProperties(prefix="nui.example")
public class ExampleConfig {
    // 引入总配置类
    @Autowired
    private GlobalConfig globalConfig;

    private Integer pageSize;
    // 存储上传的excel文件夹的名称
    private String excelDir;


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getAbsoluteExcelDir() {
        return globalConfig.getUploadRootDir() + excelDir;
    }

    public String getExcelDir() {
        return excelDir;
    }

    public void setExcelDir(String excelDir) {
        this.excelDir = excelDir;
    }
}
