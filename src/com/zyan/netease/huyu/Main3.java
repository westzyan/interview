package com.zyan.netease.huyu;

import java.util.*;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t =scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int[][] nums = new int[n][3];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 3; j++) {
                    nums[i][j] = scanner.nextInt();
                }
            }
            Stack<int[]> stack = new Stack<>();
            List<Temp> temps = new ArrayList<>();


            for (int i = 0; i < n; i++) {
                if (stack.isEmpty()) {
                    stack.push(nums[i]);
                }else if (stack.peek()[1] == nums[i][1]) {
                    int num = stack.pop()[0] - nums[i][0];
                    Temp temp = new Temp(nums[i][1], num, 0);
                    temps.add(temp);
                } else {
                    stack.push(nums[i]);
                }
            }
            temps.sort((o1, o2) -> {
                if (o1.selfTime > o2.selfTime) {
                    return 1;
                } else if (o1.selfTime < o2.selfTime) {
                    return -1;
                } else {
                    return o1.id - o2.id;
                }
            });
            System.out.println(temps.get(0).id);

            int[] ids = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                ids[i] = nums[i][1];
            }
            Map<Integer, Pos> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(nums[i][1])) {
                     map.get(nums[i][1]).setLast(i);
                } else {
                    Pos pos = new Pos();
                    pos.setFirst(i);
                    map.put(nums[i][1], pos);
                }
            }
            for (int id : ids) {
                Pos pos = map.get(id);
                int first = pos.first;
                int last = pos.last;
                while (first < last) {

                }
            }

        }
    }
}
class Temp{
    int id;
    int time;
    int selfTime;

    public Temp(int id, int time, int selfTime) {
        this.id = id;
        this.time = time;

        this.selfTime = selfTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getSelfTime() {
        return selfTime;
    }

    public void setSelfTime(int selfTime) {
        this.selfTime = selfTime;
    }
}

class Pos{
    int first;
    int last;

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }
}