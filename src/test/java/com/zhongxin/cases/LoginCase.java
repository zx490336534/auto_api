package com.zhongxin.cases;

import com.zhongxin.pojo.CaseInfo;
import com.zhongxin.utils.Constants;
import com.zhongxin.utils.ExcelUtils;
import com.zhongxin.utils.HttpUtils;
import com.zhongxin.utils.UserData;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class LoginCase extends BaseCase {

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) {
        paramsReplace(caseInfo);
        String responseBody = HttpUtils.call(caseInfo, UserData.DEFAULT_HEADERS);
        getParams(responseBody, "$.data.token_info.token", "${token}");
        getParams(responseBody, "$.data.id", "${member_id}");
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
