package com.zhongxin.cases;

import com.alibaba.fastjson.JSONPath;
import com.zhongxin.pojo.CaseInfo;
import com.zhongxin.pojo.WriteBackData;
import com.zhongxin.utils.ExcelUtils;
import com.zhongxin.utils.HttpUtils;
import com.zhongxin.utils.SQLUtils;
import com.zhongxin.utils.UserData;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * 充值接口测试
 */
public class RechargeCase extends BaseCase {

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) {
        paramsReplace(caseInfo);
        BigDecimal beforeSQLresult = (BigDecimal) SQLUtils.getSingleResult(caseInfo.getSql());
        HashMap<String, String> headers = getAuthorizationHeader();
        String responseBody = HttpUtils.call(caseInfo, headers);
        responseAssert(caseInfo.getExpectedResult(), responseBody);
        addWriteBackData(sheetIndex, caseInfo.getId(), 8, responseBody);
        BigDecimal afterSQLresult = (BigDecimal) SQLUtils.getSingleResult(caseInfo.getSql());
        boolean sqlAssertFlag = sqlAssert(caseInfo, beforeSQLresult, afterSQLresult);
    }

    /**
     * 充值数据库断言
     */
    public boolean sqlAssert(CaseInfo caseInfo, BigDecimal beforeSQLresult, BigDecimal afterSQLresult) {
        boolean flag = false;
        if (StringUtils.isNotBlank(caseInfo.getSql())) {
            String amountStr = JSONPath.read(caseInfo.getParams(), "$.amount").toString();
            BigDecimal amout = new BigDecimal(amountStr);
            BigDecimal subtractResult = afterSQLresult.subtract(beforeSQLresult);
            // compareTo == 0 => 相等
            if (subtractResult.compareTo(amout) == 0) {
                System.out.println("数据库断言成功");
                flag = true;
            } else {
                System.out.println("数据库断言失败");
                flag = false;
            }
        }
        return flag;
    }


    @DataProvider
    public Object[] datas() {
        List list = ExcelUtils.read(this.sheetIndex, 1, CaseInfo.class);
        return list.toArray();
    }
}
