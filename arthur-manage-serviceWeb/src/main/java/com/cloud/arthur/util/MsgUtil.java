package com.cloud.arthur.util;



import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenzhen on 2017/7/25
 */
public final class MsgUtil {


    public static Map<String, Object> success(Object o) {
        return map(true, o);
    }

    public static Map<String, Object> failure(Object o) {
        return map(false, o);
    }

    private static Map<String, Object> map(boolean success, Object o) {
        Map<String, Object> result = new HashMap();
        result.put("success", Boolean.valueOf(success));
        if(o != null) {
            result.put("data", o);
        }

        return result;
    }
}
