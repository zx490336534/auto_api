package com.zhongxin.cases;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.JSONObject;
import com.zhongxin.pojo.CaseInfo;
import com.zhongxin.pojo.WriteBackData;
import com.zhongxin.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注册接口测试类型
 */
public class RegisterCase extends BaseCase {

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) {
        paramsReplace(caseInfo);
        Long beforeSQLresult = (Long) SQLUtils.getSingleResult(caseInfo.getSql());
        String responseBody = HttpUtils.call(caseInfo, UserData.DEFAULT_HEADERS);
        boolean responseAssertFlag = responseAssert(caseInfo.getExpectedResult(), responseBody);
        Long afterSQLresult = (Long) SQLUtils.getSingleResult(caseInfo.getSql());
        boolean sqlAssertFlag = sqlAssert(caseInfo.getSql(), beforeSQLresult, afterSQLresult);
        String assertResult = responseAssertFlag ? Constants.ASSERT_SUCCESS : Constants.ASSERT_FAILED;
        addWriteBackData(sheetIndex, caseInfo.getId(), Constants.RESPONSE_CELL_NUM, responseBody);
        addWriteBackData(sheetIndex, caseInfo.getId(), Constants.ASSERT_CELL_NUM, assertResult);
        Assert.assertEquals(assertResult, Constants.ASSERT_SUCCESS);
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
