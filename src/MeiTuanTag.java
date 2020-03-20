import java.util.Arrays;

/**
 * 注：此为2020美团笔试的部分内容，可能有bug
 */
public class MeiTuanTag {

    static void findUpSubstring(int[] arr) {
        int maxLen = 0, tmpLen = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= arr[i - 1]) tmpLen += 1;
            else {
                maxLen = Math.max(maxLen, tmpLen);
                tmpLen = 1;
            }
            maxLen = Math.max(maxLen, tmpLen);
        }
        System.out.println(maxLen);
    }

    static void reverse(int[] arr, int start, int end) {
        if (start < 0 || end >= arr.length || start > end)
            return;

        for (int i = start; i <= end; i++) {
            if (arr[i] == 0) arr[i] = 1;
            else arr[i] = 0;
        }
    }

    static int findMaxThreeNumberSum(int n, int[] arr1, int[] arr2) {
        int maxVal = 0;

        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int sum1 = 0, sum2 = 0;

        if (n < 3) {
            for (Integer i : arr1) sum1 += i;
            for (Integer i : arr2) sum2 += i;
        } else {
            sum1 = arr1[n - 1] + arr1[n - 2] + arr1[n - 3];
            sum2 = arr2[n - 1] + arr2[n - 2] + arr2[n - 3];
        }
        maxVal = Math.max(sum1, sum2);
        return maxVal;
    }

    static int maxUpStrings(int n, int[] arr) {
        int maxLen = 0, index = 0, tmpLen = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                tmpLen = findContinueMax(i, arr);
                if (tmpLen > maxLen) {
                    index = i;
                    maxLen = tmpLen;
                }
            }
        }
        return index;
    }

    static int findContinueMax(int exceptIndex, int[] arr) {
        int maxLen = 0, tmpLen = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (i == exceptIndex) continue;
            if (arr[i] < arr[i + 1]) tmpLen += 1;
            else {
                maxLen = Math.max(maxLen, tmpLen);
                tmpLen = 0;
            }
        }
        maxLen = Math.max(maxLen, tmpLen);
        return maxLen;
    }
}
