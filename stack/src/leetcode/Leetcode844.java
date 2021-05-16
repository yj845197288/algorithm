package leetcode;

import java.util.Stack;

public class Leetcode844 {

    public void transform(String s, Stack<String> st){
        for (int i=0;i<s.length();i++){
            if('#'==s.charAt(i)&&!st.empty()){
                st.pop();
            }else if('#'!=s.charAt(i)){
                st.push(String.valueOf(s.charAt(i)));
            }
        }
    }
    public boolean backspaceCompare(String S, String T) {
        Stack<String> s = new Stack<>();
        Stack<String> t = new Stack<>();
        transform(S,s);
        transform(T,t);
        if(s.size()!=t.size()){
            return false;
        }
        while (!s.empty()){
            if(!s.pop().equals(t.pop())){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String a = "a#a";
        char b = '#';
        System.out.println(b==a.charAt(1));
    }
}
