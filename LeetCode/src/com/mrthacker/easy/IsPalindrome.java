package com.mrthacker.easy;

/**
 * @ClassName IsPalindrome
 * @Description TODO
 * @Author wangjun
 * @Date 2019/3/15 9:47
 **/
public class IsPalindrome {
    public static boolean isPalindrome(int x) {
        boolean result = false;
        int num = x;
        if (x >= 0) {
            int res = 0;
            while (x != 0) {
                res = res*10 + x%10;
                x /= 10;
            }
            if (res == num) {
                result = true;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
    }
}
