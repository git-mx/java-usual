package com.shyfay.usual.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mx
 * @since 2019/6/24
 */
public class RegularExptession {
    private static String REGULAR_EXPRESSION_AIRLINE = "(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})";
    private static String REGULAR_EXPRESSION_CELLPHONE = "(((13|17|18)\\d)|(14(5|7|9))|(15([0-3]|[5-9]))|(16(6|8))|(191))\\d{8}";
    //验证大小月份和2月的
    private static String REGULAR_EXPRESSION_ID18 = "[1-9]\\d{5}(18|19|20)\\d{2}(((01|03|05|07|08|10|12)(([0-2][1-9])|10|20|30|31))|((04|06|09|11)(([0-2][1-9])|10|20|30))|(02(([0-2][1-9])|10|20)))\\d{3}(\\d|X)";
    public static void main(String[] args) {
        String str1 = "52212519881121006X";
        Pattern pattern  = Pattern.compile(REGULAR_EXPRESSION_ID18);
        Matcher matcher = pattern.matcher(str1);
        System.out.println(matcher.matches());
    }
}
