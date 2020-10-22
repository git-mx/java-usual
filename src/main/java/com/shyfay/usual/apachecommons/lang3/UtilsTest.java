package com.shyfay.usual.apachecommons.lang3;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassPathUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Notes 记录几个比较实用的utils
 * ArrayUtils.hashCode 相同个数相同顺序的数组的hashCode会是一样的
 *
 * @Author muxue
 * @Since 8/5/2020
 */
public class UtilsTest {
    public static void main(String[] args) {
        //1.ArrayUtils
        //toArray
        Integer[] integers = ArrayUtils.toArray(1, 2, 3);
        Serializable[] serializables = ArrayUtils.toArray(1, 2, 3);
        //nullToEmpty 如果数组不为空返回原数组
        integers = ArrayUtils.nullToEmpty(integers);
        //toObject/toPrimitive 实现非封箱类型与封箱类型之间的转换
        int[] ints = ArrayUtils.toPrimitive(integers);
        integers = ArrayUtils.toObject(ints);


        //2.CharUtils
        //toCharacterObject/toChar, 实现char或者Sting与Character的互转
        //toIntValue 把char或者Character转换成对应的int值
        //isAscii 判断字符是否是ASCII码

        //3.ClassPathUtils 处理类路径的工具类
        System.out.println(ClassPathUtils.toFullyQualifiedName(Integer.class, "Integer.value"));
        System.out.println(ClassPathUtils.toFullyQualifiedName(Integer.class.getPackage(), "Integer.class"));

        //4.ClassUitls 操作java类
        System.out.println(int[].class.getSimpleName());
        System.out.println(ClassUtils.getShortClassName(int[].class));
        System.out.println(ClassUtils.getPackageName(String.class));
        //获取所有的父类，不包括接口
        List<Class<?>> allSuperClasses =  ClassUtils.getAllSuperclasses(ArrayList.class);
        System.out.println(allSuperClasses);
        //getAllInterfaces 同上
        //convertClassNamesToClasses/convertClassesToNames
        List<Class<?>> classes = ClassUtils.convertClassNamesToClasses(Arrays.asList("java.lang.Integer", "java.lang.String"));

        //org.springframework.util.ClassUtils.convertClassNameToResourcePath(applicationContext.getEnvironment().resolvePlaceholders(packageName));
        //isPrimitiveOrWrapper isPrimitiveWrapper primitiveToWrapper primitivesToWrappers wapperToPrimitive 包装类型与非包装类型检测与互转

        //isAssignable 是否是相同的class类型，支持class和数组等等
        System.out.println(ClassUtils.isAssignable(int.class, Integer.class)); //true
        //isInnerClass
        //getClass 加强版本的Class.forName()可以指定是否马上初始化该类
        //hierachy 获取类的继承结构

        //5.EnumUtils 辅助操作枚举的一些工具 getEnum getEnumList getEnumMap isValidEnum

        //6.RandomStringUtils 生成随机字符串使用
        System.out.println(RandomStringUtils.random(10));//可能是乱码
        System.out.println(RandomStringUtils.random(3, 'a', 'b', 'c'));
        System.out.println(RandomStringUtils.randomAscii(10));//ASCII码内的字符组成的字符串
        System.out.println(RandomStringUtils.randomNumeric(5));//随机一个5位长度的由数字组成的字符串
        //6.RandomUtils

        //RegExUtils 正则处理 removeAll removeFirst removePattern replaceAll replaceFirst

    }
}
