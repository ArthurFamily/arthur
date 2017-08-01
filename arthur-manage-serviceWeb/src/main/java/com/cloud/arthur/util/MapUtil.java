package com.cloud.arthur.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenzhen on 2017/7/25
 */
public class MapUtil {
    private Map<String, Object> map = new HashMap<String, Object>();

    public static MapUtil map() {
        return new MapUtil();
    }

    public MapUtil put(String key, Object value) {

        if (StringUtils.isNotEmpty(key)) map.put(key, value);
        return this;
    }

    public Map build() {
        return this.map;
    }
}
