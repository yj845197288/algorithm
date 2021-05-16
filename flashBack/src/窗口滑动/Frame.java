package 窗口滑动;

import java.util.HashMap;

public class Frame {
    public void slidingWindow(String s,String t){
        HashMap<String,Integer> need = new HashMap<>();
        HashMap<String,Integer> window = new HashMap<>();
        char[] chars = t.toCharArray();
        for (char c:chars){
            need.put(String.valueOf(c),1);
        }
        int left = 0,right=0;
        int valid=0;
        while (right<s.length()){
            // c 是移入窗口的数据
            char c = s.charAt(right);
            // 右移窗口
            right++;
            // 进行窗口数据的一系列更新
            //...
            while (valid==need.size()){
                char d = s.charAt(left);
                left++;
                // 进行窗口数据的一系列更新
                //...
            }

        }

    }
}
