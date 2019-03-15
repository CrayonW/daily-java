package com.mrthacker.medium;

/**
 * @ClassName Convert
 * @Description 将给定的字符串和给定的行数，以从左到右，从上到下Z字形排列
 * @Example
 * @Analysis
 * @Author wangjun
 * @Date 2019/3/12 14:12
 **/
public class Convert {

    public static String convert(String s, int numRows) {
        char[] origin = s.toCharArray();
        int length = origin.length;
        int columNums = length/(numRows - 2) / (numRows - 1) + 1;
        char[][] location = new char[s.length()][numRows];
        int k = 0;
        for (int i = 0; i < columNums; i ++) {
            for (int j = 0; j < numRows; j ++) {
                int num = j % numRows;
                if (num == 0 || numRows - j == num) {
                   location[i][j] = origin[k];
                   k ++;
                }
            }
        }
        for (int i = 0; i < columNums; i ++) {
            for (int j = 0; j < numRows; j ++) {
                System.out.print("".equals(location[i][j]+"") ? "*": location[i][j]);
            }
            System.out.println();
        }
        return "";
    }

    public static void main(String[] args) {
        convert("LEETCODEISHIRING", 3);
    }
}
