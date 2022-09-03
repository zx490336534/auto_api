package com.zhongxin.cases;

import com.alibaba.fastjson.JSONPath;
import com.zhongxin.pojo.CaseInfo;
import com.zhongxin.pojo.WriteBackData;
import com.zhongxin.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
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
        boolean responseAssertFlag = responseAssert(caseInfo.getExpectedResult(), responseBody);
        BigDecimal afterSQLresult = (BigDecimal) SQLUtils.getSingleResult(caseInfo.getSql());
        boolean sqlAssertFlag = sqlAssert(caseInfo, beforeSQLresult, afterSQLresult);
        String assertResult = responseAssertFlag ? Constants.ASSERT_SUCCESS : Constants.ASSERT_FAILED;
        addWriteBackData(sheetIndex, caseInfo.getId(), Constants.RESPONSE_CELL_NUM, responseBody);
        addWriteBackData(sheetIndex, caseInfo.getId(), Constants.ASSERT_CELL_NUM, assertResult);
        Assert.assertEquals(assertResult, Constants.ASSERT_SUCCESS);
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
        List list = ExcelUtils.read(null, this.sheetIndex, 1, CaseInfo.class);
        return list.toArray();
    }
}
