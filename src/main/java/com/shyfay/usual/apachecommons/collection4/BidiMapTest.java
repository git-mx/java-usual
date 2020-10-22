package com.shyfay.usual.apachecommons.collection4;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

/**
 * @Notes 双重Map，可以通过value来获取key
 * @Author muxue
 * @Since 8/15/2020
 */
public class BidiMapTest {
    public static void main(String[] args) {
        BidiMap<String, String> map = new DualHashBidiMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        MapIterator<String, String> it = map.mapIterator();
        while(it.hasNext()){
            it.next();
            System.out.println(it.getKey() + ":" + it.getValue());
        }
        System.out.println(map.get("key1"));
        System.out.println(map.getKey("value1"));
        System.out.println(map.getOrDefault("key4", "defaultValue"));
        //得到一个视图
        BidiMap<String, String> inverseMap = map.inverseBidiMap();
        map.remove("key1");
        map.removeValue("value1");
        System.out.println(map);
        System.out.println(inverseMap);
     }
}
