package com.zhongxin.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * excel 表格映射
 */
@Data
public class CaseInfo {
    // 期望结果		sql
    @Excel(name = "用例编号")
    private int id;

    @Excel(name = "接口名称")
    private String name;

    @Excel(name = "请求方式")
    private String method;

    @Excel(name = "url")
    private String url;

    @Excel(name = "用例描述)")
    private String desc;

    @Excel(name = "参数")
    private String params;

    @Excel(name = "参数类型")
    private String contentType;

    @Excel(name = "期望结果")
    private String expectedResult;

    @Excel(name = "sql")
    private String sql;
}
