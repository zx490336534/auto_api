package com.zhongxin.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * excel 表格映射
 */
public class CaseInfo {

    @Excel(name = "CaseId(用例编号)")
    private int id;
    @Excel(name = "Name(接口名)")
    private String name;
    @Excel(name = "Type(接口提交类型)")
    private String method;
    @Excel(name = "Url(接口地址)")
    private String url;
    @Excel(name = "Desc(用例描述)")
    private String desc;
    @Excel(name = "Params(参数)")
    private String params;
    @Excel(name = "Content-Type")
    private String contentType;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    @Override
    public String toString() {
        return "CaseInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", desc='" + desc + '\'' +
                ", params='" + params + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
