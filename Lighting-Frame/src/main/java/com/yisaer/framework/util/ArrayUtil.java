package com.yisaer.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * C
 */
public final class ArrayUtil {
    public static boolean isNotEmpty(Object[] array) {
        return !ArrayUtils.isEmpty(array);
    }

    private static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }
}
