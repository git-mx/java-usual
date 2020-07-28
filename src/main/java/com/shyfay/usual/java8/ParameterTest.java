package com.shyfay.usual.java8;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Notes java8为反射提供了Parameter类，来获取方法的参数信息
 * @Author muxue
 * @Since 7/27/2020
 */
public class ParameterTest {
    private List<String> getParameters(Class clazz, String methodName){
        List<String> parameters = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods){
            if(methodName.equals(method.getName())){
                Parameter[] params = method.getParameters();
                for(Parameter param : params){
                    parameters.add(param.getName());
                }
            }
        }
        return parameters;
    }
}
