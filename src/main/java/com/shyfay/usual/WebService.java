package com.shyfay.usual;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

/**
 * @author 牟雪
 * @since 2018/12/11
 */
public class WebService {
    public static void main(String[] args) {

//        try {
//            //String endpoint = "http://gkfw.luzhou.gov.cn/services/RestNewsService";
//            String endpoint = "http://gkfw.luzhou.gov.cn/services/RestNewsService";
//            // 直接引用远程的wsdl文件
//            // 以下都是套路
//            Service service = new Service();
//            Call call = (Call) service.createCall();
//            call.setTargetEndpointAddress(new java.net.URL(endpoint));
//            //call.setUseSOAPAction(true);
//            //call.setSOAPActionURI("http://service.rest2.cms.jeecms.com/getSercher");
//            call.setOperationName(new QName("http://service.rest2.cms.jeecms.com/", "getSercher"));
//            //call.setOperationName("getRegionlistByParentid");
//            call.addParameter("Q", XMLType.XSD_STRING, ParameterMode.IN);
//            call.addParameter("PAGENO", XMLType.XSD_STRING, ParameterMode.IN);
//            call.addParameter("PAGESIZE", XMLType.XSD_STRING, ParameterMode.IN);
//            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
//            call.setEncodingStyle("UTF-8");
//            //String result = (String) call.invoke(new Object[]{"510500"});
//            String result = (String) call.invoke(new Object[]{"呵呵", "1", "10"});
//            System.out.println("result is " + result);
//        }
//        catch (Exception e) {
//            System.err.println(e.toString());
//        }
        try {
            //String endpoint = "http://gkfw.luzhou.gov.cn/services/RestNewsService";
            String endpoint = "http://117.174.155.223:8123/services/RestRegionService.wsdl";
            // 直接引用远程的wsdl文件
            // 以下都是套路
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(endpoint));
            call.setUseSOAPAction(true);
            call.setSOAPActionURI("http://service.rest2.cms.jeecms.com/getRegionlistByParentid");
            call.setOperationName(new QName("http://service.rest2.cms.jeecms.com/", "getRegionlistByParentid"));
            //call.setOperationName("getRegionlistByParentid");
            call.addParameter("PARENTID", XMLType.XSD_STRING, ParameterMode.IN);
//            call.addParameter("PAGENO", XMLType.XSD_STRING, ParameterMode.IN);
//            call.addParameter("PAGESIZE", XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_STRING);
            call.setEncodingStyle("UTF-8");
            String result = (String) call.invoke(new Object[]{"510500"});
            //String result = (String) call.invoke(new Object[]{"呵呵", "1", "10"});
            System.out.println("result is " + result);
        }
        catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
