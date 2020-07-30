package com.shyfay.usual.java7;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Notes java7提供了新的关于文件路径的API Path
 * @Author muxue
 * @Since 7/29/2020
 */
public class PathTest {
    public static void main(String[] args) {
        Path path1 = Paths.get("folder1", "sub1");
        System.out.println(path1);//folder1\sub1
        Path path2 = Paths.get("folder2", "sub2");
        System.out.println(path1.resolve(path2)); //folder1\sub1\folder2\sub2
        System.out.println(path1.resolveSibling(path2));//folder1\folder2\sub2
        System.out.println(path1.relativize(path2));//..\..\folder2\sub2
        System.out.println(path1.subpath(0, 1));//folder1
        System.out.println(path1.startsWith(path2));//false
        System.out.println(path1.endsWith(path2));//false
    }
}
