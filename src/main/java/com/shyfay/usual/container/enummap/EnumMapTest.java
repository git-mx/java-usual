package com.shyfay.usual.container.enummap;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

/**
 * @Notes 使用EnumMap来头=提高效率,EnmuMap的效率比HashMap的效率还要高
 * 因为它的底层采用的是数组来实现的
 * 本例用于演示统计每一种颜色的数量
 * @Author muxue
 * @Since 8/14/2020
 */
public class EnumMapTest {
    public static void main(String[] args) {
        Map<Color, Integer> enumMap = new EnumMap<Color, Integer>(Color.class);
        Color color = Color.RED;
        Color color1 = Color.GREEN;
        Color color2 = Color.BLACK;
        Color color3 = Color.YELLOW;
        Color color4 = Color.WHITE;
        enumMap.put(color, 1);
        enumMap.put(color1, 2);
        enumMap.put(color2, 3);
        enumMap.put(color3, 4);
        enumMap.put(color4, 5);
        System.out.println(enumMap);

        EnumSet<Color> enumSet = EnumSet.allOf(Color.class);
        System.out.println(enumSet);
    }
}
