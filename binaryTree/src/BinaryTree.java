import java.util.*;

public class BinaryTree {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return targetSum == root.val;
        if (root.left != null && hasPathSum(root.left, targetSum - root.val)) return true;
        if (root.right != null && hasPathSum(root.right, targetSum - root.val)) return true;
        return false;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        int pos = 0;
        while (inorder[pos] != preorder[0]) pos++;
        List<Integer> leftPre = new LinkedList<>();
        List<Integer> leftIn = new LinkedList<>();
        List<Integer> rightPre = new LinkedList<>();
        List<Integer> rightIn = new LinkedList<>();
        for (int i = 0; i < pos; i++) {
            leftPre.add(preorder[i + 1]);
            leftIn.add(inorder[i]);
        }
        for (int i = pos + 1; i < preorder.length; i++) {
            rightPre.add(preorder[i]);
            rightIn.add(inorder[i]);
        }
        TreeNode treeNode = new TreeNode(preorder[0]);
        treeNode.left = buildTree(leftPre.stream().mapToInt(Integer::valueOf).toArray(), leftIn.stream().mapToInt(Integer::valueOf).toArray());
        treeNode.right = buildTree(rightPre.stream().mapToInt(Integer::valueOf).toArray(), rightIn.stream().mapToInt(Integer::valueOf).toArray());
        return treeNode;
    }

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return countNodes(root.left) + countNodes(root.right) + 1;

    }

    public int kthLargest(TreeNode root, int k) {
        int count = countNodes(root.right);
        if(k<=count) return kthLargest(root.right,k);
        if(k==count+1) return root.val;
        return kthLargest(root.left,k-count-1);
    }

    /**
     * 剑指offer 26
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(B==null) return false;
        if(A==null) return false;
        if(isMatch(A,B)) return true;
        return isSubStructure(A.left,B)||isSubStructure(A.right,B);
    }
    public boolean isMatch(TreeNode A,TreeNode B){
        if(B==null) return true;
        if(A==null) return false;
        if(A.val!=B.val) return false;
        return (isMatch(A.left,B.left)&&isMatch(A.right,B.right));
    }

    /**
     * 662
     */
    public int widthOfBinaryTree(TreeNode root) {
        int ans=0;
        Queue<PNI> q = new LinkedList<>();
        q.add(new PNI(root,0));
        while (!q.isEmpty()){
            int cnt = q.size();
            int l = q.peek().t;
            int r = q.peek().t;
            for (int i=0;i<cnt;i++){
                TreeNode treeNode = q.peek().treeNode;
                int idx= q.peek().t;
                r=idx;
                if(treeNode.left!=null) q.add(new PNI(treeNode.left,idx*2));
                if(treeNode.right!=null) q.add(new PNI(treeNode.right,idx*2+1));
                q.poll();
            }
            ans = Math.max(ans,r-l+1);
        }
        return ans;
    }

    public int minCameraCover(TreeNode root) {
        int[][] dp = new int[2][2];
        getDP(root,dp);
        return Math.min(dp[0][1],dp[0][0]);
    }

    private void getDP(TreeNode root, int[][] dp) {
        if(root==null){
            dp[0][0]=0;
            dp[0][1]=10000;
            dp[1][0]=0;
            dp[1][1]=10000;
            return;
        }
        if(root.left==null&&root.right==null){
            dp[0][0]=10000;
            dp[0][1]=1;
            dp[1][0]=0;
            dp[1][1]=1;
            return;
        }
        int[][] l = new int[2][2];
        int[][] r = new int[2][2];
        getDP(root.left,l);
        getDP(root.right,r);
        dp[0][0] = Math.min(Math.min(l[0][1]+r[0][0],l[0][0]+r[0][1]),l[0][1]+r[0][1]);
        dp[1][0] = Math.min(dp[0][0],l[0][0]+r[0][0]);
        dp[0][1] = Math.min(Math.min(l[1][0]+r[1][0],l[1][1]+r[1][1]),Math.min(l[1][0]+r[1][1],l[1][1]+r[1][0]))+1;
        dp[1][1] = dp[0][1];

    }
}

class PNI{
    TreeNode treeNode;
    int t;
    PNI(){}
    PNI(TreeNode treeNode,int t){
        this.treeNode=treeNode;
        this.t=t;
    }
}
