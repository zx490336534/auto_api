package com.zhongxin.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;
import lombok.ToString;

@Data
public class Member {
    private int id;
    private String reg_name;
    private String pwd;
    private String mobile_phone;
    private int type;
    private BigDecimal leave_amount;
    private Timestamp reg_time;
}
