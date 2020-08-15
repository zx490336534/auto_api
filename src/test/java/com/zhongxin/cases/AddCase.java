package com.zhongxin.cases;

import com.alibaba.fastjson.JSONPath;
import com.zhongxin.pojo.CaseInfo;
import com.zhongxin.utils.Constants;
import com.zhongxin.utils.ExcelUtils;
import com.zhongxin.utils.HttpUtils;
import com.zhongxin.utils.SQLUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * 新增项目接口测试
 */
public class AddCase extends BaseCase {

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) {
        paramsReplace(caseInfo);
        HashMap<String, String> headers = getAuthorizationHeader();
        String responseBody = HttpUtils.call(caseInfo, headers);
        getParams(responseBody, "$data.id", "${loan_id}");
        boolean responseAssertFlag = responseAssert(caseInfo.getExpectedResult(), responseBody);
        String assertResult = responseAssertFlag ? Constants.ASSERT_SUCCESS : Constants.ASSERT_FAILED;
        addWriteBackData(sheetIndex, caseInfo.getId(), Constants.RESPONSE_CELL_NUM, responseBody);
        addWriteBackData(sheetIndex, caseInfo.getId(), Constants.ASSERT_CELL_NUM, assertResult);
        Assert.assertEquals(assertResult, Constants.ASSERT_SUCCESS);
    }


    @DataProvider
    public Object[] datas() {
        List list = ExcelUtils.read(this.sheetIndex, 1, CaseInfo.class);
        return list.toArray();
    }
}
