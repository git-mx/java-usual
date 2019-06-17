package com.shyfay.usual.string;

/**
 * @author 牟雪
 * @since 2019/2/21
 */
public class SubstringTest {
    public static void main(String[] args){
        String str = "<p>服务电话：</p><p>&lt;a&nbsphref=\"tel:0830-2393664\"&gt;0830-2393664&lt;/a&gt;AAAA</p><p><br></p>";
        String head = "";
        String str1 = str.substring(0, str.indexOf("&lt;a"));
        if(str1.endsWith("<p>")){
            head = str1.substring(0, str1.lastIndexOf("<p>"));
        }else{
            head = str1.substring(0, str1.lastIndexOf("<p>")) + str1.substring(str1.lastIndexOf("<p>") + 3);
        }
        System.out.println("前面：" + head);
        String middle = "";
        middle = str.substring(str.indexOf("&lt;a"), str.indexOf("&lt;/a&gt;") + 10);
        middle = middle.replaceAll("&nbsp", " ");
        System.out.println("中间:" + middle);
        String tail = "";
        str1 = str.substring(str.indexOf("&lt;/a&gt;") + 10);
        if(str1.startsWith("</p>")){
            tail = str1.substring(4);
        }else{
            tail = str1.substring(0, str1.indexOf("</p>")) + str1.substring(str1.indexOf("</p>") + 4);
        }
        System.out.println("后面：" + tail);
    }
}
