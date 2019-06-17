package com.shyfay.usual;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


/**
 * @author 牟雪
 * @since 2019/2/15
 */
public class GaoDeTest {
    public static void main(String[] args){
        try{
            String url = "https://restapi.amap.com/v3/ip?ip=192.168.5.41&output=json&key=86b410e42958cda3a2654ab9756e0705";
            HttpGet httpGet = new HttpGet(url);
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse resp = client.execute(httpGet);
            if(resp.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = resp.getEntity();
                String respContent = EntityUtils.toString(he,"UTF-8");
                System.out.println(respContent);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
