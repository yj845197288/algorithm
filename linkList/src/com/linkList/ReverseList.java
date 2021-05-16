package com.linkList;

import org.junit.Test;

//反转链表
public class ReverseList {
    // 反转n个节点 复杂方法
    // 反转前n,返回反转之后的头
    private ListNode reverseN1(ListNode head,int n){
        if(n==1) return head;
        // 在最外循环反转完成后成为最后一个(同时在最里面那个迭代是第n个元素)
        ListNode last = head.next;
        // 反转链表（第2到结尾）
        ListNode first=reverseN1(head.next,n-1);
        head.next=last.next;
        last.next=head;
        return first;
    }

    ListNode successor = null; // 后驱节点

    //反转n个节点  容易理解的方法
    // 反转以 head 为起点的 n 个节点，返回新的头结点
    ListNode reverseN2(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN2(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }

    // 反转链表的一部分
    ListNode reverseBetween(ListNode head,int m,int n){
        if(m==1){
            return reverseN2(head,n);
        }
        head.next=reverseBetween(head.next,m-1,n-1);
        return head;
    }

    @Test
    public void test(){
        ListNode head =null;
        head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        reverseN1(head,2);
    }

}


class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}