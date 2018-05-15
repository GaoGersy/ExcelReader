package com.piesat.excel.excel;

import java.util.Map;

public interface ValueConvert {
    boolean handler(Map<String, Object> map, String property,String value);
}
