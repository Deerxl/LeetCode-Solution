import java.util.*;

class StringTag {
    /**
     * 求无重复子串的最长长度 -medium 滑动窗口 hash map
     */
    int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) return s.length();
        int l = 0, r = 1;
        int maxLength = 1;

        Hashtable<Character, Integer> table = new Hashtable<>();
        table.put(s.charAt(l), l);
        while (l < s.length() - 1 && r < s.length()) {

            //r = l + 1;
            while (r < s.length() && !table.containsKey(s.charAt(r))) {
                table.put(s.charAt(r), r);
                r++;
            }

            maxLength = Math.max(r - l, maxLength);

            if (r != s.length()) {
                int tmp = table.get(s.charAt(r));
                for (int i = l; i <= tmp; i++) {
                    table.remove(s.charAt(i));
                }
                l = 1 + tmp;
                if (s.length() - l + 1 < maxLength) {
                    break;
                }
                //table.remove(s.charAt(r));
                table.put(s.charAt(r), r);
                r++;
            }
        }
        return maxLength;
    }

    /**
     * 找最长的回文子串 -medium 动态规划  中心扩展
     */
    public String longestPalindrome(String s) {
        if (s.length() == 0) return "";
        int start, end;
        String subString = "";
        for (int i = 0; i < s.length() - 1 && s.length() > subString.length(); i++) {
            boolean b = s.charAt(i) == s.charAt(i + 1);     //判断是否是相邻的相等
            if (b || (s.length() - i > 2 && s.charAt(i) == s.charAt(i + 2))) {    //是隔一个相等
                start = i;
                end = b ? i + 1 : i + 2;
                do {
                    end += 1;
                }while (b && end < s.length() && s.charAt(end) == s.charAt(end - 1));
                end -= 1;
                do {
                    start -= 1;
                    end += 1;
                } while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end));
                start += 1;
                end -= 1;
                subString = end - start + 1 > subString.length() ? s.substring(start, end + 1) : subString;
            }
        }
        if (subString.equals("")) return s.substring(0,1);
        return subString;
    }


    /**
     * 求字符串z字形排列后的顺序 -medium
     */
    public String convert(String s, int numRows) {
        if (numRows <= 1 || numRows >= s.length()) return s;
        int cycle = numRows * 2 - 2;
        StringBuffer str = new StringBuffer();

        for (int i = 1; i <= numRows; i++) {
            /*int j = 0;
            int second = (2 * numRows - i) % cycle;
            int[] index;
            if (i == second) {
                index = new int[]{i - 1};
            } else {
                if (second == 0) second = cycle;
                index = new int[]{i - 1, second - 1};
            }
            while (j + index[0] < s.length()) {
                str.append(s.charAt(index[0] + j));
                if (i != 1 && i != numRows && j + index[1] < s.length()) {
                    str.append(s.charAt(index[1] + j));
                }
                j += cycle;
            }*/
            int second = (2 * numRows - i) % cycle;
            if (second == 0) second = cycle;
            for (int j = 0; j + i - 1 < s.length(); j+= cycle) {
                str.append(s.charAt(i - 1 + j));
                if (i != 1 && i != numRows && j + second - 1 < s.length()) {
                    str.append(s.charAt(second - 1 + j));
                }
            }
        }
        return String.valueOf(str);
    }

    /**
     * 求数组最长相同前缀 -easy 初始设结果为第一个，接着不断缩减
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)  return "";

        String result = strs[0];
        for (int i = 0; i < strs.length - 1; i++) {
            int minLen = Math.min(result.length(), strs[i + 1].length());
            result = result.substring(0, minLen);
            while (!strs[i + 1].substring(0, result.length()).equals(result)) {
                result = result.substring(0, result.length() - 1);
                if (result.length() == 0) return "";
            }
        }
        return result;

        /** 按列比较，比较麻烦。。
         *

        StringBuffer buffer = new StringBuffer();
        int strsLen = strs.length;
        int charLen = 0;

        if (strsLen == 1) return strs[0];

        while (true) {
            int i = 0;
            for (; i < strsLen - 1; i++) {
                ///if (strs[i].length() == 0) return "";
                if (charLen >= strs[i].length() || charLen >= strs[i + 1].length()
                        || strs[i].charAt(charLen) != strs[i + 1].charAt(charLen))
                    break;
            }
            if (i != 0 && i == strsLen - 1 && strs[i].length() != 0) {
                buffer.append(strs[0].charAt(charLen++));
            } else {
                break;
            }
        }
        return buffer.toString();
         */
    }

    /**
     * 求匹配字符串的index -easy 双指针
     */
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int i = 0, j = 0;
        for (; i < haystack.length() && j < needle.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(j))
                j++;
            else if (haystack.charAt(i) != needle.charAt(j) && j != 0){
                i = i - j;
                j = 0;
            }
        }
        if (j == needle.length())
            return i - j;
        else return -1;
    }

    /** 找匹配子串的所有index 比较麻烦。。最后超出时间限制。。
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> results = new ArrayList<>();
        if (words == null || words.length == 0) return results;
        int wLength = words[0].length();    //单词的长度
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(i, words[i]);
        }

        Map<Integer, Integer> recMap = new HashMap<>();     //记录 words的索引和s的索引
        int startIndex = 0;
        int i = 0;
        //for (int i = 0; i < s.length(); i++) {
        while (i <= s.length() - wLength) {
            String curStr = s.substring(i, i + wLength);
            if (!map.containsValue(curStr)) {
                recMap.clear();
                i = startIndex + 1;
                startIndex = i;
                //i += 1;
                continue;
            }
            int index = uniqueIndexOfWords(recMap, containWord(map, curStr));
            if (index != -1) {
                if (recMap.size() == 0) startIndex = i;
                recMap.put(index, i);
                i += wLength;
            } else {
                //i = startIndex + wLength;

                i = startIndex + 1;
                recMap.clear();
            }

            if (recMap.size() == words.length) {
                results.add(startIndex);
                recMap.clear();
                //i = startIndex + wLength;
                i = startIndex + 1;
            }
        }

        return results;
    }

    private List<Integer> containWord(Map map, String word) {    //返回words中含有word的所有索引
        List<Integer> keys = new ArrayList<>();
        for (Object key : map.keySet()) {
            if (map.get(key).equals(word))
                keys.add((Integer) key);
        }
        return keys;
    }

    private int uniqueIndexOfWords(Map recMap, List<Integer> keys) {     //返回没有被用到的索引
        if (keys == null || keys.size() == 0) return -1;
        for (int i = 0; i < keys.size(); i++) {
            if (!recMap.containsKey(keys.get(i))) return keys.get(i);
        }
        return -1;
    }

     */


}
