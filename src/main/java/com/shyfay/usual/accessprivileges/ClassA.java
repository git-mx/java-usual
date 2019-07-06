package com.shyfay.usual.accessprivileges;

/**
 * @author mx
 * @since 2019/6/27
 */
public class ClassA {
    public String publicStr = "AAAA";
    private String privateStr = "BBBB";
    public static String staticStr = "CCCC";
    String defaultStr = "DDDD";
    protected String protectedStr = "EEEE";

    public String getPrivateStr() {
        return privateStr;
    }

    public void setPrivateStr(String privateStr) {
        this.privateStr = privateStr;
    }
}
