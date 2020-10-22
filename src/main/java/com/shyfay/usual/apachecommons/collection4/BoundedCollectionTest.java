package com.shyfay.usual.apachecommons.collection4;

import org.apache.axis.collections.LRUMap;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.list.FixedSizeList;
import org.apache.commons.collections4.list.GrowthList;
import org.apache.commons.collections4.list.SetUniqueList;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Notes 固定大小的集合
 * @Author muxue
 * @Since 8/15/2020
 */
public class BoundedCollectionTest {
    public static void main(String[] args) {
        FixedSizeList<String> list = FixedSizeList.fixedSizeList(Arrays.asList("aaa", "bbb", "ccc"));
        //不能新增和移除，但是可以修改
        list.set(2, "ddd");
        System.out.println(list.isFull());
        System.out.println(list.maxSize());

        //环形的先进先出队列，当队列满时，新加入的元素会替换掉最先放入队列的元素
        CircularFifoQueue<String> queue = new CircularFifoQueue<>(3);
        queue.addAll(Arrays.asList("aaa", "bbb", "ccc"));
        queue.add("ddd");
        System.out.println(queue);

        //BoundedMap FixedSizeSortedMap

        //LRUMap LRU算法，如果一个数据在一段时间内都没有被访问到，那么在将来它被访问的可能性也很小
        //一个定长的集合，当集合满时有新的元素要存入集合，那么最近闲置最久的那个元素将被替换掉
        LRUMap map = new LRUMap(3);
        map.put("aaa", "aaa");
        map.put("bbb", "bbb");
        map.put("ccc", "ccc");
        System.out.println(map.size());
        System.out.println(map.isEmpty());
        System.out.println(map.getMaximumSize());
        map.get("aaa");
        map.get("bbb");
        map.put("ddd", "ddd");
        System.out.println(map);

        //利用GrowthList避免数组越界
        List<String> src = new ArrayList<>();
        src.add("aaa");
        src.add("bbb");
        src = GrowthList.growthList(src);
        src.set(4, "ddd");
        System.out.println(src);

        //使用SetUniqueList对数组去重并保证原有的元素顺序
        src = new ArrayList<>(Arrays.asList("aaa", "bbb", "bbb","ccc"));
        src = SetUniqueList.setUniqueList(src);
        System.out.println(src);
    }
}
