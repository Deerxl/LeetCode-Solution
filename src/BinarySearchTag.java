public class BinarySearchTag {
    /**
     * 求两个数组的中位数 -hard 二分法 分治法
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;

            int tmp = m;
            m = n;
            n = tmp;
        }

        int i = (m + 1) / 2;
        int halfLen = (m + n + 1) / 2;
        int j = halfLen - i;

        while (i >= 0 && i <= m) {
            if (i < m && nums1[i] < nums2[j - 1]) {
                i += 1;
                j -= 1;
            } else if (i > 0 && nums1[i - 1] > nums2[j]) {
                i -= 1;
                j += 1;
            } else {
                int odd = (m + n) % 2;
                int maxLeft, minRight;

                if (m == 0 || i == 0) {
                    maxLeft = j > 0 ? nums2[j - 1] : nums2[j];
                } else {
                    maxLeft = j > 0 ? Math.max(nums1[i - 1], nums2[j - 1]) : nums1[i - 1];
                }

                if (odd == 1) {
                    return maxLeft;
                } else {
                    if (i == m) {
                        minRight = nums2[j];
                    } else if (j == n) {
                        minRight = nums1[i];
                    } else {
                        minRight = Math.min(nums1[i], nums2[j]);
                    }
                    return (double)(maxLeft + minRight) / 2 ;
                }
            }
        }

        return 0.0;
    }

    /**
     * 求旋转后的数组的数的index -medium 二分法
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid = (left + right) / 2;

        while (left <= right) {
            if (nums[mid] == target) return mid;

            if (nums[left] <= nums[mid]) {   //左边升序
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            mid = (left + right) / 2;
        }
        return -1;
    }

    /**
     * 求target在数组中出现的首尾index -medium 二分法
     */
    public int[] searchRange(int[] nums, int target) {
        int[] defaultRes = new int[]{-1, -1};
        if (nums == null || nums.length == 0) return defaultRes;

        int left = findHalfIndex(nums, true, target);
        if (left == nums.length || nums[left] != target) return defaultRes;

        defaultRes[0] = left;
        int right = findHalfIndex(nums, false, target);
        defaultRes[1] = right;
        return defaultRes;

        /** my way: 二分法，比较复杂
        if (nums == null || nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target) return new int[]{-1, -1};
        int left = 0, right = nums.length - 1;
        int mid = (left + right) / 2;

        while (left <= right) {
            if (nums[mid] == target) {
                while (true) {
                    if ((nums[left] == target && left == 0)
                            || (nums[left] == target && left > 0 && nums[left - 1] < target)){
                        break;
                    }
                    else if (left >= 0 && nums[left] < target) {
                        //left += (mid - left + 1) / 2;
                        left++;
                    } else if (left > 0 && nums[left] == target && nums[left - 1] == target) {
                        //left -= (left + 1) / 2;
                        left--;
                    }
                    //mid = (left + right) / 2;
                }
                while (true) {
                    if ((nums[right] == target && right == nums.length - 1)
                            || (nums[right] == target && right < nums.length - 1 && nums[right + 1] != target)) {
                        break;
                    }
                    else if (nums[right] > target) {
                        //right -= (right - mid + 1) / 2;
                        right--;
                    } else if (right < nums.length && nums[right] == target) {
                        //right += (nums.length - right + 1) / 2;
                        right++;
                    }
                    //mid = (left + right) / 2;
                }
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            mid = (left + right) / 2;
        }
        if (left <= right) return new int[]{left, right};
        else return new int[]{-1, -1};
         */
    }

    private int findHalfIndex(int[] nums, boolean leftSide, int target) {
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (leftSide && nums[mid] == target)) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return leftSide ? left : left - 1;
    }

    /**
     * 二分法求target索引  -easy
     */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int low = 0, high = nums.length;
        int mid;
        while (low < high && low >= 0) {
            mid = (low + high) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            else if (target < nums[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

}
