package com.zhongxin.cases;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.sun.xml.internal.rngom.parse.host.Base;
import com.zhongxin.pojo.CaseInfo;
import com.zhongxin.pojo.WriteBackData;
import com.zhongxin.utils.ExcelUtils;
import com.zhongxin.utils.HttpUtils;
import com.zhongxin.utils.UserData;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class LoginCase extends BaseCase {

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) {
        String responseBody = HttpUtils.call(caseInfo, UserData.DEFAULT_HEADERS);
        getParams(responseBody, "$.data.token_info.token", "${token}");
        getParams(responseBody, "$.data.id", "${member_id}");
        responseAssert(caseInfo.getExpectedResult(), responseBody);
        addWriteBackData(sheetIndex, caseInfo.getId(), 8, responseBody);
    }




    @DataProvider
    public Object[] datas() {
        List list = ExcelUtils.read(this.sheetIndex, 1, CaseInfo.class);
        return list.toArray();
    }
}
