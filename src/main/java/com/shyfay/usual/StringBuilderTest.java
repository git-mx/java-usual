package com.shyfay.usual;


/**
 * @author 牟雪
 * @since 2019/1/7
 */
public class StringBuilderTest {
//    public static void main(String[] args){
////        StringBuilder sb = new StringBuilder();
////        sb.append("aaaa");
////        sb.append("bbbb,");
////        sb.deleteCharAt(sb.lastIndexOf("A"));
////        System.out.println(sb.toString());
//
//        String temploate = "AAAA%sssss%ssss%ssss";
//        String test = String.format(temploate, "BBBB", "CCCC", "DDDD");
//        System.out.println(test);
//
//
//
//    }
    public static void main(String[] args) {
        //电话费
        String url = "alipays://platformapi/startapp?appId=20000987&url=%2Fwww%2Findex.html%3Fmobile%3DAAAA%26sourceId%3Dxxxxxxxx";
        url = url.replace("AAAA", "18181873960");
        System.out.println("电话费");
        System.out.println(url);
        //水费
        url = "alipays://platformapi/startapp?appId=20000193&url=%2Fwww%2FeBill.htm%3Freferer%3DGOPAY%26billKey%3DAAAA%26instId%3DBBBB%26subBizType%3DWATER%26st%3D1";
        url = url.replace("AAAA", "123456");
        url = url.replace("BBBB", "UASDFSG");
        System.out.println("水费");
        System.out.println(url);
        //电费
        url = "alipays://platformapi/startapp?appId=20000193&url=%2Fwww%2FeBill.htm%3Freferer%3DGOPAY%26billKey%3DAAAA%26instId%3DBBBB%26subBizType%3DELECTRIC%26st%3D1";
        url = url.replace("AAAA", "123456");
        url = url.replace("BBBB", "UASDFSG");
        System.out.println("电费");
        System.out.println(url);
        //燃气费
        url = "alipays://platformapi/startapp?appId=20000193&url=%2Fwww%2FeBill.htm%3Freferer%3DGOPAY%26billKey%3DAAAA%26instId%3DBBBB%26subBizType%3DGAS%26st%3D1";
        url = url.replace("AAAA", "123456");
        url = url.replace("BBBB", "UASDFSG");
        System.out.println("燃气费");
        System.out.println(url);
    }
}
