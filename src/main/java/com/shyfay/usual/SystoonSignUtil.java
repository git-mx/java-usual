package com.shyfay.usual;

/**
 * @author 牟雪
 * @since 2019/3/18
 */
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 签名工具
 *
 * @author 曾传志
 * @since 2018-03-06
 **/
public class SystoonSignUtil {

    private SystoonSignUtil(){}

    public static String getSign(Map<String,String> map, String appsecret){
        StringBuffer sb=new StringBuffer(appsecret);
        // 1.参数排序
        List<String> keyList = new ArrayList<String>(map.keySet());
        // 校验放在必传参数拦截器 map为空
        Collections.sort(keyList);
        for(String key:keyList){
            sb.append(key).append(map.get(key));
        }
        sb.append(appsecret);
        return DigestUtils.md5Hex(sb.toString()).toUpperCase();
    }
}

