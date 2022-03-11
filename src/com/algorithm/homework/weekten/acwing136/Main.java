package com.algorithm.homework.weekten.acwing136;


import java.util.Scanner;
import java.util.TreeMap;

/**
 * treemap，key用红黑树实现
 *
 * @author qiucihang
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TreeMap<Integer, Integer> data = new TreeMap<>();

        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        for (int i = 0; i < n; i++) {
            int current = s.nextInt();
            data.put(current, i);
            //第二个数开始做邻值查找
            if (i > 0) {
                //当前值的小邻值
                Integer currentLowKey = data.lowerKey(current);
                //当前值的大邻值
                Integer currentHighKey = data.higherKey(current);

                int currentAnswer = 0;

                if (currentLowKey == null) {
                    //没有小邻值取大邻值
                    currentAnswer = currentHighKey;
                } else if (currentHighKey == null) {
                    //没有大邻值取小邻值
                    currentAnswer = currentLowKey;
                } else {
                    int diffLowKey = current - currentLowKey;
                    int diffHighKey = currentHighKey - current;
                    //取差值小的key作为答案
                    if (diffLowKey < diffHighKey) {
                        currentAnswer = currentLowKey;
                    } else if (diffLowKey > diffHighKey) {
                        currentAnswer = currentHighKey;
                    } else {
                        //如果大小邻值差值相等，取key较小值
                        if (currentLowKey < currentHighKey) {
                            currentAnswer = currentLowKey;
                        } else {
                            currentAnswer = currentHighKey;
                        }
                    }
                }

                System.out.println(Math.abs(current - currentAnswer) + " " + (data.get(currentAnswer) + 1));
            }
        }
        s.close();
    }
}