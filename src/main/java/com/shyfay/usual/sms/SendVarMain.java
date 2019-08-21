package com.shyfay.usual.sms;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

/**
 * @author mx
 * @since 2019/8/19
 */
public class SendVarMain {
    public static final String charset = "utf-8";
    // 请登录zz.253.com 获取创蓝API账号(非登录账号,示例:N1234567)
    public static String account = "N521633_N4674305";
    // 请登录zz.253.com 获取创蓝API密码(非登录密码)
    public static String password = "lkVseqS0mZ207d";











    public static void main(String[] args) throws UnsupportedEncodingException {

        //变量短信发送的URL 请登录zz.253.com 获取完整的URL接口信息
        String smsVariableRequestUrl = "http://smssh1.253.com/msg/variable/json";
        //设置您要发送的内容：其中“【】”中括号为运营商签名符号，多签名内容前置添加提交
        String msg = "尊敬的{$var},您好,您的验证码是{$var},{$var}分钟内有效【泸州通】";
        //参数组
        String params = "18181873960,牟先生,1234,3;18380406399,张女士,123,3;";
        //状态报告
        String report= "true";

        SmsVariableRequest smsVariableRequest=new SmsVariableRequest(account, password, msg, params, report);

        String requestJson = JSON.toJSONString(smsVariableRequest);

        System.out.println("before request string is: " + requestJson);

        String response = ChuangLanSmsUtil.sendSmsByPost(smsVariableRequestUrl, requestJson);

        System.out.println("response after request result is : " + response);

        SmsVariableResponse smsVariableResponse = JSON.parseObject(response, SmsVariableResponse.class);

        System.out.println("response  toString is : " + smsVariableResponse);

    }
}
