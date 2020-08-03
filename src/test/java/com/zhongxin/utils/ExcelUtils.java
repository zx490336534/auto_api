package com.zhongxin.utils;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.zhongxin.pojo.API;
import com.zhongxin.pojo.CaseInfo;

import java.io.FileInputStream;
import java.util.List;

public class ExcelUtils {
    public static void main(String[] args) {
        List<CaseInfo> list = read(0, 1, CaseInfo.class);
        List<API> list2 = read(1, 1, API.class);
        for (CaseInfo caseInfo : list) {
            System.out.println(caseInfo);
        }
        System.out.println("=======");
        for (API api : list2) {
            System.out.println(api);
        }
    }

    /**
     * 读取excel数据后封装到指定对象中
     *
     * @param sheetIndex 开始sheet索引
     * @param sheetNum   sheet个数
     * @param clazz      excel映射类字节对象
     * @return
     */
    public static List read(int sheetIndex, int sheetNum, Class clazz) {
        try {
            // 1. excel文件流
            FileInputStream fis = new FileInputStream("src/test/resources/cases_v1.xls");
            // 2. easypoi 导入参数
            ImportParams params = new ImportParams();
            params.setStartSheetIndex(sheetIndex);//从第x个sheet开始读取
            params.setSheetNum(sheetNum);//读取x个sheet
            // 3. 导入
            List caseInfoList = ExcelImportUtil.importExcel(fis, clazz, params);
            // 4. 关流
            fis.close();
            return caseInfoList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
