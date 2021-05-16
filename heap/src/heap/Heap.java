package heap;

import java.util.Scanner;

/**
 * 堆的实现  大顶堆
 */
public class Heap {
    private int[] data = new int[100];
    private int cnt = 0;

    private void swap(int[] t, int a, int b) {
        int temp = t[a];
        t[a] = t[b];
        t[b] = temp;
    }

    private void shiftUp(int ind) {
        while (ind > 0 && data[(ind - 1) / 2] < data[ind]) {
            swap(data, (ind - 1) / 2, ind);
            ind = (ind - 1) / 2;
        }
    }

    private void shiftDown(int ind) {
        int n = cnt - 1;
        while (ind * 2 + 1 <= n) {
            int temp = ind;
            if (data[temp] < data[ind * 2 + 1])
                temp = ind * 2 + 1;
            if (ind * 2 + 2 <= n && data[temp] < data[ind * 2 + 2])
                temp = ind * 2 + 2;
            if (temp == ind) break;
            swap(data, temp, ind);
            ind = temp;
        }
    }

    private void push(int x) {
        data[cnt++] = x;
        shiftUp(cnt - 1);
    }

    private void pop() {
        if (size() == 0) return;
        swap(data, 0, cnt - 1);
        cnt--;
        shiftDown(0);
    }

    private int top() {
        return data[0];
    }

    private int size() {
        return cnt;
    }

    private void output(int n){
        System.out.printf("heap :");
        for (int i=0;i<n;i++){
            System.out.printf("%d ",data[i]);
        }
        System.out.printf("\n");
    }

    public static void main(String[] args) {
        Heap heapStructure = new Heap();
        Scanner inp = new Scanner(System.in);
        int maxN = 0;
        while (inp.hasNextInt()) {
            int i = inp.nextInt();
            switch (i) {
                case 0: {
                    int val = inp.nextInt();
                    System.out.printf("push %d to heap\n", val);
                    heapStructure.push(val);
                }
                break;
                case 1: {
                    System.out.printf("pop %d from heap\n", heapStructure.top());
                    heapStructure.pop();
                }
                break;
                case 2:{
                    heapStructure.output(maxN);
                }
                break;
            }
            maxN = Math.max(heapStructure.cnt,maxN);
            heapStructure.output(heapStructure.cnt);
        }
    }

}
