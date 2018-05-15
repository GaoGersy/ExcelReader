package com.piesat.excel;

import com.piesat.excel.excel.ExcelFilter;

public class UserFilter implements ExcelFilter {
    @Override
    public boolean rowNumFilter(int rowNum) {
        return false;
    }

    @Override
    public boolean columnNumFilter(int columnNum) {
        return false;
    }

    @Override
    public boolean columnsFilter(int columns) {
        return false;
    }

    @Override
    public boolean contentFilter(String content) {
        return false;
    }
}
