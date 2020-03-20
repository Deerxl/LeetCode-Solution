import java.util.*;

public class ArrayTag {
    /**
     * twoSum: -easy hashcode
     *
     * Given nums = [2, 7, 11, 15], target = 9,
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     *
     * use hash code, save time, but cost memory.
     *
     * @param nums array
     * @param target the needed sum
     * @return two nums
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> amap = new HashMap<>();
        int size = nums.length;
        for (int i = 0; i < size; i++){
            int complement = target - nums[i];
            if (amap.containsKey(complement)){
                return new int[] {amap.get(complement), i};
            }
            amap.put(nums[i], i);
        }
        return null;
    }

    /**
     * 求两个数组的中位数 -hard 二分法 分治法
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /**
         * my ways : let nums3 to sort the array and find the medium element(s).
         * Runtime: 6 ms, Memory Usage: 41.8 MB
         */
        /**
         if (nums1.length != 0 && nums2.length != 0) {
         int index = (nums1.length + nums2.length) / 2;      //index为奇数的那个 或 偶数的大的一个
         int[] nums3 = new int[nums1.length + nums2.length];
         for (int i = nums1.length; i < nums3.length; i++) {
         nums3[i] = Integer.MAX_VALUE;
         }
         System.arraycopy(nums1, 0, nums3, 0, nums1.length);

         //int count = nums1.length;  //记录当前nums3已经添加的数量
         // point = -1;  //记录当前nums3排序正确的最大的索引
         for (int count = nums1.length, point = -1, index2 = 0; point < index; point++) {
         if (index2 < nums2.length && nums2[index2] < nums3[point + 1]) {      //nums2[i] 插入，nums3中元素依次后移
         int j = count++;
         while ((j - 1) > point) {
         nums3[j] = nums3[j - 1];
         j--;
         }
         nums3[point + 1] = nums2[index2];
         index2 += 1;
         }
         }
         return (((nums1.length + nums2.length) % 2) == 0) ? ((double) (nums3[index] + nums3[index - 1]) / 2) : nums3[index];
         } else if (nums1.length == 0 && nums2.length != 0) {
         return (nums2.length % 2 == 0) ? ((double) (nums2[nums2.length / 2] + nums2[nums2.length / 2 - 1]) / 2) : nums2[nums2.length / 2];
         } else {
         return (nums1.length % 2 == 0) ? ((double) (nums1[nums1.length / 2] + nums1[nums1.length / 2 - 1]) / 2) : nums1[nums1.length / 2];
         }
         */

        /**
         * 递归法
         */

        /*int m = nums1.length;
        int n = nums2.length;

        // to ensure m <= n
        if (m > n) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;

            int tmp = m;
            m = n;
            n = tmp;
        }

        int iMin = 0, iMax = m;
        int halfLen = (m + n + 1) / 2;
        //int i = 0, j = (m + n - 2 * i) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && nums1[i] < nums2[j - 1]) {
                iMin = i + 1;
            } else if (i > iMin && nums1[i - 1] > nums2[j]) {
                iMax = i - 1;
            } else {
                int flag = (m + n) % 2;

                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if (flag == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums1[i], nums2[j]);
                }

                return (double) (minRight + maxLeft) / 2;

            }
        }

        return 0.0;*/

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
     * 求最大面积 -medium 双指针法
     */
    public int maxArea(int[] height) {
        int maxV = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            maxV = Math.max(maxV, Math.min(height[left], height[right]) * (right - left));
            if ((height[left] < height[right])) {
                left++;
            } else {
                right--;
            }
        }
        return maxV;
    }

    /**
     * 求和为 0 的三数组合 -medium 双指针法
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        //注释解答超时
        /*Map<Integer, Integer> table = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            table.put(i, nums[i]);
        }*/
        /*if (nums.length < 3) {
            return result;
        } else if (nums[0] == nums[nums.length -1]) {
            if (nums[0] != 0){
                return result;
            }
            List<Integer> triple = Arrays.asList(0 ,0, 0);
            result.add(triple);
            return result;
        }
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (j > i + 2 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int complement = 0 - nums[i] - nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[k] == complement) {
                        *//*List<Integer> triple = new ArrayList<>();
                        triple.add(nums[i]);
                        triple.add(nums[j]);
                        triple.add(complement);*/
        /*
                        List<Integer> triple = Arrays.asList(nums[i], nums[j], complement);
                        result.add(triple);
                        break;
                    }
                }
                *//*if (table.containsValue(complement)) {

                }*//*
            }
        }*/

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && (nums[i] != nums[i - 1]))) {
                int l = i + 1, r = nums.length - 1, sum = 0 - nums[i];
                while (l < r) {
                    if (nums[l] + nums[r] == sum) {
                        result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) l++;
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        l++;
                        r--;
                    } else if (nums[l] + nums[r] < sum) {
                        while (l < r && nums[l] == nums[l + 1]) l++;
                        l++;
                    } else if (nums[l] + nums[r] > sum) {
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        r--;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 求和最接近 target 的三个数 -medium 双指针
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closeSum = 0;
        if (nums.length >= 3) {
            closeSum = nums[0] + nums[1] + nums[2];
            int tmp = 0;
            int l = 1;
            int r = nums.length - 1;
            int compliment = 0;

            for (int i = 0; i < nums.length - 2; i++) {
                compliment = target - nums[i];
                l = i + 1;
                r = nums.length - 1;

                while (l < r) {
                    tmp = nums[i] + nums[l] + nums[r];
                    closeSum = Math.abs(closeSum - target) < Math.abs(tmp - target) ? closeSum : tmp;

                    if (l < r && nums[l] + nums[r] == compliment) return target;
                    else if (l < r && nums[l] + nums[r] < compliment) {
                        while (l < r && nums[l] == nums[l + 1]) l++;
                        l++;
                    } else if (l < r && nums[l] + nums[r] > compliment) {
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        r--;
                    }
                }
            }
        }
        return closeSum;
    }

    /**
     * 求和为target的四个数组合 -medium 双指针 注意排除冗余判断以加快速度！
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        if (length >= 4) {
            Arrays.sort(nums);
            /*Hashtable<Integer, Integer> table = new Hashtable<>();
            for (int i = 0; i < nums.length; i++) {
                table.put(i, nums[i]);
            }*/

            for (int i = 0; i < length - 3; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) continue;

                int iMin = nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3];
                if (iMin > target) break;

                int iMax = nums[length - 1] + nums[length - 2] + nums[length - 3] + nums[length - 4];
                if (iMax < target) break;

                for (int j = i + 1; j < nums.length - 2; j++) {
                    if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                    int sum = target - nums[i] - nums[j];
                    int l = j + 1;
                    int r = nums.length - 1;

                    //if (nums[l] > sum) continue;

                    while (l < r) {
                        if (nums[l] + nums[r] == sum) {
                            List<Integer> solution = Arrays.asList(nums[i], nums[j], nums[l], nums[r]);
                            result.add(solution);

                            while (l < r && nums[l] == nums[l + 1]) l++;
                            l++;
                            while (l < r && nums[r] == nums[r - 1]) r--;
                            r--;
                        } else if (nums[l] + nums[r] < sum) {
                            while (l < r && nums[l] == nums[l + 1]) l++;
                            l++;
                        } else {
                            while (l < r && nums[r] == nums[r - 1]) r--;
                            r--;
                        }
                    }
                }



            }
        }

        return result;
    }

    /**
     * 去除数组重复元素并返回长度 -easy 双指针 i为慢针 j为快针
     */
    public int removeDuplicates(int[] nums) {
        /** 方法一 较慢
        int length = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsValue(num)) {
                map.put(length, num);
                length += 1;
            }
        }
        for (int i = 0; i < length; i++) {
            nums[i] = map.get(i);
        }
        if (nums.length > length) nums[length] = '\0';
        return length;
         */

        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }

    /**
     * 计算非val元素的个数 -easy
     */
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) return 0;
        int length = 0;
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i++] = nums[j];
                length++;
            }
        }
        return length;
    }

    /**
     * 找到比此数组代表的数字大的最少的数组排列 -medium 对尾部递减的 找到拐点 交换后 再排序
     */
    public int[] nextPermutation(int[] nums) {
        int i = nums.length - 1;
        int changeIndex = 0;
        while (i >= 1 && nums[i] - nums [i - 1] <= 0) {
            i--;
        }
        if (i == nums.length - 1) {
            if (i >= 1) {
                int temp = nums[nums.length - 1];
                nums[nums.length - 1] = nums[nums.length - 2];
                nums[nums.length - 2] = temp;
            }
            return nums;
        } else if (i >= 1) {
            int changeVal = nums[i - 1];
            changeIndex = i - 1;
            while (i < nums.length) {
                if ((i == nums.length -1 && nums[i] > changeVal) || nums[i + 1] <= changeVal) {
                    nums[changeIndex] = nums[i];
                    nums[i] = changeVal;
                    break;
                }
                i++;
            }
        }
        int offset = i == 0 ? i : changeIndex + 1;
        for (i = offset; i < (nums.length - offset) / 2 + offset; i++) {
            int temp = nums[i];
            nums[i] = nums[nums.length - i + offset - 1];
            nums[nums.length - i + offset - 1] = temp;
        }
        return nums;
    }

    /**
     * 数组 最大子序列和 -easy
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return Integer.MIN_VALUE;
        int maxVal = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.max(nums[i], nums[i - 1]);
            maxVal = Math.max(maxVal, nums[i]);
        }
        return maxVal;
    }

    /**
     * 缺失的第一个正数 -hard 只允许使用常数空间
     * 先替换 <= 0, > length的数，遍历，找到nums[i]对应的值标记为负数，再次遍历，出现的第一个正数对应的索引即为缺失的数
     */
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;

        int num1count = 0, length = nums.length;
        for (int num : nums) {
            if (num == 1) num1count++;
        }
        if (num1count == 0) return 1;
        if (length == 1) return 2;

        for (int i = 0; i < length; i++) {
            if (nums[i] <= 0 || nums[i] > length) nums[i] = 1;
        }

        for (int i = 0; i < length; i++) {
            int cur = Math.abs(nums[i]);
            if (cur == length) nums[0] = -Math.abs(nums[0]);
            else nums[cur] = -Math.abs(nums[cur]);
        }

        for (int i = 1; i < length; i++) {
            if (nums[i] > 0) return i;
        }
        if (nums[0] > 0) return length;
        return length + 1;
    }
}
