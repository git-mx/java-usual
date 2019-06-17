package com.shyfay.usual;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 牟雪
 * @since 2018/12/25
 */
public class UrlConnectionTest {
    private static String sendPostUrl(String url, String param){
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流（设置请求编码为UTF-8）
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 获取请求返回数据（设置返回数据编码为UTF-8）
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    public static void main(String[] args){
        StringBuilder sb = new StringBuilder();
        sb.append("{\"libcode\":\"0B8D82A8-EA42-A371-506D-FE39B23F1B4B\",");
        sb.append("\"ifcode\":\"10002\",");
        sb.append("\"barcode\":\"000124\"}");
        String params = sb.toString();
        String returnStr = sendPostUrl("http://117.174.153.226:8089/HNCLib/HNCLibWeb/HNCLibWebServlet_PublicIF", params);
        System.out.println(returnStr);
    }
}
