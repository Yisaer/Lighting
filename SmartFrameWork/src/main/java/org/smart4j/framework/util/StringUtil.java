package org.smart4j.framework.util;

//import org.apache.*;

import org.apache.commons.lang3.StringUtils;

/**
 *
 */
public final class StringUtil {
    /**
     * 字符串分隔符
     */
    public static final String SEPARATOR = String.valueOf((char) 29);

    public static boolean isNotEmpty(String strValue) {
        return !isEmpty(strValue);
    }

    private static boolean isEmpty(String strValue) {
        if (strValue!=null){
            strValue=strValue.trim();
        }
        return StringUtils.isEmpty(strValue);
    }

    public static String[] splitString(String body, String s) {
        return body.split(s);
    }
}
