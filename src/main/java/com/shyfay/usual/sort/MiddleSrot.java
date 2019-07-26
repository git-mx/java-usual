package com.shyfay.usual.sort;

import java.util.Arrays;

/**
 * 折半排序算法实现
 * @author mx
 * @since 2019/7/22
 */
public class MiddleSrot {
    public static void main(String[] args) {
        int[] arr = {5,2,7,3,9,10,8,6,1,4};
        middleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void middleSort(int[] arr){
        for(int i=1; i<arr.length; i++){
            int temp = arr[i];
            int low = 0;
            int high = i - 1;
            while(low <= high){
                int mid = (low+high)/2;
                if(temp < arr[mid]){
                    high = mid - 1;
                }else{
                    low = mid + 1;
                }
            }
            for(int j=i-1; j>high; j--){
                arr[j+1] = arr[j];
            }
            arr[high+1] = temp;
        }
    }
}
