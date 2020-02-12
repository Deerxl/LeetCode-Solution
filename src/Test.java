import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {


    public void test_findMedianSortedArrays(){
        ArrayTag arrayTag = new ArrayTag();
        int[] nums1 = {};
        int[] nums2 = {1,2,3,4,5};
        double medium = arrayTag.findMedianSortedArrays(nums1, nums2);
        System.out.println(medium);
    }

    void test_fourSum() {
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        int tag = 0;
        ArrayTag arrayTag = new ArrayTag();
        List<List<Integer>> result = arrayTag.fourSum(nums, tag);
        System.out.println(result);

    }

    void test_nextPermutation() {
        int[] nums = new int[]{9,9,7,8,8,8,0};
        ArrayTag arrayTag = new ArrayTag();
        System.out.println(arrayTag.nextPermutation(nums));
    }

    void test_lengthOfLongestSubstring() {
        String s = "gwregvrvf4wfwefasfeaf";
        StringTag stringTag = new StringTag();
        System.out.println(stringTag.lengthOfLongestSubstring(s));
    }

    void test_longestPalindrome() {
        String s = "aaabaaaa";
        StringTag stringTag = new StringTag();
        System.out.println(stringTag.longestPalindrome(s));
    }

    void test_convert() {
        String s = "LEETCODEISHIRING";
        int numRows = 4;
        StringTag stringTag = new StringTag();
        System.out.println(stringTag.convert(s, numRows));
    }

    void test_reverse() {
        int x = -54321;
        MathTag mathTag = new MathTag();
        System.out.println(mathTag.reverse(x));
    }

    void test_myAtoi() {
        String s = "2147483648";
        MathTag mathTag = new MathTag();
        System.out.println(mathTag.myAtoi(s));
    }

    void test_intToRoman() {
        int num = 1994;
        MathTag mathTag = new MathTag();
        System.out.println(mathTag.intToRoman(num));
    }

    void test_longestCommonPrefix() {
        String[] strs = new String[]{"flower","flow","flight"};
        StringTag stringTag = new StringTag();

        System.out.println("result is " + stringTag.longestCommonPrefix(strs));
    }

    void test_letterCombinations() {
        String str = "23";
        BackTrackingTag backTrackingTag = new BackTrackingTag();
        System.out.println("result is " + backTrackingTag.letterCombinations(str));
    }

    void test_removeNthFromEnd() {
        ListNode head = new ListNode(0);
        //ListNode p = head;
        //ListNode p1 = new ListNode(1); p.next = p1;
        //ListNode p2 = new ListNode(2); p1.next = p2;
        //ListNode p3 = new ListNode(3); p2.next = p3;
        //ListNode p4 = new ListNode(4); p3.next = p4;
        //ListNode p5 = new ListNode(5); p4.next = p5;

        int n = 1;
        LinkeListTag linkeListTag = new LinkeListTag();
        System.out.println(linkeListTag.removeNthFromEnd(head, n));
    }

    void test_swapPairs() {
        ListNode head = new ListNode(0);
        ListNode p = head;
        ListNode p1 = new ListNode(1); p.next = p1;
        ListNode p2 = new ListNode(2); p1.next = p2;
        ListNode p3 = new ListNode(3); p2.next = p3;
        ListNode p4 = new ListNode(4); p3.next = p4;
        ListNode p5 = new ListNode(5); p4.next = p5;
        LinkeListTag linkeListTag = new LinkeListTag();
        System.out.println(linkeListTag.swapPairs(head));
    }

    void test_strStr() {
        String str1 = "mississippi";
        String str2 = "pi";
        StringTag stringTag = new StringTag();
        System.out.println(stringTag.strStr(str1, str2));
    }

    void test_divide() {
        int div1 = 0x80000000;
        int div2 = -1;
        MathTag mathTag = new MathTag();
        System.out.println(div1 / div2 + "      " + mathTag.divide(div1, div2));
    }
}
