package com.shyfay.usual.apachecommons.collection4;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.util.Arrays;
import java.util.Collection;

/**
 * @Notes 多值Map，这也是非常有用的
 * @Author muxue
 * @Since 8/15/2020
 */
public class MultiValueMap {
    public static void main(String[] args) {
        MultiValuedMap<String, String> multiValuedMap = new ArrayListValuedHashMap<>();
        multiValuedMap.put("key1", "value11");
        multiValuedMap.put("key1", "value12");
        Collection<String> values = multiValuedMap.values();
        System.out.println(values);
        multiValuedMap.removeMapping("key1", "value11");
        multiValuedMap.putAll("key2", Arrays.asList("value21", "value22", "value23"));
        values = multiValuedMap.get("key2");
        System.out.println(values);
        multiValuedMap.remove("key2");
    }
}
