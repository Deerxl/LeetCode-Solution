import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class TreeTag {
    /**
     * 中序遍历 -medium 递归
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return null;
        List<Integer> inOrderList = new ArrayList<>();
        inOrderList.add(root.val);
        if (root.left != null) inOrderList.addAll(inorderTraversal(root.left));
        if (root.right != null) inOrderList.addAll(inorderTraversal(root.right));
        return inOrderList;
    }

    /**
     * 中序遍历 -medium 线索二叉树
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> inOrderList = new ArrayList<>();
        TreeNode pre, cur = root;
        while (cur != null) {
            if (cur.left == null) {     //如果cur左子树为空，则将cur输出到list
                inOrderList.add(cur.val);
                cur = cur.right;
            } else {        //左子树不为空
                pre = cur.left;
                while (pre.right != null) {     //如果cur有左子树，那么将cur放到cur左子树的最右节点
                    pre = pre.right;
                }
                pre.right = cur;
                TreeNode temp = cur;
                cur = cur.left;
                temp.left = null;
            }

        }
        return inOrderList;
    }

    /**
     * 判断一棵树是不是二叉搜索树 -medium 中序遍历，栈
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        double limitVal = -Double.MAX_VALUE;
        while (!stack.empty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= limitVal)
                return false;
            limitVal = root.val;
            root = root.right;
        }
        return true;
    }

    /**
     * 找树所有的众数 -easy 中序遍历 求相同值
     */
    public int[] findMode(TreeNode root) {
        List<Integer> inList = inorder(root);
        List<Integer> result = new ArrayList<>();
        int maxCount = 0;
        for (int i = 0; i < inList.size(); i++) {
            int cur = inList.get(i), tmpCount = 1;
            while (i < inList.size() - 1 && inList.get(i + 1) == cur) {
                tmpCount++;
                i++;
            }
            if (maxCount == tmpCount) result.add(cur);
            else if (maxCount < tmpCount) {
                result.clear();
                result.add(cur);
                maxCount = tmpCount;
            }
        }

        int[] res = new int[result.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = result.get(i);
        }

        return res;
    }

    private List<Integer> inorder(TreeNode root) {
        List<Integer> inList = new ArrayList<>();
        if (root == null) return inList;
        if (root.left != null) inList.addAll(inorder(root.left));
        inList.add(root.val);
        if (root.right != null) inList.addAll(inorder(root.right));
        return inList;
    }

    /**
     * 锯齿状层次遍历树 -medium 用两个栈，分别存储两行
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();

        s1.push(root);
        while (!s1.empty() || !s2.empty()) {
            List<Integer> first = new ArrayList<>();
            while (!s1.empty()) {
                TreeNode cur = s1.pop();
                first.add(cur.val);

                if (cur.left != null) s2.push(cur.left);
                if (cur.right != null) s2.push(cur.right);
            }
            if (first.size() > 0) result.add(first);

            List<Integer> second = new ArrayList<>();
            while (!s2.empty()) {
                TreeNode cur = s2.pop();
                second.add(cur.val);

                if (cur.right != null) s1.push(cur.right);
                if (cur.left != null) s1.push(cur.left);
            }
            if (second.size() > 0) result.add(second);
        }

        return result;
    }

    /**
     * 前序遍历 -medium 递归
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        result.add(root.val);
        if (root.left != null) result.addAll(preorderTraversal(root.left));
        if (root.right != null) result.addAll(preorderTraversal(root.right));
        return result;
    }

    /**
     * 前序遍历 -medium 栈：出栈时出最后一个元素，入栈时先入右节点，再入左节点
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);
        //result.add(cur.val);

        while (!stack.empty()) {
            TreeNode cur = stack.lastElement();    //检索并移除最后一个元素
            result.add(cur.val);
            stack.removeElementAt(stack.size() - 1);

            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
        return result;
    }

    /**
     * 后序排列 -hard 栈：入栈时先入左节点，后入右节点，出栈时最后一个元素，将此插入到结果的最前面，或最后reverse
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode cur = stack.lastElement();
            result.add(cur.val);
            stack.removeElementAt(stack.size() - 1);

            if (cur.left != null) stack.add(cur.left);
            if (cur.right != null) stack.add(cur.right);
        }
        Collections.reverse(result);
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    /**
     * 求根到某一路径的和为sum的集和 -medium 深度优先搜索
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        helper(root, sum);

        return result;
    }

    private void helper(TreeNode root, int rest) {
        if (root == null) return;

        list.add(root.val);
        rest -= root.val;
        if (rest == 0 && root.left == null && root.right == null)
            result.add(new ArrayList(list));
        helper(root.left, rest);
        helper(root.right, rest);
        list.remove(list.size() - 1);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

}
