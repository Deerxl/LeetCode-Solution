import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BackTrackingTag {
    /**
     * 求九宫格按键字母组合 -medium 获得一个数字，和前面的组合结果再次组合
     */
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return null;
        List<String> result = new ArrayList<>();
        HashMap<Integer, String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        //int[] nums = new int[]{2, 3, 4, 5, 6, 7, 8, 9};
        //String[] values = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        for (int i = 0; i < digits.length(); i++) {
            String value = map.get(Integer.valueOf(String.valueOf(digits.charAt(i))));
            int charLen = value.length();
            List<String> tmp = new ArrayList<>();
            for (int j = 0; j < charLen; j++) {
                if (i == 0) {
                    tmp.add(String.valueOf(value.charAt(j)));
                } else {
                    for (String str : result) {
                        String newStr = str.concat(String.valueOf(value.charAt(j)));
                        tmp.add(newStr);
                    }
                }
            }
            result = tmp;
        }
        return result;
    }

    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        if (n <= 0) return results;
        results.add("(");
        for (int i = 1; i < n * 2; i++) {
            List<String> tmp = new ArrayList<>();

            for (String result : results) {
                int left = 1, right = 0;
                for (int k = 1; k < result.length(); k++) {
                    if (result.charAt(k) == '(') left++;

                }
                if (right < left) {
                    String s = result.concat(String.valueOf(')'));
                    tmp.add(s);
                }
                if (left < n) {
                    String s = result.concat(String.valueOf('('));
                    tmp.add(s);
                }
            }
            results = tmp;
        }
        return results;
    }
}
