import java.util.Stack;

public class DynamicProgrammingTag {
    /*public boolean isMatch(String s, String p) {
        if (s.length() == 0 || p.length() == 0) return false;
        char starRec=' ', schar, pchar;
        boolean starInUse = false;
        for (int i = 0, j = 0; i < s.length() && j < p.length();) {
            schar = s.charAt(i);
            pchar = p.charAt(j);
            //经测试，'*'前必跟'.'或字母
            if (pchar == '*') {
                char back = p.charAt(j - 1);
                if(isCharacter(back)) {
                    starRec = p.charAt(j - 1);
                } else if (back == '.') {
                    starRec = '*';
                }
                starInUse = true;
            }

            if (schar == pchar || pchar == '.'
                    || (pchar == '*' && (starRec == schar || starRec == '*'))) {
                i++;
                j++;
                starInUse = false;
            } else if (){
                //j++;
            }
            //break;
        }
        return false;
    }*/

    boolean isCharacter(char c) {
        return c >= 'a' && c <= 'z';
    }

    /**
     * 求有效括号对的最长长度 -hard 栈 动态规划 比较容易
     */
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        int[] dp = new int[s.length()];

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (s.charAt(i - 1) == '(') {       // ...()
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {        // ...))
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
        /**栈

        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') stack.push(i);
            else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
         */

        /** 比较麻烦。。
        if (s == null || s.length() <= 1) return 0;
        int sLength = s.length();
        int maxLen = 0, tmpLen = 0;
        int[] flags = new int[sLength];
        for (int i = 0; i < sLength; i++) flags[i] = 0;   //初始化全为 0
        int i = 0, j = 1;
        while (i < sLength - 1 && j < sLength) {
            if (s.charAt(i) == '(' && s.charAt(j) == ')') {
                flags[i] = 1;
                flags[j] = 1;
                if (i - 1 >= 0 && j + 1 < sLength && s.charAt(i - 1) == '(' && s.charAt(j + 1) == ')') {
                    i--;
                    j++;
                } else if (i - 1 >= 0 && j + 1 < sLength && s.charAt(j + 1) == ')' && flags[i - 1] == 1) {
                    i--;
                    while (i >= 0 && flags[i] == 1) {
                        i--;
                    }
                    if (i < 0 || ! (flags[i] == 0 && s.charAt(i) == '(' )) {
                        i = j + 1;
                        j = i + 1;
                    } else {
                        j++;
                    }
                }
                else {
                    i = j + 1;
                    j = i + 1;
                }
            } else {
                i++;
                j++;
            }
        }

        for (int k = 0; k < sLength; k++) {
            if (flags[k] == 1) tmpLen += 1;
            else {
                maxLen = Math.max(tmpLen, maxLen);
                tmpLen = 0;
            }
        }
        maxLen = Math.max(tmpLen, maxLen);

        return maxLen;

         */
    }

    /**
     * 求二维二进制数组的“1”构成的最大长方形面积 -hard 动态规划，先求每一行的最大宽度，再向上求最大面积。
     */
    public int maximalRectangle(char[][] matrix) {
        int maxArea = 0;
        int[][] intMatrix = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            int[] newLine = new int[matrix[i].length];
            for (int j = 0; j < matrix[i].length; j++) {
                int temp = Integer.parseInt(String.valueOf(matrix[i][j]));
                if (temp == 1) newLine[j] = (j == 0 ? temp : temp + newLine[j - 1]);
            }
            intMatrix[i] = newLine;
        }

        for (int i = 0; i < intMatrix.length; i++) {
            for (int j = 0; j < intMatrix[i].length; j++) {
                if (intMatrix[i][j] != 0) {
                    int tempi = i;
                    int minLen = Integer.MAX_VALUE, maxHeight = 0, tempMaxArea = 0;
                    do {
                        minLen = Math.min(minLen, intMatrix[tempi][j]);
                        maxHeight++;
                        tempMaxArea = Math.max(tempMaxArea, minLen * maxHeight);
                    } while (--tempi >= 0 && intMatrix[tempi][j] != 0);
                    maxArea = Math.max(maxArea, tempMaxArea);
                }

            }
        }
        return maxArea;

    }
}
