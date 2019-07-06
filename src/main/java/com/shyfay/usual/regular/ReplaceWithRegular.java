package com.shyfay.usual.regular;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 本例用于测试将${XXX}里面的变量替换成值的操作
 * @author mx
 * @since 2019/6/26
 */
public class ReplaceWithRegular {
    private static String REGULAR_EXPRESSION = "\\$\\{.*\\}";

    public static void main(String[] args) {
        String source = "AAAA${}AAAA";
        String paramValue = "CCCC";
        System.out.println(replaceParamWithValue(source, REGULAR_EXPRESSION, paramValue));
        String source1 = "AAAA${BBBB}CCCC${DDDD}EEEE${FFFF}GGGG";
        Map<String, String> paramValues = new HashMap<>();
        paramValues.put("BBBB", "1111");
        paramValues.put("DDDD", "2222");
        paramValues.put("FFFF", "3333");
        System.out.println(replaceParamWithVlue(source1, paramValues));
    }

    private static String replaceParamWithValue(final String source, final String regex, final String paramValue){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        return matcher.replaceAll(paramValue);
    }

    private static String replaceParamWithVlue(final String source, final Map<String, String> paramValues){
        String msg = source;
        Set entrySet = paramValues.entrySet();
        Iterator<Map.Entry> iterator = entrySet.iterator();
        Map.Entry entry;
        while(iterator.hasNext()){
            entry = iterator.next();
            msg = replaceParamWithValue(msg, "\\$\\{" + entry.getKey() + "\\}", (String)entry.getValue());
        }
        return msg;
//        for(Iterator var4 = paramValues.keySet().iterator(); var4.hasNext(); msg = replaceParamWithValue(msg, "\\$\\{" + entry.getKey() + "\\}", (String)entry.getValue())){
//            entry = (Map.Entry)var4.next();
//        }
    }

}
