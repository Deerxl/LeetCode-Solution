import java.util.*;

public class JianZhiOfferTag {
    /**
     * 1. 求数组重复的元素 -easy
     */
    public int findRepeatNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return nums[i];
            else set.add(nums[i]);
        }
        return -1;
    }

    /**
     * 2. 求二维数组中是否包含某个值 -easy 可以从右上角开始比较
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int rlen = matrix.length, clen = matrix[0].length;
        for (int i = 0; i < rlen; i++) {
            for (int j = 0; j < clen; j++) {
                if (matrix[i][j] == target) return true;
            }
        }
        return false;

        /**
         * bug: 没考虑全
         * if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
         *         int row = 0, column = 0;
         *         int rlen = matrix.length, clen = matrix[0].length;
         *         while (row < rlen || column < clen) {
         *             if (matrix[row][column] == target) {    //相等
         *                 return true;
         *             } else if (matrix[row][column] < target     //target范围在这两个对角中
         *                     && row < rlen - 1 && column < clen - 1 && matrix[row + 1][column + 1] > target) {
         *                 int timer = 2;
         *                 while (timer > 0) {
         *                     if (timer == 1) {
         *                         column = 0; row++;
         *                     }
         *                     while (column < clen && matrix[row][column] <= target) {
         *                         if (matrix[row][column] == target) {
         *                             return true;
         *                         }
         *                         column++;
         *                     }
         *                     timer--;
         *                 }
         *                 return false;
         *             } else if (matrix[row][column] < target     //达到某一行或列的边界
         *                     && (row == rlen - 1 || column == clen - 1)) {
         *                 if (row == rlen - 1) {      //行边界
         *                     while (column < clen && matrix[row][column] <= target) {
         *                         if (matrix[row][column] == target) return true;
         *                         column++;
         *                     }
         *                     return false;
         *                 } else if (column == clen - 1) {    //列边界
         *                     column = 0;
         *                     row += 1;
         *                 }
         *             } else if (matrix[row][column] > target) {
         *                 return false;
         *             } else {
         *                 row++;
         *                 column++;
         *             }
         *         }
         *         return false;
         */

    }

    /**
     * 3. 替换字符 -easy 可以使用三倍长的char数组存储后转成string
     */
    public String replaceSpace(String s) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') buffer.append("%20");
            else buffer.append(String.valueOf(s.charAt(i)));
        }
        return buffer.toString();
    }

    /**
     * 4. 反转list -easy 用stack
     */
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int size = stack.size();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = stack.pop();
        }
        return result;
    }

    /**
     * 5. 根据前序遍历和中序遍历构造树 -medium 用栈存储左节点
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);
        int length = preorder.length;
        int j = 0;
        for (int i = 1; i < length; i++) {
            TreeNode node = stack.peek();
            int preVal = preorder[i];
            if (node.val != inorder[j]) {
                node.left = new TreeNode(preorder[i]);
                stack.push(node.left);
            } else {
                while (stack.size() > 0 && stack.peek().val == inorder[j]) {
                    j++;
                    node = stack.pop();
                }
                node.right = new TreeNode(preVal);
                stack.push(node.right);

            }
        }

        return root;
    }

    /**
     * 6. 用两个栈实现队列入队出队 -easy
     */
    class CQueue {
        Stack<Integer> s1;
        Stack<Integer> s2;

        public CQueue() {
            s1 = new Stack<>();
            s2 = new Stack<>();
        }

        public void appendTail(int value) {
            s1.push(value);
        }

        public int deleteHead() {
            if (!s2.empty()) {
                return s2.pop();
            } else if (s1.empty()) {
                return -1;
            } else {
                while (!s1.empty()) {
                    s2.push(s1.pop());
                }
                return s2.pop();
            }
        }
    }

    /**
     * 7. 斐波那契数列 -easy 用vector存储，每次结果都取模1000000007后再添加
     */
    public int fib(int n) {
        Vector<Integer> vec = new Vector<>();
        for (int i = 0; i <= n; i++) {
            if (i <= 1) vec.add(i);
            else {
                long res = vec.get(i - 1) + vec.get(i - 2);
                vec.add((int) (res % 1000000007));
            }
        }
        return vec.lastElement();
    }

    /**
     * 62. 求圆环中最后剩下的数字 -easy
     */
    public int lastRemaining(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) list.add(i);

        int index = (m - 1) % list.size();
        while (list.size() != 1) {
            list.remove(index);
            index = (index  + m - 1) % list.size();
        }
        return list.get(0);
    }

}
