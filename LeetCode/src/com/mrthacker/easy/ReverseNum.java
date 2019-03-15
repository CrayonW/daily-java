package com.mrthacker.easy;

/**
 * @ClassName ReverseNum
 * @Description TODO
 * @Author wangjun
 * @Date 2019/3/14 10:54
 **/
public class ReverseNum {
    public static int reverse(int x) {
        int num = x >= 0 ? x: (0-x);
        char[] origin = (num + "").toCharArray();
        char[] result = new char[origin.length];
        boolean flag = true;
        for (int i = origin.length - 1, j = 0; i >= 0 && j < origin.length; i--, j++) {
            if (flag) {
                if (origin[i] != 0) {
                    result[j] = origin[i];
                    flag = false;
                }
            } else {
                result[j] = origin[i];
                flag = false;
            }
        }
        return Integer.valueOf(x >= 0 ? new String(result):"-" + new String(result));
    }

    public static void main(String[] args) {
        System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(120));
    }
}
