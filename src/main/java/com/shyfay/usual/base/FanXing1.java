package com.shyfay.usual.base;

import java.lang.reflect.*;
import java.util.List;

/**
 * @Notes 本例用于演示Java提供的泛型工具类，即通过反射可以拿到泛型对象的信息
 * JAVA没有真正意义上的泛型，但是java提供了一个可以代表泛型的类型Type来为程序提供更高的灵活性
 * java定义了一个接口Type接口，它有四个子接口可一个直接的实现类
 * 四个子接口是：
 * TypeVariabel（类型变量类型）：比如List<T>中的T
 * WildcardType（泛型表达式类型）：比如List<? extends Numer> 这种表达式
 * ParameterizedType（参数化类型）：就是我们平时用到的泛型List,Map等等,List<String> strList 这个List就是一个参数化类型
 * GenericArrayType（数组类型）：T[] 就是一个数组类型
 * List<T ? entends>[] :
 * 这里List就是ParameterizedType
 * T就是TypeVariable
 * T ? entends 就是WildcardType
 * 整个List<T ? entends>[]就是一个GnenicArrayType
 * @Author muxue
 * @Since 8/19/2020
 */
public class FanXing1<T> {
    public void testFanXing(T t, List<String>[] pTypeArray, T[] vTypeArray, List<String> list,
                            List<? extends Number> wildcardList, String[] strings, FanXing1[] test){
    }

    private static void printTypeVariable(String fieldName, TypeVariable typeVariable){
        for(Type type : typeVariable.getBounds()){
            System.out.println("\t\t" + fieldName + "：TypeVariable getBounds" + type);
        }
        System.out.println("\t\t定义Class getGenericDeclaration：" + typeVariable.getGenericDeclaration());
        System.out.println("\t\tgetName：" + typeVariable.getName());
    }

    public static void main(String[] args) {
        Method[] declaredMethods = FanXing1.class.getDeclaredMethods();
        for(Method method : declaredMethods){
            if(method.getName().startsWith("main")){
                continue;
            }
            System.out.println("declared method：" + method);
            Type[] types = method.getGenericParameterTypes();
            for(Type type : types){
                if(type instanceof ParameterizedType){
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    System.out.println("ParameterizedType：" + parameterizedType);
                    for(Type type1 : parameterizedType.getActualTypeArguments()){
                        printTypeVariable(method.getName(), (TypeVariable) type1);
                    }
                }else if(type instanceof GenericArrayType){
                    GenericArrayType genericArrayType = (GenericArrayType) type;
                    System.out.println("GerenicArrayType：" + genericArrayType);
                    Type genericComponentType = genericArrayType.getGenericComponentType();
                    System.out.println("\tGenericComponentType：" + genericComponentType);
                    if(genericComponentType instanceof TypeVariable){
                        TypeVariable type1 = (TypeVariable) genericComponentType;
                        printTypeVariable(method.getName(), type1);
                    }
                }else if(type instanceof WildcardType){
                    WildcardType wildcardType = (WildcardType) type;
                    System.out.println("WildcardType：" + wildcardType);
                }else if(type instanceof TypeVariable){
                    TypeVariable typeVariable  = (TypeVariable) type;
                    System.out.println("TypeVariable：" + typeVariable);
                }else{
                    Class clazz = (Class) type;
                    System.out.println("type：" + clazz);
                }
            }
        }
    }
}
