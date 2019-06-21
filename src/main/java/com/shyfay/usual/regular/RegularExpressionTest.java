package com.shyfay.usual.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 座机号、手机号、身份证号正则表达式
 * @author mx
 * @since 2019/6/21
 */
public class RegularExpressionTest {

    private static String REGULAR_EXPRESSION_AIRLINE = "(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})";
    private static String REGULAR_EXPRESSION_CELLPHONE = "1((3\\d)|(4[5-9])|(5([0-3]|[5-9]))|(6[6|8])|(7[3-8])|(8[0-9])|(9[8|9]))\\d{8}";
    private static String REGULAR_EXPRESSION_ID_18 = "[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\\d{3}([0-9]|X)";
    private static String REGULAR_EXPRESSION_ID_15 = "[1-9]\\d{5}\\d{2}((0[0-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\\d{3}";
    public static void main(String[] args) {
        String phoneStr = "<p>电话号码：0830-7203658</p><p>公司地址：</p><p>古蔺镇墨宝寺62号</p>";
        System.out.println(checkTelephone(phoneStr));
        String idStr18 = "41000119910101123X";
        String idStr15 = "410001910101123";
        checkId(idStr18);
        checkId(idStr15);
    }

    public static String checkTelephone(String str){
        Pattern pattern = Pattern.compile(REGULAR_EXPRESSION_AIRLINE);
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            return matcher.group();
        }
        pattern = Pattern.compile(REGULAR_EXPRESSION_CELLPHONE);
        matcher = pattern.matcher(str);
        while(matcher.find()){
            return matcher.group();
        }
        return "";
    }

    public static void checkId(String str){
        Pattern pattern = null;
        Matcher matcher = null;
        if(str.length()==18){
            pattern = Pattern.compile(REGULAR_EXPRESSION_ID_18);
        }else{
            pattern = Pattern.compile(REGULAR_EXPRESSION_ID_15);
        }
        matcher = pattern.matcher(str);
        System.out.println(matcher.matches());
    }
}
