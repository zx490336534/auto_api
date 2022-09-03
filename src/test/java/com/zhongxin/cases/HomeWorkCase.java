package com.zhongxin.cases;

import com.zhongxin.pojo.CaseInfo;
import com.zhongxin.utils.Constants;
import com.zhongxin.utils.ExcelUtils;
import com.zhongxin.utils.HttpUtils;
import com.zhongxin.utils.UserData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class HomeWorkCase extends BaseCase {
    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) {
        Map<String, String> headers = null;
        if (!caseInfo.getName().equals("register") && !caseInfo.getName().equals("login")) {
            headers = getAuthorizationHeader();
            paramsReplace(caseInfo);
        } else {
            headers = UserData.DEFAULT_HEADERS;
            paramsReplace(caseInfo);
        }
        String responseBody = HttpUtils.call(caseInfo, headers);
        getParams(responseBody, "$id", "${id}");
        getParams(responseBody, "$token", "${token}");
        if (caseInfo.getName().equals("projects_add")) {
            getParams(responseBody, "$id", "${project_id}");
        }
        getParams(responseBody, "$create_time", "${create_time}");
        if (caseInfo.getName().equals("projects")) {
            getParams(responseBody, "$count", "${count}");
            getParams(responseBody, "$results", "${results}");
            getParams(responseBody, "$total_pages", "${total_pages}");
        }

        paramsReplace(caseInfo);
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
