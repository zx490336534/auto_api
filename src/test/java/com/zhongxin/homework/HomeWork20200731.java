package com.zhongxin.homework;

import com.zhongxin.pojo.CaseInfo;
import com.zhongxin.utils.ExcelUtils;
import com.zhongxin.utils.HttpUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class HomeWork20200731 {
    /** 流程：
    * 1. FileInputStream读取Excel文件流
    * 2. ExcelImportUtil传入文件流,配置,CaseInfo字节码
    * 3. DataProvider中拿到读取的内容
    * 4. 将返回的内容传给Test进行测试遍历
    * */

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
