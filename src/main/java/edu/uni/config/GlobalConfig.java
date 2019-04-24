package edu.uni.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalConfig {
    // 上传资源的总文件夹，系统绝对路径，后面不带/
    @Value("nui.uploadRootDir")
    private String uploadRootDir;

    public String getUploadRootDir() {
        return uploadRootDir;
    }

    public void setUploadRootDir(String uploadRootDir) {
        this.uploadRootDir = uploadRootDir;
    }
}
