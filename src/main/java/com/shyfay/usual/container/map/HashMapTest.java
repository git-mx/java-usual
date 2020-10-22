package com.shyfay.usual.container.map;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Notes HashMap源码分析
 * HashMap与Object类的equals()方法和hashCode()方法之间的关系如下：
 * 首先HashMap判断key是否存在的步骤是：
 * 1.调用key的hashCode()得到一个值，再用这个值进行一定的计算得到一个hash值
 * 2.put方法时，使用这个hash值与当前Node数组的长度取模得到这个key在Node数组中的下标
 * 3.put方法时，判断这个下标上的链表是否为空，如果为空则表示该key不存在，直接插入key对应的节点
 * 4.如果不为空，则调用key.equals(oldKey1);key.equals(oldKey2);...
 *   方法判断当前插入的key与已经存在的key挨个比对是否相等，如果相等则用新的值替换对应Node的值
 *   如果不等，则生成一个新的Node插入到当前链表的末尾。
 * 从这整个过程就可以看出HashMap与Object的equals()方法与hashMap()方法的关系
 * 另外值得注意的是lombok的@Data注解会覆盖Object的hashCode()方法，它的实现逻辑是
 * 只要两个同一类型对象，如果他们各个属性的值都相等，那么调用这个两个对象的hashCode()方法
 * 返回的值是一样。同时也覆盖了equals方法，也是只要每个属性的值相等则equals为true，
 * 所以如果一个类的对象会在系统中被用于当作HashMap的key，那么这个类需要谨慎使用@Data注解
 * 一般都是自己手动覆盖equals方法和hashCode方法
 * 另外对于基础数据类型： == 是比对两个对象的内存起始地址 .equals方法是比对的对象的值
 * 例如String和Integer他们都覆盖了Object类的equals方法
 * @Author muxue
 * @Since 8/2/2020
 */
//@Data
@Getter
@Setter
public class HashMapTest {
    private Integer id;
    private String name;
    public static void main(String[] args) {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("a", "aaaa");
        stringMap.put("a", "bbbb");
        stringMap.put("a", "cccc");
        System.out.println(stringMap.size());
        stringMap = new HashMap<>();
        stringMap.put(new String("a"), "aaaa");
        stringMap.put(new String("a"), "bbbb");
        stringMap.put(new String("a"), "cccc");
        //这里也只会输出1，是因为String类重写了equals方法和hashCode方法
        System.out.println(stringMap.size());
        Map<Integer, Integer> intMap = new HashMap<>();
        intMap.put(new Integer("1"), 1111);
        intMap.put(new Integer("1"), 2222);
        intMap.put(new Integer("1"), 3333);
        //这里也只会输出1，是因为Integer类重写了equals方法和hashCode方法
        System.out.println(intMap.size());

        HashMapTest t1 = new HashMapTest();
        HashMapTest t2 = new HashMapTest();
        HashMapTest t3 = new HashMapTest();
        t1.setId(1);
        t1.setName("name");
        t2.setId(1);
        t2.setName("name");
        t3.setId(1);
        t3.setName("name");
        //这里如果把@Data注解干掉则输出false，放开则输出true
        System.out.println(t1.equals(t2));
        Map<HashMapTest, Integer> tMap = new HashMap<>();
        tMap.put(t1, t1.getId());
        tMap.put(t2, t2.getId());
        tMap.put(t3, t3.getId());
        //这里如果把@Data注解干掉则输出3，放开则输出1
        System.out.println(tMap.size());
    }
}
