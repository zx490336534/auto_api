package com.zhongxin.cases;

import com.alibaba.fastjson.JSONPath;
import com.zhongxin.pojo.CaseInfo;
import com.zhongxin.utils.ExcelUtils;
import com.zhongxin.utils.HttpUtils;
import com.zhongxin.utils.UserData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class LoginCase {
    public int sheetIndex;

    @BeforeClass
    @Parameters({"sheetIndex"})
    public void beforeClass(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) {
        String responseBody = HttpUtils.call(caseInfo,UserData.DEFAULT_HEADERS);
        Object token = JSONPath.read(responseBody, "$.data.token_info.token");
        Object memberId = JSONPath.read(responseBody, "$.data.id");
        if (token != null) {
            UserData.VARS.put("${token}", token);
        }
        if (memberId != null) {
            UserData.VARS.put("${member_id}", memberId);
        }
    }

    @DataProvider
    public Object[] datas() {
        List list = ExcelUtils.read(this.sheetIndex, 1, CaseInfo.class);
        return list.toArray();
    }
}
