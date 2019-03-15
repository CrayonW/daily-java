package com.mrthacker.easy;

/**
 * @ClassName RomanToInt
 * @Description TODO
 * @Author wangjun
 * @Date 2019/3/15 10:06
 **/
public class RomanToInt {
    public static int romanToInt(String s) {
        int valuse = 0;
        char[] origin = s.toCharArray();
        int length = origin.length;
        for (int i=0; i<length; i++) {
            switch(origin[i]) {
                case 'I':
                    if (i+1 < length && origin[i+1] == 'V') {
                        valuse += 4;i ++;
                    } else if (i+1 < length && origin[i+1] == 'X') {
                        valuse += 9;i ++;
                    } else {
                        valuse += 1;
                    }
                    break;
                case 'V':
                    valuse += 5;
                    break;
                case 'X':
                    if (i+1 < length && origin[i+1] == 'L') {
                        valuse += 40;i ++;
                    } else if (i+1 < length &&origin[i+1] == 'C') {
                        valuse += 90;i ++;
                    } else {
                        valuse += 10;
                    }
                    break;
                case 'L':
                    valuse += 50;
                    break;
                case 'C':
                    if (i+1 < length && origin[i+1] == 'D') {
                        valuse += 400;i ++;
                    } else if (i+1 < length && origin[i+1] == 'M') {
                        valuse += 900;i ++;
                    } else {
                        valuse += 100;
                    }
                    break;
                case 'D':
                    valuse += 500;
                    break;
                case 'M':
                    valuse += 1000;
                    break;
            }
        }
        return valuse;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("IV"));
    }
}
