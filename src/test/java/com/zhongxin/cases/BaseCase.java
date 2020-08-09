package com.zhongxin.cases;

import com.alibaba.fastjson.JSONPath;
import com.zhongxin.pojo.WriteBackData;
import com.zhongxin.utils.ExcelUtils;
import com.zhongxin.utils.UserData;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseCase {
    public int sheetIndex;

    @BeforeClass
    @Parameters({"sheetIndex"})
    public void beforeClass(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }


    /**
     * 添加回写对象到回写集合中
     */
    public void addWriteBackData(int sheetIndex, int rowNum, int cellNum, String content) {
        WriteBackData wdb = new WriteBackData(sheetIndex, rowNum, cellNum, content);
        ExcelUtils.wdbList.add(wdb);
    }

    /**
     * 从responseBody 通过Jsonpath取出对应参数，存到UserData中
     */
    public void getParams(String responseBody, String jsonPathExpression, String userDataKey) {
        Object token = JSONPath.read(responseBody, jsonPathExpression);
        if (token != null) {
            UserData.VARS.put(userDataKey, token);
        }
    }

    @AfterSuite
    public void finish() throws Exception {
        ExcelUtils.batchWrite();
    }
}
