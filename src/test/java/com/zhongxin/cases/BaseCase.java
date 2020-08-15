package com.zhongxin.cases;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.zhongxin.pojo.CaseInfo;
import com.zhongxin.pojo.WriteBackData;
import com.zhongxin.utils.ExcelUtils;
import com.zhongxin.utils.HttpUtils;
import com.zhongxin.utils.UserData;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BaseCase {
    public int sheetIndex;
    private static Logger logger = Logger.getLogger(HttpUtils.class);

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
        Object userDataValue = JSONPath.read(responseBody, jsonPathExpression);
        if (userDataValue != null) {
            UserData.VARS.put(userDataKey, userDataValue);
        }
    }

    /**
     * 获取鉴权头，并且加入默认请求头，返回头
     */
    public HashMap<String, String> getAuthorizationHeader() {
        Object token = UserData.VARS.get("${token}");
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + token);
        headers.putAll(UserData.DEFAULT_HEADERS);
        return headers;
    }

    /**
     * 接口响应断言
     *
     * @param expectedResult 断言的期望值
     * @param responseBody   接口响应内容
     * @return 接口响应断言结果
     */
    public boolean responseAssert(String expectedResult, String responseBody) {
        Map<String, Object> map = JSONObject.parseObject(expectedResult, Map.class);
        Set<String> keySet = map.keySet();
        boolean reponseAssertFlag = true;
        for (String actualExpression : keySet) {
            Object expectedValue = map.get(actualExpression);
            Object actualValue = JSONPath.read(responseBody, actualExpression);
            if (!expectedValue.equals(actualValue)) {
                reponseAssertFlag = false;
                break;
            }
        }
        System.out.println("断言结果:" + reponseAssertFlag);
        return reponseAssertFlag;
    }

    /**
     * 参数化替换
     */
    public void paramsReplace(CaseInfo caseInfo) {
        Set<String> keySet = UserData.VARS.keySet();
        String params = caseInfo.getParams();
        String sql = caseInfo.getSql();
        String expectedResult = caseInfo.getExpectedResult();
        String url = caseInfo.getUrl();
        for (String placeHolder : keySet) {
            String value = UserData.VARS.get(placeHolder).toString();
            if (StringUtils.isNotBlank(params)) {
                params = params.replace(placeHolder, value);
            }
            if (StringUtils.isNotBlank(sql)) {
                sql = sql.replace(placeHolder, value);
            }
            if (StringUtils.isNotBlank(expectedResult)) {
                expectedResult = expectedResult.replace(placeHolder, value);
            }
            if (StringUtils.isNotBlank(url)) {
                url = url.replace(placeHolder, value);
            }
        }
        caseInfo.setParams(params);
        caseInfo.setSql(sql);
        caseInfo.setExpectedResult(expectedResult);
        caseInfo.setUrl(url);
        logger.info(caseInfo);
    }

    @AfterSuite
    public void finish() throws Exception {
        ExcelUtils.batchWrite();
    }
}
