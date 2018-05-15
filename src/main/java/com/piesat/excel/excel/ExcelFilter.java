package com.piesat.excel.excel;

public interface ExcelFilter {
    /**
     * 过滤哪一行的数据
     * @param rowNum 行号
     * @return
     */
    boolean rowNumFilter(int rowNum);

    /**
     * 过滤哪一列的数据
     * @param columnNum 列号
     * @return
     */
    boolean columnNumFilter(int columnNum);

    /**
     * 当列数满足条件时过滤掉
     * @param columns 总列数
     * @return
     */
    boolean columnsFilter(int columns);

    /**
     * 单元格数据满足条件时过滤掉
     * @param content 单元格内容
     * @return
     */
    boolean contentFilter(String content);
}
