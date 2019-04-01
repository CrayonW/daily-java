package com.mrthacker.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Convert
 * @Description 将给定的字符串和给定的行数，以从左到右，从上到下Z字形排列
 * @Example
 * @Analysis
 * @Author wangjun
 * @Date 2019/3/12 14:12
 **/
public class Convert {

    /**
     * 思路：按照Z字顺序给每个字符编号，然后进行同级排序
     * 如何给字符进行准确的编号？
     * 1, 2, 3, ..., numRows, numRows, numRows-1, ...., 1
     * 就是数字变换
     */
    public static String convert(String s, int numRows) {
        String result = new String();
        char[] origin = s.toCharArray();
        int num = 1;
        boolean flag = true;
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < origin.length; i++) {
            if (map.containsKey(num)) {
                String append = map.get(num) + origin[i];
                map.put(num, append);
            } else {
                map.put(num, origin[i] + "");
            }
            if (numRows != 1) {
                if (num % numRows == 0) {
                    flag = false;
                }
                if (num == 1) {
                    flag = true;
                }
                if (flag) {
                    num++;
                } else {
                    num--;
                }
            }
        }
        for (Integer integer:map.keySet()) {
            result += map.get(integer);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(convert("LEETCODEISHIRING", 3));
        System.out.println(convert("ABC", 1));
    }


}
