import java.util.Hashtable;

class StringTag {
    /**
     * 求无重复子串的最长长度 -medium 滑动窗口 hash map
     * @param s
     * @return
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


}
