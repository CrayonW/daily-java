package com.mrthacker.medium;

/**
 * @ClassName LongestPalindrome
 * @Description 取一段字符串的最大回文(正和反读是一样的)
 * //todo 动态规划， 马拉车算法
 * @Example “baba” => "aba"
 * @Analysis 最小为1位，2
 * @Author wangjun
 * @Date 2019/3/11 14:23
 **/
public class LongestPalindrome {

    /**
     * 中心扩展法
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] origin = s.toCharArray();
        int start = 0, end = 0;
        for (int i = 0; i < origin.length; i ++) {
            int len1 = len(origin, i, i);
            int len2 = len(origin, i, i+1);
            int len = len1 > len2 ? len1:len2;
            if (len > end - start) {
                start = i - (len - 1)/2;
                end = i + len/2;
            }
        }
        return s.substring(start, end + 1);
    }

    public static int len(char[] origin, int left, int right) {
        while (left >= 0 && right < origin.length && origin[left] == origin[right]) {
            left --;
            right ++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
//        System.out.println(longestPalindrome("abacab"));
        System.out.println(longestPalindrome("cbbd"));
//        System.out.println(longestPalindrome("abadd"));
//        System.out.println(longestPalindrome("babadada"));
    }
}
