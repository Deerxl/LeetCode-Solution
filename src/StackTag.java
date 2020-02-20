import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackTag {

    /**
     * 求 三种括号是否对称 -easy
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') stack.push(c);
            else {
                if (stack.empty()) return false;
                char popc = stack.pop();
                if (!((popc == '{' && c == '}')
                        || (popc == '[' && c == ']')
                        || (popc == '(' && c == ')'))) return false;
            }
        }
        return stack.empty();
    }

    /**
     * 接雨水  -hard   栈：遇到上升的向前求面积
     */
    public int trap(int[] height) {
        int volumn = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.empty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.empty()) break;
                int distance = i - stack.peek() - 1;
                int minHeight = Math.min(height[i], height[stack.peek()]) - height[top];
                volumn += distance * minHeight;
            }
            stack.push(i);
        }
        return volumn;
    }


    /**
     * 简化路径     -medium 栈
     */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] splitPath = path.split("/");
        for (int i = 0; i < splitPath.length; i++) {

            switch (splitPath[i]) {
                case ".":
                case "":
                    continue;
                case "..":
                    if (!stack.empty())
                        stack.pop();
                    break;
                default:
                    stack.push(splitPath[i]);
                    break;
            }
        }
        if (stack.empty()) return "/";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < stack.size(); i++) {
            buffer.append("/");
            buffer.append(stack.get(i));
        }
        return buffer.toString();
    }

    /**
     * 求数组的最大长方体    -hard   栈 / 分治（最低的一长条面积 和左右的最大面积相比）
     */
    public int largestRectangleArea(int[] heights) {
        return singleSideArea(heights, 0, heights.length - 1);
    }

    private int singleSideArea(int[] heights, int start, int end) {
        if (start > end) return -1;
        int minIndex = start;
        for (int i = start; i <= end; i++) {
            if (heights[i] < heights[minIndex]) minIndex = i;
        }
        //int maxLengthArea = heights[minIndex] * (end - start + 1);
        //int leftSideArea = singleSideArea(heights, start, minIndex - 1);
        //int rightSideArea = singleSideArea(heights, minIndex + 1, end);
        //int maxTwoSides = Math.max(leftSideArea, rightSideArea);
        return Math.max(
                Math.max(singleSideArea(heights, start, minIndex - 1),
                        singleSideArea(heights, minIndex + 1, end)),
                heights[minIndex] * (end - start + 1));
    }

    /**
     * 求数组的最大长方体 -hard 栈：当高度下降时来依次计算前面的面积
     */
    public int largestRectangleArea2(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxArea = 0;
        int i = 0;
        for (; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[i] < heights[stack.peek()]) {
                maxArea = Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            maxArea = Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1));
        }
        return maxArea;
    }

    /**
     * 求二维二进制数组的“1”构成的最大长方形面积   -hard 每行向上累加，以行为单位求最大面积
     */
    public int maximalRectangle(String[][] matrix) {
        int[][] matrix2 = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            int[] newLine = new int[matrix[i].length];
            for (int j = 0; j < matrix[i].length; j++) {
                int temp = Integer.parseInt(matrix[i][j]);
                if (temp == 1)
                    newLine[j] = (i == 0 ? temp : temp + matrix2[i - 1][j]);
            }
            matrix2[i] = newLine;
        }
        int maxArea = 0;
        for (int i = 0; i < matrix.length; i++) {
            maxArea = Math.max(maxArea, maxSingleLineArea(matrix2[i]));
        }
        return maxArea;
    }

    private int maxSingleLineArea(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] > heights[i]) {
                maxArea = Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }
        return maxArea;
    }

    /**
     * 逆波兰表达式求值 -medium 栈存储数值，遇到符号 出栈2个 求值后入栈
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int num1, num2;
        for (int i = 0; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "+":
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num1 + num2);
                    break;
                case "-":
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num2 - num1);
                    break;
                case "*":
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num2 * num1);
                    break;
                case "/":
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num2 / num1);
                    break;
                default:
                    stack.push(Integer.parseInt(tokens[i]));
                    break;
            }
        }
        return stack.pop();
    }

    /**
     * 计算简单的表达式 -hard   用栈来存储，更简单的方法是用一个栈存储，大于10的数字可以用 x10再 +来解决，减少冗余，更加快
     */
    public int calculate(String s) {
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        int num1, num2;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                    ops.push('(');
                    break;
                case '+':
                case '-':
                    if (!ops.empty() && (ops.peek() == '-' || ops.peek() == '+')) {
                        num1 = nums.pop();
                        num2 = nums.pop();
                        if (ops.pop() == '+') nums.push(num2 + num1);
                        else nums.push(num2 - num1);
                    }
                    ops.push(c);
                    break;
                case ')':
                    while (ops.peek() != '(') {
                        num1 = nums.pop();
                        num2 = nums.pop();
                        if (ops.pop() == '+') nums.push(num2 + num1);
                        else nums.push(num2 - num1);
                    }
                    ops.pop();  // pop '('
                    break;
                case ' ':
                    break;
                default:
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(String.valueOf(c));
                    while (i < s.length() - 1 && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') {
                        buffer.append(String.valueOf(s.charAt(++i)));
                    }
                    nums.push(Integer.parseInt(buffer.toString()));
                    break;
            }
        }
        if (!ops.empty()) {
            num1 = nums.pop();
            num2 = nums.pop();
            if (ops.pop() == '+') nums.push(num2 + num1);
            else nums.push(num2 - num1);
        }
        return nums.pop();
    }
}

/**
 * 最小栈，-easy 用两个栈，一个栈正常出入，一个栈栈顶存储最小值。
 */
class MinStack {
    private Stack<Integer> s1;
    private Stack<Integer> s2;
    /** initialize your data structure here. */
    public MinStack() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void push(int x) {
        s1.push(x);
        if (s2.empty() || x <= s2.peek()) {
            s2.push(x);
        }
    }

    public void pop() {
        if (!s1.empty() && s1.peek().equals(s2.peek())) {
            s2.pop();
            s1.pop();
        } else if (!s1.empty()) {
            s1.pop();
        }
    }

    public int top() {
        if (!s1.empty()) {
            return s1.peek();
        } else {
            throw new RuntimeException("error: stack is empty.");
        }
    }

    public int getMin() {
        if (!s2.empty()) {
            return s2.peek();
        } else {
            throw new RuntimeException("error: stack is empty.");
        }
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */


/**
 * 二叉搜索树迭代器 -medium 求 next和 hasNext，中序遍历，栈来存储
 */
class BSTIterator {
    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        leftMostNode(root);
    }

    private void leftMostNode(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode root = stack.pop();
        if (root.right != null) {
            leftMostNode(root.right);
        }
        return root.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.empty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */


/**
 * 用栈实现队列 -easy 用两个栈来存储，push用 s1，pop用s2，如果 s2为空，将 s1倒入 s2中
 */
class MyQueue {
    Stack<Integer> s1;
    Stack<Integer> s2;

    /** Initialize your data structure here. */
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (s2.empty()) {
            while (!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (s2.empty()) {
            while (!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.empty() && s2.empty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */


/**
 * 用队列实现栈 -easy 两个栈来完成，注意 s2每次操作后清空
 */
class MyStack {
    Queue<Integer> q1;
    Queue<Integer> q2;

    /** Initialize your data structure here. */
    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q1.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        while (q1.size() != 1) {
            q2.add(q1.remove());
        }
        int res = q1.remove();
        q1 = q2;
        Queue<Integer> temp = new LinkedList<>();
        q2 = temp;
        return res;
    }

    /** Get the top element. */
    public int top() {
        while (q1.size() != 1) {
            q2.add(q1.remove());
        }
        int res = q1.remove();
        q2.add(res);
        q1 = q2;
        Queue<Integer> temp = new LinkedList<>();
        q2 = temp;
        return res;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.size() == 0;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */