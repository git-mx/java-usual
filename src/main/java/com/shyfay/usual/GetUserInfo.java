package com.shyfay.usual;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 牟雪
 * @since 2019/3/18
 */
public class GetUserInfo {
    public static void main(String[] args){
        Map<String, String> param = new LinkedHashMap<String, String>();
        param.put("userId", "1810120");
        param.put("authAppId", "2031");
        param.put("authSignType", "md5");
        param.put("authAppType", "app");
        String authSign = SystoonSignUtil.getSign(param, "196a996d65ad4e14ab398d1d0f75b95d");
        System.out.println(authSign);

    }
}
