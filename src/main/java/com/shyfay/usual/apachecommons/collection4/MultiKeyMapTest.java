package com.shyfay.usual.apachecommons.collection4;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;

/**
 * @Notes 多个值作为key的Map，这是非常有用的，比如User的name和sex共同组成key
 * 如果用普通的map，我们的拼接字符串
 * @Author muxue
 * @Since 8/15/2020
 */
public class MultiKeyMapTest {
    public static void main(String[] args) {
        MultiKey<String> multiKey = new MultiKey<>("a", "b");
        System.out.println(multiKey);
        MultiKeyMap<String, String> multiKeyMap = new MultiKeyMap<>();
        multiKeyMap.put("zhangsan", "male", "张三-男");
        System.out.println(multiKeyMap.get("zhangsan", "male"));
        multiKeyMap.put("zhangsan", "male", "哈哈哈");
        System.out.println(multiKeyMap.get("zhangsan", "male"));
    }
}
