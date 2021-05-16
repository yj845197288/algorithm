package com.linkList;

import org.junit.Test;

/*
 相对方法构造链表
 */
public class StructureLinkList {

    int data[] = new int[20];
    int next[] = new int[20];

    private void  add(int inx,int p,int val){
        next[inx]=p;
        data[p]=val;
    }

    @Test
    public void main(){
        int head = 3;
        data[3]=0;
        add(3,5,1);
        add(5,7,2);
        add(7,9,3);
        add(9,10,100);
        int p=head;
        while (p!=0){
            System.out.print(data[p]+"->");
            p=next[p];
        }
        System.out.println("\n");
    }

}
