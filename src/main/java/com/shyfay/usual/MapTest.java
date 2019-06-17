package com.shyfay.usual;
import java.util.*;

public class MapTest {
    public static void main(String[] args){
        //TODO Map中key不可重复，但是value可以重复
        //TODO Map中的元素是无序的，无法通过下标访问
        Map<String, String> hashMap = new HashMap<String, String>();
        for(int i=0; i<10; i++){
            hashMap.put(String.valueOf(i), String.valueOf(i));
        }
        //TODO 遍历Map的元素
        Set entrySet = hashMap.entrySet();
        Iterator<Map.Entry> iterator = entrySet.iterator();
        while(iterator.hasNext()){
            Map.Entry entry = iterator.next();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(entry.getKey());
            stringBuilder.append("---");
            stringBuilder.append(entry.getValue());
            System.out.println(stringBuilder.toString());
        }
        //TODO 获取Map的key集合
        Set keySet = hashMap.keySet();
        //TODO 获取Map的value集合
        Collection valueCollection = hashMap.values();
    }
}
