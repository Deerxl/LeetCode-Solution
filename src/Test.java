import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {


    public void test_findMedianSortedArrays(){
        ArrayTag arrayTag = new ArrayTag();
        int[] nums1 = {100001};
        int[] nums2 = {100000};
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
}
