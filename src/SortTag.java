import java.util.PriorityQueue;
import java.util.Queue;

public class SortTag {
    /**
     * 稳定：如果a原本在b前面，而a=b，排序之后a仍然在b的前面。
     * 不稳定：如果a原本在b的前面，而a=b，排序之后 a 可能会出现在 b 的后面。
     * 时间复杂度：对排序数据的总的操作次数。反映当n变化时，操作次数呈现什么规律。
     * 空间复杂度：是指算法在计算机内执行时所需存储空间的度量，它也是数据规模n的函数。
     */

    /**
     * 1. 冒泡排序 时间复杂度：O(n^2) 空间复杂度：O(1) 稳定
     */
    int[] bubbleSort(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{};
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }

    /**
     * 2. 选择排序 时间复杂度：O(n^2) 空间复杂度：O(1) 不稳定
     */
    int[] selectSort(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{};
        int minIndex, temp;
        for (int i = 0; i < nums.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                minIndex = nums[minIndex] < nums[j] ? minIndex : j;
            }
            temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }
        return nums;
    }

    /**
     * 3. 插入排序 时间复杂度：O(n^2) 空间复杂度：O(1) 稳定
     */
    int[] insertSort(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{};
        int temp, tempVal;
        for (int i = 1; i < nums.length; i++) {
            temp = i;
            while (temp > 0 && nums[temp] < nums[temp - 1]) {
                tempVal = nums[temp];
                nums[temp] = nums[temp - 1];
                nums[temp - 1] = tempVal;
                temp--;
            }
        }
        return nums;
    }

    /**
     * 4. 希尔排序 时间复杂度：O(n^1.3) 空间复杂度：O(1) 不稳定
     */
    int[] shellSort(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{};
        int temp, tempVal;
        for (int i = nums.length / 2; i > 0; i /= 2) {
            for (int j = i; j < nums.length; j++) {
                temp = j;
                while (temp > 0 && nums[temp] < nums[temp - i]) {
                    tempVal = nums[temp];
                    nums[temp] = nums[temp - i];
                    nums[temp - i] = tempVal;
                    temp -= (temp - i);
                }
            }
        }
        return nums;
    }

    /**
     * 5. 归并排序 时间复杂度：O(nlogn) 空间复杂度：O(n) 稳定
     */
    int[] mergeSort(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{};
        int[] result = new int[nums.length];
        merge(nums, result, 0, nums.length - 1);
        return nums;
    }

    private void merge(int[] nums, int[] result, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;

        int start1 = left, end1 = mid, start2 = mid + 1, end2 = right;
        merge(nums, result, start1, end1);
        merge(nums, result, start2, end2);

        int k = start1;
        while (start1 <= end1 && start2 <= end2)
            result[k++] = nums[start1] < nums[start2] ? nums[start1++] : nums[start2++];
        while (start1 <= end1)
            result[k++] = nums[start1++];
        while (start2 <= end2)
            result[k++] = nums[start2++];
        for (k = left; k <= right; k++)
            nums[k] = result[k];
    }

    /**
     * 6. 快速排序 归并排序 时间复杂度：O(nlogn) 空间复杂度：O(nlogn) 不稳定
     */
    int[] quickSort(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{};
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private int partition(int[] nums, int left, int right) {
        int temp = nums[left];
        while (left < right) {
            while (temp <= nums[right] && left < right) right--;
            nums[left] = nums[right];
            while (nums[left] <= temp && left < right) left++;
            nums[right] = nums[left];
            nums[left] = temp;
        }
        return left;
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;

        int i = partition(nums, left, right);
        quickSort(nums, left, i);
        quickSort(nums, i + 1, right);
    }

    /**
     * 7. 堆排序 归并排序 时间复杂度：O(nlogn) 空间复杂度：O(n) 稳定
     */
    int[] heapSort(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{};

        for (int i = nums.length / 2 - 1; i >= 0; i--)
            adjustHeap(nums, i, nums.length);

        for (int j = nums.length - 1; j > 0; j--) {
            swap(nums, 0, j);
            adjustHeap(nums, 0, j);
        }

        return nums;
    }

    private void adjustHeap(int[] nums, int start, int length) {
        int temp = nums[start];
        for (int k = 2 * start + 1; k < length; k = 2 * k + 1) {
            if (k < length - 1 && nums[k] < nums[k + 1]) k++;
            if (nums[k] > temp) {
                swap(nums, start, k);
                start = k;
            } else {
                break;
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    /**
     * 8. 计数排序 当输入的元素是 n 个 0到 k 之间的整数时，时间复杂度是 O(n+k)，空间复杂度也是 O(n+k) 稳定
     */
    int[] countSort(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{};

        int maxVal = nums[0];
        int minVal = nums[0];
        for (int num : nums) {
            maxVal = Math.max(num, maxVal);
            minVal = Math.min(num, minVal);
        }
        int[] helper = new int[maxVal - minVal + 1];
        for (int val : nums) {
            helper[val] += 1;
        }
        int index = 0;
        for (int i = 0; i < helper.length; i++) {
            while (helper[i] != 0) {
                nums[index++] = i;
                helper[i]--;
            }
        }

        return nums;
    }
}
