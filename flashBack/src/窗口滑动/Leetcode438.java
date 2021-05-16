package 窗口滑动;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Leetcode438 {

    public List<Integer> findAnagrams(String s, String p) {
        HashMap<String, Integer> need = new HashMap<>();
        HashMap<String, Integer> window = new HashMap<>();
        char[] chars = p.toCharArray();
        for (char c : chars) {
            need.put(String.valueOf(c), need.get(String.valueOf(c)) == null ? 1 : need.get(String.valueOf(c)) + 1);
            window.put(String.valueOf(c), 0);
        }
        int left = 0, right = 0;
        int valid = 0;
        List<Integer> res = new ArrayList<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            String s1 = String.valueOf(c);
            right++;
            if (need.get(s1) != null) {
                window.put(s1, window.get(s1) + 1);
                if (need.get(s1).equals(window.get(s1))) {
                    valid++;
                }
            }
            while (right-left>=p.length()) {
                if(valid==need.size()){
                    res.add(left);
                }
                char d = s.charAt(left);
                String s2 = String.valueOf(d);
                left++;
                if (need.get(s2) != null) {
                    if (need.get(s2).equals(window.get(s2))) {
                        valid--;
                    }
                    window.put(s2, window.get(s2) - 1);
                }
            }
        }
        return res;
    }
}
