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
}
