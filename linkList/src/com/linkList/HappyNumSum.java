package com.linkList;

import org.junit.Test;

import java.util.HashSet;

public class HappyNumSum {
    @Test
    public void sum(){
        int sum=0;
        for(int a =100000;a>0;a--){
            if(isHappy(a)) {
                sum+=a;
            }
        }
        System.out.println(sum);
    }

    public boolean isHappy(int n) {
        int p = n,q=n;
        do {
            p = getNext(p);
            q = getNext(getNext(q));
        } while (p!=q&&q!=1);
        return q==1;
    }

    public int getNext(int x) {
        int sum=0;
        while (x>0){
            sum+=(x%10)*(x%10);
            x/=10;
        }
        return sum;
    }
}
