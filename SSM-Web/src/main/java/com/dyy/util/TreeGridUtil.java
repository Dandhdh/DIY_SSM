package com.dyy.util;

import java.util.List;

public class TreeGridUtil {

    private Integer id ;
    private String name ;
    private String function_url;
    private String remark ;

    private List<TreeGridUtil> children ;

    /**
     * 父节点的开启关闭状态
     */
    private String state ;
    /**
     * 是否展开
     */
    private boolean selected=false;
    /**
     * 父节点ID
     */
    private Integer parent_id ;
    /**
     * 用于授权的弹出的tree显示用的text
     */
    private String text ;
    /**
     * 用于授权的弹出的tree显示用的 是否被选中的状态
     */
    private boolean checked = false ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.text = name;
        this.name = name;
    }

    public String getFunction_url() {
        return function_url;
    }

    public void setFunction_url(String function_url) {
        this.function_url = function_url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<TreeGridUtil> getChildren() {
        return children;
    }

    public void setChildren(List<TreeGridUtil> children) {
        this.children = children;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


}