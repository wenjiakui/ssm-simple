package com.wjk.web.ssm.utils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @description: 用于JSONArray.fromObject()的注入处理日期Date类 (1)对于以Date时间属性为null值时，
 *               ，则返回空字符串，因为其转换过程中会出现问题，虽然没有发生异常，但是Ajax请求会报404 Not Found的错误
 *               (2)转换日期的格式
 * @fileName:JsonDateValueProcessor.java
 * @createTime:2019年9月8日 上午11:33:39
 * @author:wjk
 * @version 1.0.0
 *
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
    @Override
    public Object processArrayValue(Object obj, JsonConfig jsonconfig) {
        return process(obj);
    }
    @Override
    public Object processObjectValue(String s, Object obj, JsonConfig jsonconfig) {
        return process(obj);
    }
    private Object process(Object obj) {
        if (obj == null) {// 如果时间为null，则返回空字串
            return "";
        }
        if (obj instanceof Date) {
            obj = new java.util.Date(((Date) obj).getTime());
        }
        if (obj instanceof java.util.Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                    Locale.CHINA);// 格式化时间为yyyy-MM-dd类型
            return sdf.format(obj);
        } else {
            return new Object();
        }
    }
}