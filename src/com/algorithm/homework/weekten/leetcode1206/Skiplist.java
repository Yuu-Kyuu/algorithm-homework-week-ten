package com.algorithm.homework.weekten.leetcode1206;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 貌似用概率生成索引是有概率超时的
 * 感觉索引生成得不够均匀，测了几次数据层数都是够的
 * 理论上接近nlogn
 *
 * @author qiucihang
 */
public class Skiplist {
    private int maxLevel;
    private int size;
    private Node head;

    public Skiplist() {
        maxLevel = 1;
        size = 0;
        head = new Node(-1);
    }

    public boolean search(int target) {
        Node node = head;
        while (node != null) {
            //从最上层从左往右查找比target小的最后一个值
            while (node.next != null && node.next.val < target) {
                node = node.next;
            }
            //下一个节点刚好是检索的值
            if (node.next != null && node.next.val == target) {
                return true;
            }
            //若node.next的值大于目标target,下探一层
            node = node.down;
        }
        return false;
    }


    public void add(int num) {
        int updateLevel = dial();
        //记录每一层需要更新的节点
        List<Node> levelNodes = new ArrayList<>(Collections.nCopies(maxLevel, null));
        Node node = head;
        int currentLevel = 1;
        while (node != null) {
            while (node.next != null && node.next.val < num) {
                node = node.next;
            }
            levelNodes.set(currentLevel - 1, node);
            currentLevel++;
            node = node.down;
        }
        //逐级向上更新节点
        Node nextLevelNode = null;
        for (int i = maxLevel - 1; i >= maxLevel - updateLevel; i--) {
            Node newNode = new Node(num);
            // 有空节点证明要插入一个新的头节点
            while (levelNodes.get(i) == null) {
                head = new Node(head);
                levelNodes.add(0, head);
            }
            if (levelNodes.get(i).next != null) {
                newNode.next = levelNodes.get(i).next;
            }

            levelNodes.get(i).next = newNode;
            if (nextLevelNode != null) {
                newNode.down = nextLevelNode;
            }
            nextLevelNode = newNode;
        }

        size++;
        maxLevel = Integer.max(maxLevel, size / 2);
        printNode(head);
    }

    /**
     * @param num 目标数值
     * @return 是否删除了元素
     */
    public boolean erase(int num) {
        Node node = head;
        boolean exist = false;
        //与检索类似
        while (node != null) {
            while (node.next != null && node.next.val < num) {
                node = node.next;
            }
            if (node.next != null && node.next.val == num) {
                Node target = node.next;
                node.next = target.next;
                target.next = null;
                exist = true;
            }
            //就算找到了也要下探把下面层的target数据都删除
            node = node.down;
        }
        //删除了的话，size--
        //考虑要不要缩短索引层数，介于简单考虑不缩短了
        //感觉工程中只要列表扩容了基本不会缩，要么整个列表就不用了
        if (exist) {
            size--;
            maxLevel = Integer.max(maxLevel, size / 2);
            //printNode(head);
        }
        return exist;
    }

    /**
     * 课上讲的概率随机决定要不要生成上层索引
     *
     * @return 生成索引的层数
     */
    private int dial() {
        int ans = 1;
        while ((new Random().nextInt() & 1) == 1 && ans < maxLevel) {
            ans++;
        }
        return ans;
    }

    public void printNode(Node node) {
        Node downNode = node;
        Node currentNode;
        while (downNode != null) {
            currentNode = downNode;
            while (currentNode != null) {
                System.out.print(currentNode.val + "  -> ");
                currentNode = currentNode.next;
            }
            System.out.print("\n");
            downNode = downNode.down;
        }
        System.out.println("--------max level:" + maxLevel + "-------size:" + size + "-------------");
    }


    class Node {
        Integer val;
        Node next;
        Node down;

        public Node(Integer val, Node next, Node down) {
            this.val = val;
            this.next = next;
            this.down = down;
        }

        public Node(Integer val) {
            this.val = val;
        }

        public Node(Node node) {
            this.val = node.val;
            this.next = null;
            this.down = node;
        }
    }
}