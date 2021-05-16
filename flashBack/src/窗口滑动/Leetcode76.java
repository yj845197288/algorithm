package 窗口滑动;

import java.util.HashMap;

public class Leetcode76 {

    public static void main(String[] args) {
        String s = minWindow("a", "aa");
        System.out.println(s);
    }

    public static String minWindow(String s, String t) {
        HashMap<String, Integer> need = new HashMap<>();
        HashMap<String, Integer> window = new HashMap<>();
        char[] chars = t.toCharArray();
        for (char c : chars) {
            need.put(String.valueOf(c), need.get(String.valueOf(c)) == null ? 1 : need.get(String.valueOf(c)) + 1);
            window.put(String.valueOf(c), 0);
        }
        int left = 0, right = 0;
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE;
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
            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
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
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

}
