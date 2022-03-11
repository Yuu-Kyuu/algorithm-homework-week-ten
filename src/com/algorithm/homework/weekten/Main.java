package com.algorithm.homework.weekten;

import com.algorithm.homework.weekten.leetcode1206.Skiplist;

public class Main {

    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();
        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        skiplist.search(0);   // 返回 false
        skiplist.add(4);
        skiplist.search(1);   // 返回 true
        skiplist.erase(0);    // 返回 false，0 不在跳表中
        skiplist.erase(1);    // 返回 true
        skiplist.search(1);   // 返回 false，1 已被擦除

//        ["Skiplist","add","add","add","add","add",
//        "erase","erase","add","search","search",
//        "add","erase","search","add","add",
//        "add","erase","search","erase","search",
//        "search","search","erase","erase","search",
//        "erase","add","add","erase","add",
//        "search", "search","search","search","search"]
//[[],[9],[4],[5],[6],[9],[2],[1],[2],[7],[4],[5],[6],[5],[6],[7],[4],[3],[6],[3],[4],[3],[8],[7],[6],[7],[4],[1],[6],[3],[4],[7],[6],[1],[0],[3]]

        Skiplist skiplist2 = new Skiplist();
        skiplist2.add(9);
        skiplist2.add(4);
        skiplist2.add(5);
        skiplist2.add(6);
        skiplist2.add(9);

        skiplist2.erase(2);
        skiplist2.erase(1);
        skiplist2.add(2);
        skiplist2.search(7);
        skiplist2.search(4);

        skiplist2.add(5);
        skiplist2.erase(6);
        skiplist2.search(5);
        skiplist2.add(6);
        skiplist2.add(7);

        skiplist2.add(4);
        skiplist2.erase(3);
        skiplist2.search(6);
        skiplist2.erase(3);
        skiplist2.search(4);

        skiplist2.search(3);
        skiplist2.search(8);
        skiplist2.erase(7);
        skiplist2.erase(6);
        skiplist2.search(7);

        skiplist2.erase(4);
        skiplist2.add(1);
        skiplist2.add(6);
        skiplist2.erase(3);
        skiplist2.add(4);

        skiplist2.search(7);
        skiplist2.search(6);
        skiplist2.search(1);
        skiplist2.search(0);
        skiplist2.search(3);
    }
}
