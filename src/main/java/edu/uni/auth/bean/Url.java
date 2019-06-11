package edu.uni.auth.bean;

/**
 * @author 何亮
 */
public class Url {
    // url的名称
    private String url;
    // 方法名
    private String method;
    // 角色名
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Url{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
