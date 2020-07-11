package com.shyfay.usual.test;



import java.util.HashMap;
import java.util.Map;


/**
 * @Notes
 * @Author muxue
 * @Since 4/27/2020
 */
public class MainTest {

    public static void main(String[] args) {
        final String countStoreSql = "SELECT T1.StoreCounts, T2.HuaweiStoreCounts FROM"
                + " (select USER_ID, count(0) AS StoreCounts from  pttl_store_crm_info where USER_ID = ? AND STORE_STATUS = '启用') T1 INNER JOIN"
                + " (select USER_ID, count(0) AS HuaweiStoreCounts from  pttl_store_crm_info where USER_ID = ? and  is_huawei = '是' AND STORE_STATUS = '启用') T2"
                + " ON T1.USER_ID = T2.USER_ID";
        System.out.println(countStoreSql);
    }

}
