package com.zhongxin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 待写入Excel的数据格式
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WriteBackData {
    /**
     * sheet索引
     */
    private int sheetIndex;
    /**
     * 行号
     */
    private int rowNum;
    /**
     * 列号
     */
    private int cellNum;
    /**
     * 内容
     */
    private String content;
}
