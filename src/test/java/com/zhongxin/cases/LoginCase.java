package com.zhongxin.cases;

import com.zhongxin.pojo.CaseInfo;
import com.zhongxin.utils.ExcelUtils;
import com.zhongxin.utils.HttpUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class LoginCase {
    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) {
        HttpUtils.call(caseInfo);
    }

    @DataProvider
    public Object[] datas() {
        List list = ExcelUtils.read(1, 1, CaseInfo.class);
        return list.toArray();
    }
}
