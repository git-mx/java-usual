package com.shyfay.usual.container;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** ArrayList与Array的区别：
 * 1.ArrayList可以存储异构对象，而Array只能存储相同的类型的数据
 * 2.ArrayList长度是可变的，而Array长度是不变的
 * 3.存取和删除元素Array的效率更高，因为很多时候ArrayList在存取和删除元素时都会出发封箱和拆箱的操作
 * ArrayList要删除特定值的元素一定要适应迭代器（比如ArrayList<Integer>要删除值大于5的元素）
 * 注意使用Arrays.asList这种方式构造的列表是无法进行remove操作的
 * @author mx
 * @since 2019/8/27
 */
public class ArrayListTest {
    public static void main(String[] args) {
        //默认的大小是10，当大小不够可，之后会自动扩容到20，40， 80...
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(10);
        intList.add(3);
        intList.add(4);
        intList.add(5);
        intList.add(6);
        intList.add(7);
        intList.add(8);
        //删除下标为2的元素
        intList.remove(2);
        Integer three = 3;
        //删除值=3的元素
        intList.remove(three);
        //删除大于5的元素一定要这样写，不能用for循环或者增强
        Iterator<Integer> iterator = intList.iterator();
        Integer roop;
        while(iterator.hasNext()){
            roop = iterator.next();
            if(roop > 5){
                iterator.remove();
            }
        }
        System.out.println(intList);
    }

}
