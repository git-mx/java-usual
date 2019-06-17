package com.shyfay.usual.container;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetTest {
    public static void main(String[] args){
        /** Set是接口，HashSet和TreeSet以及LinkedSet都是Set接口的实现类
         * Set与List的区别
         * 1.两个类型和值都相同的元素是不能存在于同一个Set中
         * 2.Set集合中的元素是无序的，而List内的元素是有序的，Set集合无法使用下标进行访问元素，List集合可以使用下标进行访问元素
         * 3.Set就是变种的Collection，相对于Collection它没有额外的功能，但是List集合确拥有一些Collection集合没有的功能
         **/
        Set set = new HashSet();
        //Set<Integer> set = new HashSet<Integer>();
        Long start = System.currentTimeMillis();
        Integer a = 3000;
        Long b = 3000L;
        set.add(a);
        set.add(b);
        set.add("AAA");
        //TODO Set内的元素是不能重复的，这里指的不能重复是指，两个类型一样的变量值不能一样，而不是只内存起始地址
        //TODO 但是如果是两个类型不一样的变量，他们的值确实相等的，例如上面的a和b，那么他们是可以共存于同一个Set的
        //TODO 如果上面的变量b是一个Integer或者int的话那么这个Set的size就是2了
        System.out.println(set.size());//3
        //TODO Set的添加元素稍微比ArrayList和LinkedList慢一点儿
        for(int i=0; i<100000; i++){
            set.add(i);
        }
        Long end1 = System.currentTimeMillis();
        System.out.println(end1 - start);
        //TODO Set内的元素是无序的，不能通过下标访问要访问Set内的元素只能通过Iterator来访问
        Iterator<Object> iterator = set.iterator();
        while(iterator.hasNext()){
            Object temp = iterator.next();
        }
        System.out.println(System.currentTimeMillis() - end1);
    }
}
