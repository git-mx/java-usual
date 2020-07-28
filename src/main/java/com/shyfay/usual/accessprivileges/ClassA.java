package com.shyfay.usual.accessprivileges;

/**
 * @author mx
 * @since 2019/6/27
 */
public class ClassA {
    public String publicStr = "publicStr";
    private String privateStr = "privateStr";
    public static String staticStr = "staticStr";
    String defaultStr = "defaultStr";
    protected String protectedStr = "protectedStr";

    public String getPrivateStr() {
        return privateStr;
    }

    public void setPrivateStr(String privateStr) {
        this.privateStr = privateStr;
    }
}
