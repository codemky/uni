/**
 * Author: mokuanyuan 11:19 2019/4/25
 * @apiNote: 用于配置（规定）上传文件的路径和分页查询时每一页的记录数。
 */
package edu.uni.userBaseInfo1.config;

import edu.uni.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//该注解用于被Springboot自动配置类扫描到，并在项目中添加相应的组件
@Component
//该注解用于指明配置文件的路径
@PropertySource("classpath:config/userinfo1.properties")
//该注解用于说明在配置文件中有哪些变量名是用到的
@ConfigurationProperties(prefix="nui.userbaseinfo1")
public class userBaseInfo1Config {
    // 引入总配置类
    @Autowired
    private GlobalConfig globalConfig;

    //分页查询时每一页的记录数
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
