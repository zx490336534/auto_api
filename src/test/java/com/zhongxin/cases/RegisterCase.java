package com.zhongxin.cases;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.JSONObject;
import com.zhongxin.pojo.CaseInfo;
import com.zhongxin.utils.ExcelUtils;
import com.zhongxin.utils.HttpUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注册接口测试类型
 */
public class RegisterCase {

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) {
        try {
            HashMap<String, String> headers = new HashMap<>();
            headers.put("X-Lemonban-Media-Type", "lemonban.v1");

            String params = caseInfo.getParams();
            String url = caseInfo.getUrl();
            String method = caseInfo.getMethod();
            String contentType = caseInfo.getContentType();

            if ("json".equals(contentType)) {
                headers.put("Content-Type", "application/json");
            } else if ("form".equals(contentType)) {
                Map<String, String> map = JSONObject.parseObject(params, Map.class);
                String formParams = "";
                for (String key : map.keySet()) {
                    formParams += key + "=" + map.get(key) + "&";
                }
                params = formParams.substring(0, formParams.length() - 1);
                headers.put("Content-Type", "application/x-www-form-urlencoded");
            }

            if ("post".equals(method)) {
                HttpUtils.post(url, params, headers);
            } else if ("get".equals(method)) {
                HttpUtils.get(url, headers);
            } else if ("patch".equals(method)) {
                HttpUtils.patch(url, params, headers);
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
