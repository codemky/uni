package edu.uni.auth.bean;

import java.util.List;

/**
 * 目录节点数据结构
 * @Author 何亮
 */
public class Node {
    // 前端路由
    private String key;
    // 节点标题
    private String title;
    // 是否是父节点(模块节点)
    private boolean isParent;
    // 子节点
    private List<Node> children;
    /*
     * 节点编号，用于前端回传给后台时，后台能识别到是哪个节点
     * 编号格式：
     *      模块节点：M + [记录id]
     *      功能点节点：F + [记录id]
     */
    private String no;

    // 是否被选中
    private Boolean selected;

    public Node() {
    }

    public Node(String key, String title, boolean isParent, List<Node> children, String no) {
        this.key = key;
        this.title = title;
        this.isParent = isParent;
        this.children = children;
        this.no = no;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
