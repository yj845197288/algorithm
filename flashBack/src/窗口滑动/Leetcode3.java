package 窗口滑动;

import java.util.HashMap;

public class Leetcode3 {

    public int lengthOfLongestSubstring(String s) {
        HashMap<String, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            String s1 = String.valueOf(c);
            right++;
            window.put(s1, window.get(s1)==null?1:window.get(s1) + 1);
            while (window.get(s1)>1) {
                char d = s.charAt(left);
                String s2 = String.valueOf(d);
                left++;
                window.put(s2, window.get(s2) - 1);
            }
            res=Math.max(res,right-left);
        }
        return res;
    }

}
