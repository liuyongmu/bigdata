package leetcode.editor.cn;

// 4、寻找两个正序数组的中位数
//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
// 算法的时间复杂度应该为 O(log (m+n)) 。 
//
// 示例 1：
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
//
// 示例 2：
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
//
// 示例 3：
//输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
//
// 示例 4：
//输入：nums1 = [], nums2 = [1]
//输出：1.00000
//
// 示例 5：
//输入：nums1 = [2], nums2 = []
//输出：2.00000
//
// 提示：
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -106 <= nums1[i], nums2[i] <= 106 
// 
// Related Topics 数组 二分查找 分治 
// 👍 4796 👎 0


import leetcode.editor.cn.helper.Utils;

import java.util.Arrays;

public class MedianOfTwoSortedArrays {
    // for test
    public static double mergeSortMedian(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int[] nums = new int[len];
        int index = 0;
        for (int x : nums1) {
            nums[index++] = x;
        }
        for (int x : nums2) {
            nums[index++] = x;
        }
        Arrays.sort(nums);
        if (len % 2 == 1) {
            return nums[len / 2];
        }
        return (nums[len / 2 - 1] + nums[len / 2]) / 2.0;
    }

    public static void main(String[] args) {
        Utils util = new Utils();
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
        int testTime = 100000;
        int minSize = 0, maxSize = 1000, minVal = -106, maxValue = 106;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] nums1 = util.generateRandomArray(minSize, maxSize, minVal, maxValue, true);
            int[] nums2 = util.generateRandomArray(minSize, maxSize, minVal, maxValue, true);
            if (nums1.length == 0 && nums2.length == 0) break;
            double ans1 = mergeSortMedian(nums1, nums2);
            double ans2 = solution.findMedianSortedArrays(nums1, nums2);
            if (ans1 != ans2) {
                succeed = false;
                System.out.println(Arrays.toString(nums1));
                System.out.println(Arrays.toString(nums2));
                System.out.println("ans1: " + ans1);
                System.out.println("ans2: " + ans2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 两个数组都是已经拍好序的，从数组的起始点开始遍历，谁小谁往右移，直到移动中位数的地方
     * @param nums1 数组
     * @param nums2 数组
     * @return 两数组的中位数
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int sumLength = m + n;
        // left和right用来接受遍历到的数，属于一前一后的关系，因为数组长度总和可能是偶数
        int left = 0, right = 0, mIndex = 0, nIndex = 0;
        for (int i = 0; i <= sumLength / 2; i++) {
            left = right;
            // 条件
            if (mIndex < m && (nIndex >= n || nums1[mIndex] <= nums2[nIndex])) {
                right = nums1[mIndex];
                mIndex++;
            } else {
                right = nums2[nIndex];
                nIndex++;
            }
        }
        // 判断总长度的奇偶性
        if (sumLength % 2 == 1) {
            return right;
        }
        return (left + right) / 2.0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}