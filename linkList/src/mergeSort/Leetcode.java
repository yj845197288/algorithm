package mergeSort;

public class Leetcode {
    public ListNode sortList(ListNode head) {
        ListNode p,q;
        p=head;
        int size=1;
        ListNode temp=head;
        while (temp.next!=null){
            size++;
            temp=temp.next;
        }
        temp=head;
        size/=2;
        return null;

    }

    // leetcode 327 区间和的个数
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length+1];
        temp = new long[nums.length+1];
        sum[0]=0;
        for (int i=0;i<nums.length;i++){
            sum[i+1]=sum[i]+nums[i];
        }
        return mergeSort(sum,0,sum.length-1,lower,upper);
    }
    int mergeSort(long[] sum,int l,int r,int lower,int upper){
        if(l>=r) return 0;
        int mid=(l+r)>>1,ans =0;
        ans+=mergeSort(sum,l,mid,lower,upper);
        ans+=mergeSort(sum,mid+1,r,lower,upper);
        int i=l;
        int p=mid+1;
        int q=mid+1;
        while (i<=mid){
            while (p<=r&&sum[p]-sum[i]<lower) p++;
            while (q<=r&&sum[q]-sum[i]<=upper) q++;
            ans+=(q-p);
            i++;
        }
        int k=l,p1=l,p2=mid+1;
        while (p1<=mid||p2<=r){
            if(p2>r||(p1<=mid&&sum[p1]<=sum[p2])){
                temp[k++]=sum[p1++];
            }else {
                temp[k++]=sum[p2++];
            }
        }
        for (int j=l;j<=r;j++){
            sum[j]=temp[j];
        }
        return ans;
    }
    long[] temp;

}

