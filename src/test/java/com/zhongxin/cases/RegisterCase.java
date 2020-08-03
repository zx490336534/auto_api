package com.zhongxin.cases;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.zhongxin.pojo.CaseInfo;
import com.zhongxin.utils.ExcelUtils;
import com.zhongxin.utils.HttpUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

/**
 * 注册接口测试类型
 */
public class RegisterCase {

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) {
        try {
            HashMap<String, String> headers = new HashMap<>();
            headers.put("X-Lemonban-Media-Type", "lemonban.v1");
            String contentType = caseInfo.getContentType();
            if ("json".equals(contentType)) {
                headers.put("Content-Type", "application/json");
            } else if ("form".equals(contentType)) {
                headers.put("Content-Type", "application/x-www-form-urlencoded");
            }
            if ("post".equals(caseInfo.getMethod())) {
                HttpUtils.post(caseInfo.getUrl(), caseInfo.getParams(), headers);
            } else if ("get".equals(caseInfo.getMethod())) {
                HttpUtils.get(caseInfo.getUrl(), headers);
            } else if ("patch".equals(caseInfo.getMethod())) {
                HttpUtils.patch(caseInfo.getUrl(), caseInfo.getParams(), headers);
            }
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
