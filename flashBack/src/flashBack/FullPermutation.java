package flashBack;

import java.util.LinkedList;

public class FullPermutation {

    public static void main(String[] args) {
        LinkedList<LinkedList<Integer>> res = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        int[] nums={1,2};
        backtrack(nums,track,res);
        System.out.println(res);
    }

    public static void backtrack(int nums[], LinkedList<Integer> track, LinkedList<LinkedList<Integer>> res) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) continue;
            track.add(nums[i]);
            backtrack(nums, track, res);
            track.removeLast();
        }
    }
}
