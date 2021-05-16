package 窗口滑动;

import java.util.HashMap;

public class Leetcode567 {

    public static void main(String[] args) {
    }

    public boolean checkInclusion(String s1, String s2) {
        HashMap<String, Integer> need = new HashMap<>();
        HashMap<String, Integer> window = new HashMap<>();
        char[] chars =s1.toCharArray();
        for (char c : chars) {
            need.put(String.valueOf(c), need.get(String.valueOf(c)) == null ? 1 : need.get(String.valueOf(c)) + 1);
            window.put(String.valueOf(c), 0);
        }
        int left = 0, right = 0;
        int valid = 0;
        while (right < s2.length()) {
            char c = s2.charAt(right);
            String str1 = String.valueOf(c);
            right++;
            if (need.get(str1) != null) {
                window.put(str1, window.get(str1) + 1);
                if (need.get(str1).equals(window.get(str1))) {
                    valid++;
                }
            }
            while (right-left>=s1.length()) {
                if (valid==need.size()) {
                    return true;
                }
                char d = s2.charAt(left);
                String str2 = String.valueOf(d);
                left++;
                if (need.get(str2) != null) {
                    if (need.get(str2).equals(window.get(str2))) {
                        valid--;
                    }
                    window.put(str2, window.get(str2) - 1);
                }
            }
        }
        return false;
    }
}
