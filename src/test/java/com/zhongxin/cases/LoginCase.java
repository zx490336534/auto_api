package com.zhongxin.cases;

import com.zhongxin.pojo.CaseInfo;
import com.zhongxin.utils.ExcelUtils;
import com.zhongxin.utils.HttpUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
        HttpUtils.call(caseInfo);
    }

    @DataProvider
    public Object[] datas() {
        List list = ExcelUtils.read(this.sheetIndex, 1, CaseInfo.class);
        return list.toArray();
    }
}
