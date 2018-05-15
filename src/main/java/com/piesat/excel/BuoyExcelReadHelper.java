package com.piesat.excel;


import com.piesat.excel.excel.BaseExcelReadHelper;
import com.piesat.excel.model.User;

public class BuoyExcelReadHelper extends BaseExcelReadHelper<User> {
    @Override
    protected void initValueConvert() {
        setValueConvert(new UserValueConvert());
    }

    @Override
    protected void initExcelFilter() {
        setExcelFilter(new UserFilter());
    }

    @Override
    protected void initFieldFilter() {
        setFieldFilter(new UserFieldFilter());
    }
}
