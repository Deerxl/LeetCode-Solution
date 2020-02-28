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

    void test_findSubstring() {
        String s = "barfoofoobarthefoobarman";
        String[] words = new String[] {"bar","foo","the"};
        StringTag stringTag = new StringTag();
        //System.out.println(stringTag.findSubstring(s, words));
    }

    void test_longestValidParentheses() {
        String s = ")()())";
        DynamicProgrammingTag dynamicProgrammingTag = new DynamicProgrammingTag();
        System.out.println(dynamicProgrammingTag.longestValidParentheses(s));
    }

    void test_search() {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        int target = 0;
        BinarySearchTag binarySearchTag = new BinarySearchTag();
        System.out.println(binarySearchTag.search(nums, target));
    }

    void test_searchRange() {
        int[] nums = new int[]{0,0,1,1,1,2,4,4,4,4,5,5,5,6,8,8,9,9,10,10,10};
        int target = 8;
        BinarySearchTag binarySearchTag = new BinarySearchTag();
        System.out.println(Arrays.toString(binarySearchTag.searchRange(nums, target)));
    }

    void test_myPow() {
        double x = 0.7;
        int n = 29;
        MathTag mathTag = new MathTag();
        System.out.println(Math.pow(x, n) + "   " + mathTag.myPow(x, n));
    }

    void test_trap() {
        int[] height = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
        StackTag stackTag = new StackTag();
        System.out.println(stackTag.trap(height));
    }

    void test_simplifyPath() {
        String s = "/..hidden";
        StackTag stackTag = new StackTag();
        System.out.println(stackTag.simplifyPath(s));
    }

    void test_largestRectangleArea() {
        int[] height = new int[] {2,1,5,6,2,3};
        StackTag stackTag = new StackTag();
        System.out.println(stackTag.largestRectangleArea(height));
    }

    void test_maximalRectangle() {
        String[][] matrix = new String[][]{
                {"0", "1"},
                {"1","0"},
                //{"1", "0", "0", "1", "0"},
                //{"1","1","1","1","1"},
        };

        char[][] m2 = new char[][] {
                {'0','1','1','0','1'},
                {'1','1','0','1','0'},
                {'0','1','1','1','0'},
                {'1','1','1','1','0'},
                {'1','1','1','1','1'},
                {'0','0','0','0','0'}
        };
        StackTag stackTag = new StackTag();
        System.out.println(stackTag.maximalRectangle(matrix));
    }

    void test_inorderTraversal() {
        TreeNode root = new TreeNode(1);
        TreeNode no2 = new TreeNode(2);
        TreeNode no3 = new TreeNode(3);
        root.left = null;
        root.right = no2;
        no2.left = no3;
        no2.right = null;

        TreeNode[] x = new TreeNode[] {
                new TreeNode(1),
                null,
                new TreeNode(2),
                new TreeNode(3)
        };
        TreeTag treeTag = new TreeTag();
        System.out.println(treeTag.inorderTraversal(root));
    }

    void test_isValidBST() {
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        //root.left = new TreeNode(5);
        //root.right = new TreeNode(15);
        //root.right.left = new TreeNode(6);
        //root.right.right = new TreeNode(20);

        TreeTag treeTag = new TreeTag();
        System.out.println(treeTag.isValidBST(root));
    }

    void test_findMode() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(2);
        root.right.right.right = new TreeNode(3);
        root.right.right.right.left = new TreeNode(3);
        root.right.right.right.right = new TreeNode(3);

        TreeTag treeTag = new TreeTag();
        System.out.println(Arrays.toString(treeTag.findMode(root)));
    }

    void test_zigzagLevelOrder() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        TreeTag treeTag = new TreeTag();
        treeTag.zigzagLevelOrder(root);
    }

    void test_findNumberIn2DArray () {
        int[][] nums = new int[][] {
                {2, 5},
                {2, 8},
                {7, 9},
                {7, 11},
                {9, 11}
        };
        int target = 7;
        JianZhiOfferTag offer  = new JianZhiOfferTag();
        offer.findNumberIn2DArray(nums, target);
    }

    void test_buildTree() {
        int[] preorder = new int[] {3,9,20,15,7};
        int[] inorder = new int[] {9,3,15,20,7};
        JianZhiOfferTag offer = new JianZhiOfferTag();
        System.out.println(offer.buildTree(preorder, inorder));
    }

    void test_getLeastNumbers() {
        int[] arr = new int[] {0,0,0,2,0,5};
        int k = 0;
        HeapTag heapTag = new HeapTag();
        heapTag.getLeastNumbers(arr, k);
    }

    void test_movingCount() {
        int m = 16;
        int n = 8;
        int k = 4;
        JianZhiOfferTag offer = new JianZhiOfferTag();
        System.out.println(offer.movingCount(m, n, k));
    }

    void test_isNumber() {
        String s = ".3e-333333333";
        JianZhiOfferTag offer = new JianZhiOfferTag();
        System.out.println(offer.isNumber(s));
    }

    void test_exchange() {
        int[] nums = new int[] {1,2,3,4};
        JianZhiOfferTag offer = new JianZhiOfferTag();
        System.out.println(Arrays.toString(offer.exchange(nums)));
    }

    void test_spiralOrder() {
        int[][] nums = new int[][] {
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
                {8},
                {9}
        };
        JianZhiOfferTag offer = new JianZhiOfferTag();
        System.out.println(Arrays.toString(offer.spiralOrder(nums)));
    }
}
