package 动态规划.编辑距离;

import org.junit.Test;

import java.util.Map;

public class EditDistance {

    /**
     * 原始算法
     * @param i a的下标
     * @param j b的下标
     * @param a 字符串a
     * @param b 字符串b
     * @return
     */
    public  int dp(int i, int j, String a, String b) {
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;
        if (a.charAt(i) == b.charAt(j)) {
            return dp(i - 1, j - 1, a, b);
        }else {
            return Math.min(Math.min(dp(i,j-1,a,b)+1,dp(i-1,j,a,b))+1,dp(i-1,j-1,a,b)+1);
        }
    }
    @Test
    public void dpTest(){
        String a = "dad";
        String b = "sadaa";
        int dp = dp(a.length() - 1, b.length() - 1, a, b);
        System.out.println(dp);
    }


    /**
     * 带有备忘录的算法
     * @param j
     * @param a
     * @param b
     * @param memo
     * @return
     */
    public static int memodp(int i, int j, String a, String b, Map<String,Integer>  memo) {
        String key = i+","+j;
        if(memo.get(key)!=null) return memo.get(key);
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;
        if (a.charAt(i) == b.charAt(j)) {
            memo.put(key,memodp(i - 1, j - 1, a, b,memo));
        }else {
            int min = Math.min(Math.min(memodp(i, j - 1, a, b, memo) + 1, memodp(i - 1, j, a, b, memo)) + 1, memodp(i - 1, j - 1, a, b, memo) + 1);
            memo.put(key,min);
        }
        return memo.get(key);
    }

}
