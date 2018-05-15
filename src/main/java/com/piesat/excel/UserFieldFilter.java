package com.piesat.excel;

import com.piesat.excel.excel.FieldFilter;

public class UserFieldFilter implements FieldFilter {
    @Override
    public boolean filter(String name) {
        return false;
    }
}
