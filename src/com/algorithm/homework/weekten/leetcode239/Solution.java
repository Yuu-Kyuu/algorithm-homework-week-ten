package com.algorithm.homework.weekten.leetcode239;

import java.util.PriorityQueue;

/**
 * @author qiuch
 * Time complexity : O(nlogn) 插入堆O(nlogn)，取出O(1)
 * Space complexity : O(n)
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        //排序，如果值相等，取下标大的值在堆顶（下标不可能相等）
        PriorityQueue<int[]> heep = new PriorityQueue<>((pair1, pair2) -> pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1]);
        for (int i = 0; i < k; ++i) {
            heep.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = heep.peek()[0];
        for (int i = k; i < n; ++i) {
            heep.offer(new int[]{nums[i], i});
            while (heep.peek()[1] <= i - k) {
                heep.poll();
            }
            ans[i - k + 1] = heep.peek()[0];
        }
        return ans;
    }
}