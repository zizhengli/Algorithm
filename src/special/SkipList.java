package special;

import java.util.ArrayList;
import java.util.List;

public class SkipList {

    private final static double DEFAULT_PROBABILITY = 0.5;
    private SkipNode head;
    private int maxLevel;

    // constructor
    // It accepts an integer value
    public SkipList(int numerOfQueries){
        head = new SkipNode(-1);
        head.nexts.add(null);
        maxLevel = 0;
    }

    // A function to insert a new node into the skip list
    // It accepts an integer value
    // It returns nothing
    public void insert(int val) {
        SkipNode less = findLessNode(val);
        SkipNode node = less.nexts.get(0);
        if(node != null && node.getNextByLevel(0) != null && node.getNextByLevel(0).val == val) {
            return;
        }
        int newLevel = 0;
        while(Math.random() < DEFAULT_PROBABILITY) {
            newLevel++;
        }
        while(newLevel > maxLevel) {
            head.nexts.add(null);
            maxLevel++;
        }
        SkipNode newNode = new SkipNode(val);
        for(int i = 0; i <= newLevel; i++) {
            newNode.nexts.add(null);
        }
        int level = maxLevel;
        SkipNode pre = head;
        while(level >= 0) {
            pre = findNodeAtSameLevel(val, pre, level);
            if(level <= newLevel) {
                newNode.nexts.set(level, pre.nexts.get(level));
                pre.nexts.set(level, newNode);
            }
            level--;
        }
    }
    // A function to search node in the skip list
    // It accepts an integer value
    // It returns a boolean value
    public boolean isPresent(int val){
        SkipNode less = findLessNode(val);
        SkipNode next = less.nexts.get(0);
        return next != null && next.val == val;
    }

    // A function to renove node from the skip list
    // It accepts an integer value
    // It returns nothing
    public void remove(int val) {
        if(!isPresent(val)) {
            return;
        }
        int level = maxLevel;
        SkipNode pre = head;
        while (level >= 0) {
            pre = findNodeAtSameLevel(val, pre, level);
            SkipNode next = pre.nexts.get(level);
            if (next != null && next.val == val) {
                pre.nexts.set(level, next.nexts.get(level));
            }
            if (level != 0 && pre == head && pre.nexts.get(level) == null) {
                head.nexts.remove(level);
                maxLevel--;
            }
            level--;
        }
    }

    private SkipNode findLessNode(int val) {
        int level = maxLevel;
        SkipNode cur = head;
        while(level >= 0) {
            cur = findNodeAtSameLevel(val, cur, level--);
        }
        return cur;
    }

    private SkipNode findNodeAtSameLevel(int val, SkipNode current, int level) {
        SkipNode next = current.getNextByLevel(level);
        while(next != null && lessThan(val, next.getValue())) {
            current = next;
            next = current.getNextByLevel(level);
        }
        return current;
    }

    private boolean lessThan(int val1, int val2) {
        return val1 < val2;
    }

    private class SkipNode {
        int val;
        List<SkipNode> nexts;
        SkipNode(int val) {
            this.val = val;
            nexts = new ArrayList();
        }
        int getValue() {
            return val;
        }
        SkipNode getNextByLevel(int level) {
            return nexts.get(level);
        }
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList(10);

        skipList.insert(1);
        skipList.insert(10);
        skipList.insert(9);
        skipList.insert(4);
        skipList.insert(11);
        skipList.insert(5);
        skipList.insert(2);
        skipList.insert(17);

        System.out.println(skipList.isPresent(10));

        skipList.remove(10);

        System.out.println(skipList.isPresent(10));
    }
}
