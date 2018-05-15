package com.piesat.excel;

import com.piesat.excel.excel.ValueConvert;

import java.util.Map;

public class UserValueConvert implements ValueConvert {
    @Override
    public boolean handler(Map<String, Object> map, String property, String value) {
        return false;
    }
}
