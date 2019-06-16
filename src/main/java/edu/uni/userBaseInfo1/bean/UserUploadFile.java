package edu.uni.userBaseInfo1.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserUploadFile {
    private Long id;

    private String fileName;

    private String fileInfo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetime;

    private Long byWho;

    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(String fileInfo) {
        this.fileInfo = fileInfo == null ? null : fileInfo.trim();
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Long getByWho() {
        return byWho;
    }

    public void setByWho(Long byWho) {
        this.byWho = byWho;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "UserUploadFile{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileInfo='" + fileInfo + '\'' +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                '}';
    }

    public static boolean isValidForApply(UserUploadFile userUploadFile){
        return userUploadFile.getId() != null && userUploadFile.getFileName() != null &&
                userUploadFile.getFileInfo() != null ;
    }

    public static void copyPropertiesForApply(UserUploadFile new_file, UserUploadFile old_file){
        new_file.setDatetime(new Date());
        new_file.setDeleted(true);
    }



}