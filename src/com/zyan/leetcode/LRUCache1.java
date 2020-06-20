package com.zyan.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache1 {

    private Map<Integer, Node> map;
    private DoubleList cache;
    private int cap;

    public LRUCache1(int capacity){
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key){
        if (!map.containsKey(key)){
            return -1;
        }
        int val = map.get(key).val;
        // 利用 put 方法把该数据提前
        put(key, val);
        return val;
    }

    public void put(int key, int val){
        Node x = new Node(key, val);
        if (map.containsKey(key)){
            // 删除旧的节点，新的插到头部
            cache.remove(map.get(key));
            cache.addFirst(x);
            // 更新 map 中对应的数据
            map.put(key, x);
        }else {
            if (cap == cache.size()){
                // 删除链表最后一个数据
                Node last = cache.removeLast();
                map.remove(last.key);
            }
            // 直接添加到头部
            cache.addFirst(x);
            map.put(key, x);
        }
    }
}
