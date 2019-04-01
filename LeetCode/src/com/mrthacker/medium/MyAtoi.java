package com.mrthacker.medium;

/**
 * @ClassName MyAtoi
 * @Description 将给定的字符串输出为整数，范围为 [−231,  231 − 1]
 * 超出返回边界整数 0~9: 48-57; '-': 45; '+': 43; A~Z:65-90; a~z: 97-122; '.': 46;
 * @Author wangjun
 * @Date 2019/4/1 10:13
 **/
public class MyAtoi {

    public static int myAtoi(String str) {
        int result = 0, length = str.length();
        char[] origin = str.toCharArray();
        StringBuilder numString = new StringBuilder();
        boolean isBegin = false;
        int  MIN_VALUE = 0x80000000;
        int  MAX_VALUE = 0x7fffffff;
        for (int i=0; i<length; i++) {
            int aciiNum = origin[i];
            if ((aciiNum >= 65 && aciiNum <= 90)
                    || (aciiNum >= 97 && aciiNum <=122)
                    || aciiNum == 46
                    || (isBegin && (aciiNum == 45 || aciiNum == 43 || aciiNum == 32))) {//字母直接结束
                break;
            }
            if (aciiNum == 45 || aciiNum == 43) {//是否第一个
                numString.append(origin[i]);
                isBegin = true;
            } else {
                if (aciiNum >= 48 && aciiNum <= 57) {
                    numString.append(origin[i]);
                    isBegin = true;
                } else {
                    continue;
                }
            }
        }
        if (numString.length() > 0) {
            int num = numString.subSequence(0, 1).charAt(0);
            if (numString.length() == 1
                    && (num == 45 || num == 43)) {
                return 0;
            }
            try {
                result = Integer.valueOf(numString.toString());
            } catch (NumberFormatException e) {
                if (numString.subSequence(0, 1).charAt(0) == 45) {
                    result =  MIN_VALUE;
                } else {
                    result =  MAX_VALUE;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("   +0 123"));
        System.out.println(myAtoi("0-1"));
        System.out.println(myAtoi("3.14159"));
        System.out.println(myAtoi("words and 987"));
    }
}
