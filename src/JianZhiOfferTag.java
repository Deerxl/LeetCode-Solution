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
     * 8. 青蛙跳台阶 -easy 递归
     */
    public int numWays(int n) {
        if (n == 0 || n == 1) return 1;
        int fib1 = 1;
        int fib2 = 1;
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = (fib1 + fib2) % 1000000007;
            fib1 = fib2;
            fib2 = res;
        }
        return res;
    }

    /**
     * 9. 旋转数组中的最小值 -medium 二分法
     */
    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) return -1;
        int length = numbers.length;
        int low = 0, high = length - 1, mid;
        while (low < high) {
            mid = (low + high) / 2;
            if (numbers[mid] > numbers[high]) {
                low = mid + 1;
            } else if (numbers[mid] == numbers[high]){
                high -= 1;
            } else {
                high = mid;
            }
        }
        return numbers[low];
    }

    /**
     * 10. 求矩阵中字符串的路径 -medium 深度优先搜索
     */
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        int index = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(dfs(board, words, i, j, index)) return true;
            }
        }
        return false;
    }

    boolean dfs(char[][] board, char[] words, int i, int j, int index) {
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[i].length - 1) return false;

        char temp = board[i][j];
        if (temp != words[index]) return false;
        if (index == words.length - 1) return true;

        board[i][j] = '/';
        boolean find = dfs(board, words, i + 1, j, index + 1)
                    || dfs(board, words, i, j + 1, index + 1)
                    || dfs(board, words, i - 1, j, index + 1)
                    || dfs(board, words, i, j - 1, index + 1);
        board[i][j] = temp;
        return find;
    }

    /**
     * 11. 求二维坐标各位数和小于 k 的格子数 -medium 广度优先搜索
     */
    public int movingCount(int m, int n, int k) {
        int count = 0;
        boolean[][] flag = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        flag[0][0] = true;

        while (queue.size() > 0) {
            int[] cur = queue.poll();
            count++;
            int i = cur[0], j = cur[1];
            if (i < m - 1 && !flag[i + 1][j] && (countSum(i + 1) + countSum(j)) <= k) {
                queue.add(new int[]{i + 1, j});
                flag[i + 1][j] = true;
            }
            if (i > 0 && !flag[i - 1][j] && (countSum(i - 1) + countSum(j)) <= k) {
                queue.add(new int[]{i - 1, j});
                flag[i - 1][j] = true;
            }
            if (j < n - 1 && !flag[i][j + 1] && (countSum(i) + countSum(j + 1)) <= k) {
                queue.add(new int[]{i, j + 1});
                flag[i][j + 1] = true;
            }
            if (j > 0 && !flag[i][j - 1] && (countSum(i) + countSum(j - 1)) <= k) {
                queue.add(new int[]{i, j - 1});
                flag[i][j - 1] = true;
            }
        }
        return count;
    }

    private int countSum(int x) {
        int sum = 0;
        do {
            sum += x % 10;
            x /= 10;
        } while (x > 0);
        return sum;
    }

    /**
     * 12. 剪绳子 求最大乘积 -medium 绳子越平均越容易
     */
    public int cuttingRope(int n) {
        int maxArea = 0;

        for (int i = 2; i <= n / 2 + 1; i++) {    // i: 切成 i 段
            int average = n / i;
            int complement = n % i;
            int curArea = 1;

            for (int j = 0; j < complement; j++) {
                curArea *= (average + 1);
            }
            for (int j = 0; j < i - complement; j++) {
                curArea *= average;
            }

            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

    /**
     * 13. 剪绳子2 求最大乘积 -medium
     * n > 4 时，每段长为 3 乘积最大，每次运算后取模 1000000007
     */
    public int cuttingRope2(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        long maxMul = 1;
        long mod = 1000000007;
        while (n > 4) {
            maxMul *= 3;
            maxMul %= mod;
            n -= 3;
        }

        return (int) (maxMul * n % mod);
    }

    /**
     * 14. 求整数 n 对应的二进制中 1 的个数 -easy 位运算
     */
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += n & 0x1;
            n = n >> 1;
        }
        return count;
    }

    /**
     * 15. 输出 n 位以内表示的所有整数 -easy
     */
    public int[] printNumbers(int n) {
        int count = ((int)(Math.pow(10, n)) - 1);
        int[] res = new int[count];
        for (int i = 0; i < count; i++) {
            res[i] = i + 1;
        }
        return res;
    }

    /**
     * 16. 删除节点 -easy 注意考虑各种条件
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return head;
        if (head.val == val) return head.next;

        ListNode node = head;
        while (node.next != null && node.next.val != val) node = node.next;
        if (node.next != null) node.next = node.next.next;

        return head;
    }

    /**
     * 17. 求幂 -medium 注意特殊值，可以用递归，每次求一半的幂值
     */
    public double myPow(double x, int n) {
        if (n == 0 || x == 1) return 1;
        if (x == -1) return n % 2 == 0 ? 1 : -1;
        if (x == 0 || n == 0x80000000) return 0;

        int absn = n;
        if (n < 0) {
            x = 1 / x;
            absn = -absn;
        }
        double base = x;
        for (int i = 1; i < absn; i++) {
            x *= base;
        }
        return x;
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
