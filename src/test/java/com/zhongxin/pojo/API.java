package com.zhongxin.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class API {
    @Excel(name = "CaseId")
    private int id;

    @Excel(name = "Name")
    private String name;

    @Excel(name = "Url")
    private String url;

    @Excel(name = "Type")
    private String method;

}
