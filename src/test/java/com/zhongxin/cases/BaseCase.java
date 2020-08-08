package com.zhongxin.cases;

import com.zhongxin.pojo.WriteBackData;
import com.zhongxin.utils.ExcelUtils;
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

    @AfterSuite
    public void finish() throws Exception {
        ExcelUtils.batchWrite();
    }
}
