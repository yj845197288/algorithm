package heap;

import java.util.List;
import java.util.Vector;

/**
 * 堆的实现  大顶堆
 */
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
        data.remove(cnt-1);
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
class CMP implements Comparable{
    private int[] a = new int[2];
    private int[] getA(){
        return this.a;
    }
    public CMP(int a,int b){
        this.a[0]=a;
        this.a[1]=b;
    }
    @Override
    public int compareTo(Object o) {
        int i = ((CMP) o).a[0] + ((CMP) o).a[1];
        return Integer.compare(this.a[0] + this.a[1], i);
    }
}
