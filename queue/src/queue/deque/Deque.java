package queue.deque;

import queue.Queue;

public class Deque {
    Node head,tail;
    int cnt;
    Deque(){
        cnt = 0;
        head.next=tail;
        head.pre=null;
        tail.next=null;
        tail.pre=head;
    }
    void pushBack(int val){
        tail.insertPre(new Node(val));
        cnt +=1;
    }
    void pushFront(int val){
        head.insertNext(new Node(val));
        cnt +=1;
    }
    int popBack(){
        if(isEmpty()) return -1;
        int ret = tail.pre.val;
        tail.deletePre();
        cnt -=1;
        return ret;
    }
    int popFront(){
        if(isEmpty()) return -1;
        int ret = head.next.val;
        head.deleteNext();
        cnt -=1;
        return ret;
    }
    boolean isEmpty(){
        return head.next==tail;
    }
    int size(){
        return cnt;
    }
    int front(){
        int ret = head.next.val;
        return ret;
    }
    int back(){
        int ret = tail.pre.val;
        return ret;
    }

}

class Node{
    int val;
    Node pre,next;
    Node(){
        this.val = 0;
        this.pre=null;
        this.next=null;
    }
    Node(int val){
        this.val =val;
    }
    void next(Node next){
        this.next=next;
    }
    void pre(Node pre){
        this.pre=pre;
    }
    void insertPre(Node p){
        p.pre=pre;
        p.next=this;
        if(this.pre!=null){
            this.pre.next=p;
        }
        this.pre=p;
    }
    void insertNext(Node p){
        p.pre=this;
        p.next=this.next;
        if(this.next!=null){
            this.next.pre=p;
        }
        this.next=p;
    }
    void deletePre(){
        if(this.pre==null) return;;
        Node p = this.pre;
        this.pre=p.pre;
        if(p.pre!=null) p.pre.next=this;
    }
    void deleteNext(){
        if(this.next==null) return;;
        Node p = this.next;
        this.next=p.next;
        if(p.next!=null) p.next.pre=this;
    }
}

class FrontMiddleBackQueue {

    Deque q1,q2;

    public FrontMiddleBackQueue() {

    }

    public void pushFront(int val) {
        q1.pushFront(val);
        update();
    }

    public void pushMiddle(int val) {
        if (q1.size()>q2.size()){
            q2.pushFront(q1.back());
            q1.popBack();
        }
        q1.pushBack(val);
        return;
    }

    public void pushBack(int val) {
        q2.pushBack(val);
        update();
    }

    public int popFront() {
        if(isEmpty()) return -1;
        int ret = q1.popFront();
        update();
        return ret;
    }

    public int popMiddle() {
        if(isEmpty()) return -1;
        int ret = q1.popBack();
        update();
        return ret;
    }

    public int popBack() {
        if(isEmpty()) return -1;
        int ret;
        if (q2.isEmpty()){
            ret = q1.popBack();
        }else {
            ret = q2.popBack();
        }
        update();
        return ret;
    }

    public boolean isEmpty(){
        return q1.cnt==0;
    }

    public void update(){

    }
}
