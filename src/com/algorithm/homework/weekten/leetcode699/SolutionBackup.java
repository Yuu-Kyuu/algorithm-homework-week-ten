package com.algorithm.homework.weekten.leetcode699;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author qiuch
 * m：position中跨越的坐标区间
 * Time complexity : O(2m)->O(m)
 * Space complexity : O(m+n) position中跨越的坐标区间
 * <p>
 * 内存超限了！！！
 */
class SolutionBackup {
    public List<Integer> fallingSquares(int[][] positions) {
        HashMap<Integer, Integer> events = new HashMap<>();
        List<Integer> ans = new ArrayList<>(positions.length);
        int globalMax = 0;
        for (int i = 0; i < positions.length; i++) {
            int localMax = 0;
            for (int j = positions[i][0]; j < positions[i][0] + positions[i][1]; j++) {
                int currentVal = events.getOrDefault(j, 0) + positions[i][1];
                localMax = Integer.max(localMax, currentVal);
            }
            for (int j = positions[i][0]; j < positions[i][0] + positions[i][1]; j++) {
                events.put(j, localMax);
            }
            globalMax = Integer.max(globalMax, localMax);
            ans.add(globalMax);
        }
        return ans;
    }

    public static void main(String[] args) {
        new SolutionBackup().fallingSquares(new int[][]{{1, 2}, {2, 3}, {6, 1}});
        new SolutionBackup().fallingSquares(new int[][]{{9, 7}, {1, 9}, {3, 1}});
        new SolutionBackup().fallingSquares(new int[][]{{9, 6}, {2, 2}, {2, 6}});
    }
}