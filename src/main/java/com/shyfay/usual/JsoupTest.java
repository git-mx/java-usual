package com.shyfay.usual;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author 牟雪
 * @since 2019/1/16
 */
public class JsoupTest {
    public static void main(String[] args){
        try{
            //Document doc = Jsoup.connect("https://www.lzsbdc.net/index.php?r=default/column/content&col=100017&id=629").get();
            Document doc = Jsoup.connect("https://www.lzsbdc.net/index.php?r=default/column/index&col=5").get();
            System.out.println(doc);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
