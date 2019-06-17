package com.shyfay.usual.container;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListTest {
    public static void main(String[] args){
        //List<String> list = new ArrayList<String>();
        List<String> list = new LinkedList<String>();
        for(int i=0; i<100000; i++){
            list.add(String.valueOf(i));
        }
        Long start = System.currentTimeMillis();
        //TODO LinkedList实际上是一个双向链表使用LinkedList的addFirst、addLast、
        //TODO removeFirst、removeLast等可以很轻松的实现队列(queue）和栈(stack)等数据结构
        //TODO LinkedList使用iterator来顺序访问的时候跟Arraylist使用下标顺序访问的速度是差不多的
        //TODO 但是如果LinkedList使用下标来顺序访问的话就比使用iterator要慢许多
        //TODO 同样的ArrayList在使用add(element)的时候和LinkedList使用add(element)或者addFirst(element)
        //TODO 是一样很快的，但是如果使用add(0, element)的话速度就要比直接使用add(element)慢很多，另外使用下标
        //TODO remove(index)的时候也会比较慢
        //TODO 这就是说ArrayList在使用下标随机访问的时候比较快，但是在中间插入或删除元素会比较慢
        //TODO LinkedList随机访问比较慢，但是针对插入和删除速度很快，所以在日常使用中如果是元素不需要频繁改变的集合
        //TODO 可以使用ArrayList来存储，但是如果元素需要频繁改变的集合则可以使用LinkedList，且使用Iterator来便利集合
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String temp = iterator.next();
        }
        Long cast = System.currentTimeMillis() - start;
        System.out.println(cast);
    }
}


