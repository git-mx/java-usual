package com.shyfay.usual;
//import org.apache.axiom.om.OMAbstractFactory;
//import org.apache.axiom.om.OMElement;
//import org.apache.axiom.om.OMFactory;
//import org.apache.axiom.om.OMNamespace;
//import org.apache.axis2.AxisFault;
//import org.apache.axis2.addressing.EndpointReference;
//import org.apache.axis2.client.Options;
//import org.apache.axis2.client.ServiceClient;
//import org.apache.axis2.rpc.client.RPCServiceClient;

/**
 * @author 牟雪
 * @since 2018/12/13
 */
public class Axis2Test {
   public static void main(String[] args){
//       try {
//           String soapBindingAddress = "http://117.174.155.223:8123/services/RestRegionService.wsdl";
//           ServiceClient sender = new ServiceClient();
//           EndpointReference endpointReference = new EndpointReference(
//                   soapBindingAddress);
//           Options options = new Options();
//           options.setAction("http://service.rest2.cms.jeecms.com/getRegionlistByParentid");
//           options.setTo(endpointReference);
//           sender.setOptions(options);
//           OMFactory fac = OMAbstractFactory.getOMFactory();
//           // 这个和qname差不多，设置命名空间
//           OMNamespace omNs = fac.createOMNamespace("http://service.rest2.cms.jeecms.com/",
//                   "getRegionlistByParentid");
//           OMElement data = fac.createOMElement("getRegionlistByParentid", omNs);
//           // 对应参数的节点
//           String[] params = new String[] {"arg0"};
//           // 参数值
//           String[] values = new String[] { "510500" };
//           for (int i = 0; i < params.length; i++) {
//               OMElement inner = fac.createOMElement(params[i], omNs);
//               inner.setText(values[i]);
//               data.addChild(inner);
//           }
//           // 发送数据，返回结果
//           OMElement result = sender.sendReceive(data);
//           System.out.println(result.toString());
//       } catch (AxisFault ex) {
//           ex.printStackTrace();
//       }
//       try{
//           RPCServiceClient client = new RPCServiceClient();
//           Options option = client.getOptions();
//           EndpointReference erf = new EndpointReference("http://117.174.155.223:8123/services/RestRegionService");
//           option.setTo(erf);
//           QName name = new QName("http://service.rest2.cms.jeecms.com/", "getRegionlistByParentid");
//           Class[] returnTypes = new Class[] { String.class };
//           Object[] params = new Object[]{"510500"};
//           Object[] response = client.invokeBlocking(name, params, returnTypes);
//           System.out.println(response[0].toString());
//       }catch (Exception e){
//           System.out.println(e.getMessage());
//       }

   }
}
