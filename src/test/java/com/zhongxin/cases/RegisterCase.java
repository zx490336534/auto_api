package com.zhongxin.cases;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.zhongxin.pojo.CaseInfo;
import com.zhongxin.utils.ExcelUtils;
import com.zhongxin.utils.HttpUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * 注册接口测试类型
 */
public class RegisterCase {

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) {
        try {
            HttpUtils.post(caseInfo.getUrl(), caseInfo.getParams());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DataProvider
    public Object[] datas() {
        List list = ExcelUtils.read(0, 1, CaseInfo.class);
        return list.toArray();
    }
}
