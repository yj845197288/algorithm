package leetcode;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Leetcode946 {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> s = new Stack<>();
        for (int i = 0, j = 0; i < popped.length; i++) {
            while (j < pushed.length && (s.empty() || s.peek() != popped[i])) {
                s.push(pushed[j]);
                j++;
            }
            if (s.pop() != popped[i]) {
                return false;
            }
        }
        return true;
    }


    public boolean isValid(String s) {
        Stack<String> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                case '[':
                case '{':
                    st.push(String.valueOf(s.charAt(i)));
                    break;
                case ')':
                    if (st.empty() || !st.peek().equals("(")) return false;
                    st.pop();
                    break;
                case ']':
                    if (st.empty() || !st.peek().equals("[")) return false;
                    st.pop();
                    break;
                case '}':
                    if (st.empty() || !st.peek().equals("{")) return false;
                    st.pop();
                    break;
            }
        }
        return st.empty();
    }

    public String removeOuterParentheses(String S) {
        String ret = "";
        for (int i = 0, pre = 0, cnt = 0; i < S.length(); i++) {
            if (S.charAt(i) == '(') {
                cnt++;
            } else {
                cnt--;
            }
            if (cnt != 0) continue;
            ret += S.substring(pre + 1, i);
            pre = i + 1;
        }
        return ret;
    }

    @Test
    public void main() {
        String s = "(()())(())";
        String s1 = removeOuterParentheses(s);
    }


    /**
     * Leetcode 1249 移除无效的括号
     */
    public String minRemoveToMakeValid(String s) {
        Set<Integer> remove = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    remove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        while (!stack.isEmpty()) {
            remove.add(stack.pop());
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (!remove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * leetcode 145 后序遍历二叉树
     */
    public List<Integer> postorderTraversal(TreeNode root) {

        // 0  压栈左子树 1 压栈右子树 2 弹出栈顶元素
        ArrayList<Integer> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        // 元素
        Stack<TreeNode> s1 = new Stack<>();
        // 遍历的状态
        Stack<Integer> s2 = new Stack<>();
        s1.push(root);
        s2.push(0);
        while (!s1.empty()) {
            int status = s2.pop();
            switch (status) {
                case 0: {
                    s2.push(1);
                    if (s1.peek().left != null) {
                        s1.push(s1.peek().left);
                        s2.push(0);
                    }
                }
                break;
                case 1: {
                    s2.push(2);
                    if (s1.peek().right != null) {
                        s1.push(s1.peek().right);
                        s2.push(0);
                    }
                }
                break;
                case 2: {
                    ret.add(s1.peek().val);
                    s1.pop();
                }
                break;
            }
        }
        return ret;
    }

    /**
     * leetcode 331 验证二叉树的前序序列化
     */
    @Test
    public void isValidSerialization() {
        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        String[] split = preorder.split(",");
        Stack<String> stack = new Stack<>();
        Stack<String> s2 = new Stack<>();
        for (int i = 0; i < split.length; i++) {
            stack.push(split[i]);
            while (stack.size() >= 3) {
                if (stack.peek().equals("#")) {
                    s2.push(stack.pop());
                    if (stack.peek().equals("#")) {
                        stack.pop();
                        stack.pop();
                        stack.push("#");
                    } else {
                        stack.push(s2.pop());
                    }
                }
            }
            if (stack.size() == 2) {
                String a = stack.pop();
                String b = stack.pop();
                if (a.equals("#") && b.equals("#")) {
                    System.out.println(false);
                }
                stack.push(b);
                stack.push(a);
            }
        }
        System.out.println(stack.size() == 1 && stack.pop().equals("#"));
    }

    /**
     * leetcode 227 基本计算器
     */
    public int calculate(String s) {
        Stack<Integer> num = new Stack<>();
        Stack<String> ops = new Stack<>();
        s += "@";
        for (int i = 0, n = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') continue;
            if (level(String.valueOf(s.charAt(i))) == 0) {
                n = n * 10 + (s.charAt(i) - '0');
                continue;
            }
            num.push(n);
            n = 0;
            while (!ops.empty() && level(String.valueOf(s.charAt(i))) <= level(ops.peek())) {
                int b = num.pop();
                int a = num.pop();
                num.push(calc(a, ops.pop(), b));
            }
            ops.push(String.valueOf(s.charAt(i)));
        }
        return num.pop();

    }

    private int level(String c) {
        switch (c) {
            case "@":
                return -1;
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
        }
        return 0;
    }

    private int calc(int a, String op, int b) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
        }
        return 0;
    }

    @Test
    public void longestWPI() {
        int[] hours = {9, 7, 7, 8, 6, 11, 12};
        HashMap<Integer, Integer> idx = new HashMap<>();
        HashMap<Integer, Integer> f = new HashMap<>();
        idx.put(0, -1);
        f.put(0, 0);
        int cnt = 0, ans = 0;
        for (int i = 0; i < hours.length; i++) {
            if (hours[i] > 8) {
                cnt++;
            } else {
                cnt--;
            }
            if (idx.get(cnt) == null) {
                idx.put(cnt, i);
                if (idx.get(cnt - 1) == null) {
                    f.put(cnt, 0);
                } else {
                    f.put(cnt, f.get(cnt - 1) + (i - idx.get(cnt - 1)));
                }
            }
            if (idx.get(cnt - 1) == null) {
                continue;
            }
            ans = Math.max(ans, i - idx.get(cnt - 1) + f.get(cnt - 1));
        }
        System.out.println(ans);
    }

    @Test
    public void main1() {
        int[] ret = new int[100];
        ret[2] += 100;

        for (int i = 0; i < ret.length; i++) {
            System.out.println(ret[i]);
        }
        System.out.println(ret[2]);
    }



}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
