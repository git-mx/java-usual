package com.shyfay.usual.apachecommons.collection4;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MapUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Notes MapUtils
 * @Author muxue
 * @Since 8/15/2020
 */
public class MapUtilsTest {
    public static void main(String[] args) {
        Map<String, String> map = null;
        map = MapUtils.emptyIfNull(map);
        map.put("aaa", "AAA");
        map.put("bbb", "BBB");
        map.put("ccc", "CCC");
        //翻转MAP
        map = MapUtils.invertMap(map);
        //将一个MAP定长掉，并且设置为IterableMap方便便利
        IterableMap<String, String> itMap = MapUtils.fixedSizeMap(map);
        MapIterator<String, String> it = itMap.mapIterator();
        while(it.hasNext()){
            it.next();
            System.out.println(it.getKey() + ":" + it.getValue());
            if("aaa".equals(it.getKey())){
                it.remove();
            }
        }
        //定制化MAP
        MapUtils.populateMap(map, Arrays.asList("ddd", "eee", "fff"), k -> "key-" + k, v -> "value-" + v);

        Properties properties = MapUtils.toProperties(map);
    }
}
