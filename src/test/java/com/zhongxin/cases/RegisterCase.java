package com.zhongxin.cases;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.JSONObject;
import com.zhongxin.pojo.CaseInfo;
import com.zhongxin.pojo.WriteBackData;
import com.zhongxin.utils.ExcelUtils;
import com.zhongxin.utils.HttpUtils;
import com.zhongxin.utils.SQLUtils;
import com.zhongxin.utils.UserData;
import org.apache.commons.lang3.StringUtils;
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
        Long beforeSQLresult = (Long) SQLUtils.getSingleResult(caseInfo.getSql());
        String responseBody = HttpUtils.call(caseInfo, UserData.DEFAULT_HEADERS);
        responseAssert(caseInfo.getExpectedResult(), responseBody);
        addWriteBackData(sheetIndex, caseInfo.getId(), 8, responseBody);
        Long afterSQLresult = (Long) SQLUtils.getSingleResult(caseInfo.getSql());
        boolean sqlAssertFlag = sqlAssert(caseInfo.getSql(), beforeSQLresult, afterSQLresult);
    }

    /**
     * 数据库断言，因为每个接口的业务逻辑不一样，所以无法抽取到父类
     */
    public boolean sqlAssert(String sql, Long beforeSQLresult, Long afterSQLresult) {
        boolean flag = false;
        if (StringUtils.isNoneBlank(sql)) {
            if (beforeSQLresult == 0 && afterSQLresult == 1) {
                System.out.println("数据库断言成功");
                flag = true;
            } else {
                System.out.println("数据库断言失败");
                flag = false;
            }
        }
        return flag;
    }

    @DataProvider
    public Object[] datas() {
        List list = ExcelUtils.read(this.sheetIndex, 1, CaseInfo.class);
        return list.toArray();
    }
}
