package quickSort;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
1.单边递归法
2.无监督partition方法
3.三点取中法
4.小数据规模，停止快排进程
5.使用插入排序进行收尾
 */
public class quickSort {

    final static int threshold = 16;

    static int getMid(int a, int b, int c) {
        if ((b - a) * (a - c) >= 0) {
            return a;
        } else if ((a - b) * (b - c) >= 0) {
            return b;
        } else {
            return c;
        }
    }

    // 左递归法 省去一半递归
    static void quickSortV2(int[] arr, int l, int r) {
        while (l < r) {
            int x = l, y = r, base = arr[l];
            while (x < y) {
                while (x < y && arr[y] >= base) y--;
                if (x < y) arr[x++] = arr[y];
                while (x < y && arr[x] < base) x++;
                if (x < y) arr[y--] = arr[x];
            }
            arr[x] = base;
            quickSortV2(arr, x + 1, r);
            r = x - 1;
        }
    }

    static void __quickSortV3(int[] arr, int l, int r) {
        while (r - l > threshold) {
            int x = l, y = r, base = getMid(arr[l], arr[(l + r) / 2], arr[r]);
            do {
                while (arr[x] < base) x++;
                while (arr[y] > base) y--;
                if (x <= y) {
                    int temp = arr[x];
                    arr[x] = arr[y];
                    arr[y] = temp;
                    x++;
                    y--;
                }
            } while (x <= y);
            __quickSortV3(arr, x, r);
            r = y;
        }
    }
    //无边界判断的插入排序
    static void finalInsertSort(int[] arr,int l,int r){
        int idx=l;
        // 获得数最小的下标
        for (int i=l+1;i<=r;i++){
            if(arr[i]<arr[idx]) idx=i;
        }
        // 将最小的数移动到第一位
        while (idx>l){
            int temp =arr[idx];
            arr[idx]=arr[idx-1];
            arr[idx-1]=temp;
        }

        for (int i =l+2;i<=r;i++){
            int j=i;
            while (arr[j]<arr[j-1]){
                int temp =arr[j];
                arr[j]=arr[j-1];
                arr[j-1]=temp;
                j--;
            }
        }
    }
    // 使用插入排序 左递归法 基准值优化之后的快速排序
    static void quickSortV3(int[] arr,int l,int r){
        __quickSortV3(arr,l,r);
        finalInsertSort(arr,l,r);
    }

    public static void main(String[] args) {
//        int[] arr ={3,1,5,3,2,6,3,1,2,5};
//        quickSortV2(arr,0,arr.length-1);
//        for (int i:arr){
//            System.out.println(i);
//        }

//        int a = 1, b = 2;
//        System.out.println(a);
//        System.out.println(b);

        String a ="asa.sada.ada";
        String[] split = a.split("\\.");
        for (String i:split){
            System.out.println(i);
        }
        System.out.println("dada"+"."+"sdad");
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> list = new LinkedList<>();
        if(k==0){
            return new int[0];
        }
        Deque<Integer> deque = new LinkedList();
        int idx =0;
        while (idx<nums.length){
            if(!deque.isEmpty()&&deque.getFirst()+k<=idx){
                deque.pollFirst();
            }
            while (!deque.isEmpty()&&nums[deque.getLast()]<nums[idx]){
                deque.pollLast();
            }
            deque.offerLast(idx);
            idx++;
            if(idx>=k){
                list.add(nums[deque.getFirst()]);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }


}
