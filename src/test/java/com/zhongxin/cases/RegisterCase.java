package com.zhongxin.cases;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.JSONObject;
import com.zhongxin.pojo.CaseInfo;
import com.zhongxin.pojo.WriteBackData;
import com.zhongxin.utils.ExcelUtils;
import com.zhongxin.utils.HttpUtils;
import com.zhongxin.utils.UserData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注册接口测试类型
 */
public class RegisterCase extends BaseCase {

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) {
        String responseBody = HttpUtils.call(caseInfo, UserData.DEFAULT_HEADERS);
        responseAssert(caseInfo.getExpectedResult(), responseBody);
        addWriteBackData(sheetIndex, caseInfo.getId(), 8, responseBody);
    }

    @DataProvider
    public Object[] datas() {
        List list = ExcelUtils.read(this.sheetIndex, 1, CaseInfo.class);
        return list.toArray();
    }
}
