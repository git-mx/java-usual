package com.shyfay.usual.thread.concurrent.blockingqueue;

import java.util.concurrent.*;

/**
 * @Notes DelayQueue 没有大小限制的延时队列（Integer_MAX_VALUE），它的元素必须实现Delay接口
 * poll的时候只能获取到队列中过期了的元素
 * 实现Delayed接口的元素会被强制执行下面两个方法：
 * CompareTo(Delayed o)：Delayed接口继承了Comparabel接口，因此有了这个方法
 * getDelay(TimeUnit unit)：这个方法返回元素的到期时间的剩余时间，时间单位由参数指定
 * 比较常见的做法是使用一个DelayQueue来管理一个超时未相应的连接队列
 * 本例模拟一个redis 即向keyValue集合存入元素设置一个超时时间，元素超时之后将从集合中移除
 * 当向集合中存入一个已经存在的元素时则将元素的超时时间设置为新存入元素指定的时间
 * @Author muxue
 * @Since 8/29/2020
 */
public class DelayQueueTest<K, V> {
    //用于存放KeyValue的集合
    public ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>();
    //用于存放所有的key，开启一个线程检测这些key，当key过期了，就从map中移除对应的元素
    //当有新的key存入时，如果key已经存在，那么则将map中的元素替换，并且将key的过期时间设置成新的值
    public DelayQueue<DelayedItem<K>> queue = new DelayQueue<>();

    public static void main(String[] args) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int cacheNumber = 10;
        int liveTime = 0;
        DelayQueueTest<String, Integer> cache = new DelayQueueTest<>();
        for(int i=1; i<=cacheNumber; i++){
            liveTime = i * 10000;
            System.out.println(i + ":"+liveTime);
            cache.put(i + "", i, random.nextInt(liveTime));
            if(i >= 7){
                liveTime = random.nextInt(3000);
                System.out.println(i + ":" + liveTime);
                cache.put(i + "", i, liveTime);
            }
        }
        while(true){
            if(cache.map.size() <= 0){
                break;
            }
        };
        System.out.println("---------------------------------");
    }

    public void put(K k, V v, long liveTime){
        V v2 = map.put(k, v);
        DelayedItem<K> item = new DelayedItem<>(k, liveTime);
        if(null != v2){
            queue.remove(item);
        }
        queue.put(item);
    }

    public DelayQueueTest() {
        Thread t = new Thread(() -> {
            while(true){
                DelayedItem<K> item = queue.poll();
                if(null != item){
                    map.remove(item.getT());
                    System.out.println("remove:" + item.getT());
                }
//                try {
//                    TimeUnit.MILLISECONDS.sleep(300);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });
        t.setDaemon(true);
        t.start();
    }


    //为了能够使用DelayedItem将ConcurrentHahsMap中的元素删除，必须重写实现hashCode和equals方法
    static class DelayedItem<T> implements Delayed {

        private T t;
        private long liveTime;
        private long removeTime;

        public DelayedItem(T t, long liveTime){
            this.setT(t);
            this.liveTime = liveTime;
            this.removeTime = TimeUnit.MILLISECONDS.convert(liveTime, TimeUnit.MILLISECONDS) + System.currentTimeMillis();
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(removeTime - System.currentTimeMillis(), unit);
        }

        @Override
        public int compareTo(Delayed o) {
            if(o == null) return 1;
            if(o == this) return 0;
            if(o instanceof DelayedItem){
                DelayedItem<T> tempDelayedItem = (DelayedItem<T>) o;
                if(liveTime > tempDelayedItem.liveTime){
                    return 1;
                }else if(liveTime == tempDelayedItem.liveTime){
                    return 0;
                }else{
                    return -1;
                }
            }
            long diff = getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
            return diff > 0 ? 1 : diff == 0 ? 0 : -1;
        }

        @Override
        public int hashCode(){
            return t.hashCode();
        }

        @Override
        public boolean equals(Object object){
            if(object instanceof DelayedItem){
                return object.hashCode() == hashCode();
            }
            return false;
        }
    }
}
