import java.util.*;

public class Main {
    public static void main(String[] args) {
        //ArrayTag one = new ArrayTag();

        /*int[] nums = {2, 7, 11, 15};
        int[] res = one.twoSum(nums, 9);
        if (res == new int[]{0, 1}) {
            System.out.println("1 : right.");
        }*/

        //Test test = new Test();
        //test.test_findMedianSortedArrays();
        //test.test_fourSum();

        //test.test_nextPermutation();
        //test.test_lengthOfLongestSubstring();

        //System.out.println(-1/2);
        //test.test_longestPalindrome();
        //test.test_convert();
        //test.test_reverse();
        //test.test_myAtoi();
        //test.test_intToRoman();
        //test.test_longestCommonPrefix();
        //test.test_letterCombinations();
        //test.test_removeNthFromEnd();
        //test.test_swapPairs();
        //test.test_strStr();
        //test.test_divide();
        //test.test_findSubstring();
        //test.test_longestValidParentheses();
        //test.test_search();
        //test.test_searchRange();
        //test.test_myPow();
        //test.test_trap();
        //test.test_simplifyPath();
        //test.test_largestRectangleArea();
        //test.test_maximalRectangle();
        //test.test_inorderTraversal();
        //test.test_isValidBST();
        //test.test_findMode();
        //test.test_zigzagLevelOrder();
        //test.test_findNumberIn2DArray ();
        //test.test_buildTree();
        //test.test_getLeastNumbers();
        //test.test_movingCount();
        //test.test_isNumber();
        //test.test_exchange();
        //test.test_spiralOrder();
        //test.test_isMatch();
        //test.test_verifyPostorder();
        //test.test_sort();
        //test.test_combinationSum();

        //Scanner s = new Scanner(System.in);
        //MathTag mathTag = new MathTag();
        //mathTag.getContinueNums(s.nextInt(), s.nextInt());

        /*Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = scanner.nextInt();

        System.out.println(maxUpStrings(n, arr));*/

        /*Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        String num = scanner.nextLine();

        char[] charArr = num.toCharArray();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(String.valueOf(charArr[i]));
        }

        for (int i = 0; i < m; i++) {
            String str = scanner.nextLine();
            if (str.charAt(0) == 'q') {
                findUpSubstring(arr);
            } else if (str.charAt(0) == 'c') {   //c x y将区间[x,y]的0变为1,1变为0。
                int x = Integer.parseInt(String.valueOf(str.charAt(2)));
                int y = Integer.parseInt(String.valueOf(str.charAt(4)));
                reverse(arr, x - 1, y - 1);
            }
        }*/

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        in.nextLine();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(in.nextLine());
        }
        findMaxLen(n, list);
    }

    static void findMaxLen(int n, List<String> list) {
        Collections.sort(list);
        int maxLen = 0, tmpLen = 0;
        int count = 0;
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            if (list.get(i).charAt(0) == list.get(i + 1).charAt(0)
            && (list.get(i).charAt(list.get(i).length() - 1) < list.get(i + 1).charAt(list.get(i + 1).length() - 1))
            && (list.get(i).length() < list.get(i + 1).length())) {
                count++;
            } else {
                ls.add(list.get(i));
            }
        }
        ls.add(list.get(list.size() - 1));

        for (int i = 0; i < ls.size() - 1; i++) {
            String a = ls.get(i), b = ls.get(i + 1);
            if (a.charAt(0) < b.charAt(0) && a.charAt(a.length() - 1) <= b.charAt(0)) {
                tmpLen += a.length();
            }
        }
        if (ls.size() > 1) {
            String a = ls.get(ls.size() - 1);
            String b = ls.get(ls.size() - 2);
            if (a.charAt(0) >= b.charAt(b.length() - 1)) {
                tmpLen += a.length();
            }
        }
        System.out.println(tmpLen);

    }
}
