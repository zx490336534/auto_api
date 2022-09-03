package com.zhongxin.cases;

import com.zhongxin.pojo.CaseInfo;
import com.zhongxin.utils.Constants;
import com.zhongxin.utils.ExcelUtils;
import com.zhongxin.utils.HttpUtils;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

/**
 * 用户信息接口测试
 */
public class UserInfoCase extends BaseCase {

    @Test(dataProvider = "datas", description = "管理员用户信息查询")
    @Step("Type {caseInfo.id} - {caseInfo.url}")
    public void test(CaseInfo caseInfo) {
        paramsReplace(caseInfo);
        HashMap<String, String> headers = getAuthorizationHeader();
        String responseBody = HttpUtils.call(caseInfo, headers);
        boolean responseAssertFlag = responseAssert(caseInfo.getExpectedResult(), responseBody);
        String assertResult = responseAssertFlag ? Constants.ASSERT_SUCCESS : Constants.ASSERT_FAILED;
        addWriteBackData(sheetIndex, caseInfo.getId(), Constants.RESPONSE_CELL_NUM, responseBody);
        addWriteBackData(sheetIndex, caseInfo.getId(), Constants.ASSERT_CELL_NUM, assertResult);
        Assert.assertEquals(assertResult, Constants.ASSERT_SUCCESS);
    }


    @DataProvider
    public Object[] datas() {
        List list = ExcelUtils.read(null, this.sheetIndex, 1, CaseInfo.class);
        return list.toArray();
    }
}
