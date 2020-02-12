import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapTag {
    /**
     * 找不限顺序的字串的所有index -hard 2个hashMap可以解决
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> results = new ArrayList<>();
        if (words == null || words.length == 0) return results;

        int wLength = words[0].length();    //单词的长度
        int wordsNum = words.length;        //单词的数量

        Map<String, Integer> map1 = new HashMap<>();    //将words放入map
        for (String str : words) {
            int value = map1.getOrDefault(str, 0);
            map1.put(str, value + 1);
        }

        for (int i = 0; i <= s.length() - wLength * wordsNum; i++) {
            Map<String, Integer> map2 = new HashMap<>();
            int num = 0;
            while (num < wordsNum) {
                String str = s.substring(i + num * wLength, i + (num + 1) * wLength);
                if (map1.containsKey(str)) {
                    int value = map2.getOrDefault(str, 0);
                    map2.put(str, value + 1);
                    if (map2.get(str) > map1.get(str)) break;
                } else {
                    break;
                }
                num++;
            }

            if (num == wordsNum) results.add(i);
        }
        return results;
    }

}
