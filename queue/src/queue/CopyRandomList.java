package queue;

import org.junit.Test;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
public class CopyRandomList {
    @Test
    public void test(){
        Node node0  = new Node(7);
        Node node1 = new Node(13);
        Node node2 = new Node(11);
        Node node3 = new Node(10);
        Node node4 = new Node(1);
        node0.next=node1;
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node0.random=null;
        node1.random=node0;
        node2.random=node4;
        node3.random=node2;
        node4.random=node0;

        Node node = copyRandomList(node0);
    }
    public Node copyRandomList(Node head) {
        if (head ==null) return null;
        Node p = head;
        Node q;
        Node newHead;
        // 复制节点插入到原节点的后面
        while (p!=null){
            q = new Node(p.val);
            q.random = p.random;
            q.next = p.next;
            p.next = q;
            p = q.next;
        }
        // p指向复制出来的节点
        p = head.next;
        // 复制出来的节点random指向后一个
        while (p!=null){
            if(p.random!=null){
                p.random=p.random.next;
                p = p.next;
                if(p!=null){
                    p=p.next;
                }
            }
        }
        newHead = head.next;
        p = head;
        while (p!=null){
            q = p.next;
            p.next = q.next;
            if(p.next!=null){
                q.next = p.next.next;
            }
            p = p.next;
        }
        return newHead;
    }
}