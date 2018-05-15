package com.piesat.excel.excel;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析Excel，支持2003、2007
 */
public abstract class BaseExcelReadHelper<T> {

    protected ValueConvert mValueConvert;

    protected FieldFilter mFieldFilter;

    protected ExcelFilter mExcelFilter;

    private Class<T> mClazz;

    private List<String> mProperties;

    public BaseExcelReadHelper() {
        initValueConvert();
        initExcelFilter();
        initFieldFilter();
    }

    public void getExcelContent(String filePath, ReadExcelResultCallback<T> readExcelResultCallback) throws Exception {
        getExcelContent(new File(filePath), readExcelResultCallback);
    }

    public void getExcelContent(File file, ReadExcelResultCallback<T> readExcelResultCallback) throws Exception {
        Class<T> trueClass = getTrueClass();
        List<String> properties = getProperties(trueClass);
        Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
        try {
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 获取每个Sheet表
                Sheet sheet = workbook.getSheetAt(i);
                for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {// getLastRowNum，获取最后一行的行标
                    Row row = sheet.getRow(j);
                    if (row != null) {
                        if (!mExcelFilter.rowNumFilter(j)) {
                            short lastCellNum = row.getLastCellNum();
                            if (mExcelFilter != null && mExcelFilter.columnsFilter(lastCellNum)) {
                                continue;
                            }
                            T object = getObject(row, properties, trueClass);
                            readExcelResultCallback.onResult(object);
                        }
                    }
                }
            }
        } finally {
            workbook.close();
        }

    }

    public List<T> getExcelContent(String filePath) throws Exception {
        return getExcelContent(new File(filePath));
    }

    public List<T> getExcelContent(File file) throws Exception {
        Class<T> trueClass = getTrueClass();
        List<String> properties = getProperties(trueClass);
        Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
        List<T> list = new ArrayList<>();
        try {
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 获取每个Sheet表
                Sheet sheet = workbook.getSheetAt(i);
                for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {// getLastRowNum，获取最后一行的行标
                    Row row = sheet.getRow(j);
                    if (row != null) {
                        if (!mExcelFilter.rowNumFilter(j)) {
                            short lastCellNum = row.getLastCellNum();
                            if (mExcelFilter != null && mExcelFilter.columnsFilter(lastCellNum)) {
                                continue;
                            }
                            T object = getObject(row, properties, trueClass);
                            list.add(object);
                        }
                    }
                }
            }
        } finally {
            workbook.close();
        }
        return list;
    }

    private Class<T> getTrueClass() {
        if (mClazz == null) {
            Type type = getClass().getGenericSuperclass();
            mClazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
        }
        return mClazz;
    }

    private List<String> getProperties(Class clazz) {
        if (mProperties == null) {
            Field[] fields = clazz.getDeclaredFields();
            mProperties = new ArrayList<>();
            for (int i = 0; i < fields.length; i++) {
                String name = fields[i].getName();
                if (mFieldFilter != null && !mFieldFilter.filter(name)) {
                    mProperties.add(name);
                }
            }
        }
        return mProperties;
    }

    private T getObject(Row row, List<String> properties, Class clazz) {
        short lastCellNum = row.getLastCellNum();
        Map<String, Object> map = new HashMap<>();
        int count = 0;
        for (int k = 0; k < lastCellNum; k++) {
            if (mExcelFilter != null && mExcelFilter.columnNumFilter(k)) {
                continue;
            }
            Cell cell = row.getCell(k);
            String property = properties.get(count);
            if (mValueConvert == null || !mValueConvert.handler(map, property, cell.toString())) {
                map.put(property, cell.toString());
            }
            count++;
        }
        T object = (T) map2Bean(map, clazz);
        return object;
    }

    public ValueConvert getValueConvert() {
        return mValueConvert;
    }

    public void setValueConvert(ValueConvert valueConvert) {
        mValueConvert = valueConvert;
    }

    public FieldFilter getFieldFilter() {
        return mFieldFilter;
    }

    public void setFieldFilter(FieldFilter fieldFilter) {
        mFieldFilter = fieldFilter;
    }

    public ExcelFilter getExcelFilter() {
        return mExcelFilter;
    }

    public void setExcelFilter(ExcelFilter excelFilter) {
        mExcelFilter = excelFilter;
    }

    public static <T> T map2Bean(Map<String, Object> map, Class<T> class1) {
        T bean = null;
        try {
            bean = class1.newInstance();
            BeanUtils.populate(bean, map);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    protected abstract void initValueConvert();

    protected abstract void initExcelFilter();

    protected abstract void initFieldFilter();

}
