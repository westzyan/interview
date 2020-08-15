package com.zyan.xiecheng;

/**
 * Created by zhangyan122 on 2020/8/15
 */
import java.util.*;
import java.util.stream.Collectors;

class WorkflowNode {
    String nodeId;
    int timeoutMillis;
    List<WorkflowNode> nextNodes;
    boolean initialised;

    public static WorkflowNode load(String value) {
        // Create head node;
        Map<String, WorkflowNode> map = new HashMap<>();
        WorkflowNode head = new WorkflowNode("HEAD", 0, null);
        map.put(head.nodeId, head);

        for (String nodeValue : value.split("\\|")) {
            String[] properties = nodeValue.split("\\`");
            WorkflowNode node = map.get(properties[0]);

            node.timeoutMillis = Integer.parseInt(properties[1]);
            node.initialised = true;

            // Check next nodes
            if (properties[2].equals("END")) {
                continue;
            }
            node.nextNodes = Arrays.stream(properties[2].split(","))
                    .map(p -> new WorkflowNode(p, 0, null))
                    .collect(Collectors.toList());
            node.nextNodes.forEach(p -> map.put(p.nodeId, p));

            map.put(node.nodeId, node);
        }

        return head;
    }

    public WorkflowNode(String nodeId, int timeoutMillis, List<WorkflowNode> nextNodes) {
        this.nodeId = nodeId;
        this.timeoutMillis = timeoutMillis;
        this.nextNodes = nextNodes;
    }
}

public class Main2 {
    public static PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    public static void main(String args[]) {
        int count = 0;
        Scanner cin = new Scanner(System.in);
        WorkflowNode node = null;
        while (cin.hasNext()) {
            node = WorkflowNode.load(cin.next());
        }
        func(node, 0);
        System.out.println(queue.peek());
    }
    public static void func(WorkflowNode head, int count){

        if (!head.initialised) {
            queue.add(count);
            return;
        }
        for (WorkflowNode nextNode : head.nextNodes) {
            func(nextNode, count + nextNode.timeoutMillis);
        }

    }
}