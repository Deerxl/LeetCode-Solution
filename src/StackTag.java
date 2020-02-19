import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Stack;

public class StackTag {

    /**
     * 求 三种括号是否对称 -easy
     * @param s
     * @return
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
     * @param height
     * @return
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
     * @param path
     * @return
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
     * @param heights
     * @return
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
     * @param heights
     * @return
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
     * @param matrix
     * @return
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


}
