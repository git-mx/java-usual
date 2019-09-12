package com.shyfay.usual.container;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mx
 * @since 2019/8/27
 */
public class ArrayListTest {

    private static void systemout(List<Integer> sourceList){
        if(null == sourceList || sourceList.isEmpty()){
            return;
        }
        if(sourceList.size() <= 100){
            for(int i=0; i<sourceList.size(); i++){
                System.out.println(sourceList.get(i).toString());
            }
        }else{
            int roopTime = sourceList.size() / 100;
            List<Integer> subList;
            for(int i=0; i<=roopTime; i++){
                if(i == roopTime){
                    subList = sourceList.subList(i * 100, sourceList.size());
                }else{
                    subList = sourceList.subList(i*100, (i + 1) * 100);
                }
                systemout(subList);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        for(int i=0; i<235; i++){
            integerList.add(i);
        }

        List<Integer> subList1 = integerList.subList(0, 5);
        List<Integer> subList2 = integerList.subList(5, 10);
        System.out.println(subList1);
        System.out.println(subList2);
        systemout(integerList);
    }
}
