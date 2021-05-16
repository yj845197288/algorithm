package queue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XunHuanQueue {


    public int getKthMagicNumber(int k) {
        ArrayList<Integer> arr = new ArrayList();
        arr.add(1);
        int p3=0,p5=0,p7=0;
        while (arr.size()<k){
            int ans = 3*arr.get(p3);
            ans = Math.min(ans,5*arr.get(p5));
            ans = Math.min(ans,7*arr.get(p7));
            if(3*arr.get(p3)==ans) p3++;
            if(5*arr.get(p5)==ans) p5++;
            if(7*arr.get(p7)==ans) p7++;
            arr.add(ans);
        }
        return arr.get(k-1);
    }
    public boolean hasRepeate(String a){
        int cnt[] = new int[26];
        for (int i=0;!"".equals(a.charAt(i));i++){
            int n = cnt[a.charAt(i) - 'a'];
            n+=1;
            if(n==2){
                return true;
            }
        }
        return false;
    }

    public boolean buddyStrings(String a, String b) {
        if(a.length()!=b.length()) return false;
        if(a.equals(b)) return hasRepeate(a);
        int i = 0,j;
        while (a.charAt(i) == b.charAt(i)){
            i++;
        }
        j=i+1;
        while (j<a.length()&&a.charAt(j)==b.charAt(j)){
            j++;
        }
        if(j==a.length()){
            return false;
        }
        if(a.charAt(i)!=b.charAt(j)||a.charAt(j)!=b.charAt(i)){
            return false;
        }
        j++;
        while (j<a.length()){
            if(a.charAt(j)!=b.charAt(j)){
                return false;
            }
            j++;
        }
        return true;
    }


    public int leastInterval(char[] tasks, int n) {
        int[] cnt  = new int[26];
        for (int i = 0;i<cnt.length;i++){
            cnt[i]=0;
        }
        for(int i = 0;i<tasks.length;i++){
            cnt[tasks[i]-'A'] +=1;
        }
        cnt = Arrays.stream(cnt).sorted().toArray();
        int m = 0;
        for (int i = 25;i>=0&&cnt[i]==cnt[25];i--,m++);
        return Math.max(tasks.length,(cnt[25]-1)*(n+1)+m);
    }

    @Test
    public  void aa() {
        int[] a = {9,8,4,1,3,2,6,5,7};
        List<Integer> integers = pancakeSort(a);
        for (Integer b: integers){
            System.out.println(b);
        }

    }

    private void reverse(int[] arr,int n,int[] ind){
        int temp;
        for(int i = 0,j=n-1;i<j;i++,j--){
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            ind[arr[i]] = i;
            ind[arr[j]] = j;
        }
    }
    public  List<Integer> pancakeSort(int[] arr) {
        int[] ind = new int[arr.length+1];
        List<Integer> ret = new ArrayList<>();
        for(int i = 0;i<arr.length;i++){
            ind[arr[i]] = i;
        }
        for(int i = arr.length;i>=1;i--){
            if(ind[i]+1!=1){
                ret.add(ind[i]+1);
                reverse(arr,ind[i]+1,ind);
            }
            if(i!=1){
                ret.add(i);
                reverse(arr,i,ind);
            }

        }
        return ret;
    }

    int data[] =  new int[10];
    int head=0;
    int tail=0;
    int cnt=0;
    // 入队
    void push(int n){
        if(full()){
            return;
        }
        data[tail]=n;
        tail++;
        cnt++;
        if(tail==data.length){
            tail=0;
        }
        return;
    };
    // 出队
    void pop(){
        if(empty()) return;
        head++;
        cnt--;
        if(tail==data.length){
            tail=0;
        }
        return;
    };
    // 是否为空
    boolean empty(){
        return cnt ==0;
    }
    // 是否已满
    boolean full(){
        return cnt == data.length;
    }
    // 队首元素
    int front(){
        if(empty()) return 0;
        return data[head];
    }
    // 队列大小
    int size(){
        return cnt;
    }
    // 输出队列所有元素
    void outPut(){
        for (int i=0,j=head;i<cnt;i++){
            System.out.println(data[j]);
            j+=1;
            if(j==data.length){
                j=0;
            }
        }
    }
    void clear(){
        head=tail=cnt=0;
    }

}
