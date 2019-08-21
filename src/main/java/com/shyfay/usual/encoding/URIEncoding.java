package com.shyfay.usual.encoding;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author mx
 * @since 2019/8/1
 */
public class URIEncoding {
    public static void main(String[] args) {
        String uri = "https://www.baidu.com/s?wd=呵呵";
        try{
            System.out.println(URLEncoder.encode(uri, StandardCharsets.UTF_8.toString()));
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
