package heap;

import org.junit.Test;

import java.util.List;
import java.util.Vector;

public class Leetcode {

    public class HeapImp<T extends Comparable<T>> {
        private Vector<T> data = new Vector<>();
        private int cnt = 0;
        private int type;

        public HeapImp(int type) {
            this.type = type;
        }


        private void swap(List<T> t, int a, int b) {
            T temp = t.get(a);
            t.set(a, t.get(b));
            t.set(b, temp);
        }

        private void shiftUp(int ind) {
            while (ind > 0 && data.get((ind - 1) / 2).compareTo(data.get(ind)) == type) {
                swap(data, (ind - 1) / 2, ind);
                ind = (ind - 1) / 2;
            }
        }

        private void shiftDown(int ind) {
            int n = cnt - 1;
            while (ind * 2 + 1 <= n) {
                int temp = ind;
                if (data.get(temp).compareTo(data.get(ind * 2 + 1)) == type)
                    temp = ind * 2 + 1;
                if (ind * 2 + 2 <= n && data.get(temp).compareTo(data.get(ind * 2 + 2)) == type)
                    temp = ind * 2 + 2;
                if (temp == ind) break;
                swap(data, temp, ind);
                ind = temp;
            }
        }

        private void push(T x) {
            data.add(x);
            cnt++;
            shiftUp(cnt - 1);
        }

        private void pop() {
            if (size() == 0) return;
            swap(data, 0, cnt - 1);
            data.remove(cnt - 1);
            cnt--;
            shiftDown(0);
        }

        private T top() {
            return data.get(0);
        }

        private int size() {
            return cnt;
        }

    }

    // 剑指offer 40
    @Test
    public void getLeastNumbers() {
        int[] arr = {3, 2, 1};
        int k = 2;
        HeapImp heap = new HeapImp<Integer>(-1);

        for (int a : arr) {
            heap.push(a);
            if (heap.size() > k)
                heap.pop();
        }
        int[] a = new int[k];
        List<Integer> data = heap.data;
        data.forEach(e -> System.out.println(e));

    }

    @Test
    public void lastStoneWeight() {
        int re = 0;
        int[] stones = {2,7,4,1,8,1};
        HeapImp<Integer> h = new HeapImp(-1);
        for (int a : stones) {
            h.push(a);
        }
        while (h.size() > 1) {
            int y = h.top();
            h.pop();
            int x = h.top();
            h.pop();
            if (x == y) continue;
            h.push(y - x);
        }
        if (h.size() == 0) {
        }else {
            re = h.top();
        }
        System.out.println(re);

    }
}
