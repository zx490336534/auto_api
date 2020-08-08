package com.zhongxin.cases;

import com.alibaba.fastjson.JSONPath;
import com.sun.xml.internal.rngom.parse.host.Base;
import com.zhongxin.pojo.CaseInfo;
import com.zhongxin.pojo.WriteBackData;
import com.zhongxin.utils.ExcelUtils;
import com.zhongxin.utils.HttpUtils;
import com.zhongxin.utils.UserData;
import org.testng.annotations.*;

import java.util.List;

public class LoginCase extends BaseCase {

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) {
        String responseBody = HttpUtils.call(caseInfo, UserData.DEFAULT_HEADERS);
        Object token = JSONPath.read(responseBody, "$.data.token_info.token");
        Object memberId = JSONPath.read(responseBody, "$.data.id");
        if (token != null) {
            UserData.VARS.put("${token}", token);
        }
        if (memberId != null) {
            UserData.VARS.put("${member_id}", memberId);
        }

        addWriteBackData(sheetIndex, caseInfo.getId(), 8, responseBody);
    }


    @DataProvider
    public Object[] datas() {
        List list = ExcelUtils.read(this.sheetIndex, 1, CaseInfo.class);
        return list.toArray();
    }
}
