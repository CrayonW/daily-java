package com.mrthacker.hard;

import java.util.Vector;

/**
 * @ClassName Regular
 * @Description 正则匹配
 * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符。
 * '*' 匹配零个或多个前面的元素。
 * @Author mrthakcer
 * @Date 2019-04-18
 **/
public class Regular {
    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isMatch("", ""));
    }
}
