package leetcode.editor.cn;

// 977、有序数组的平方
//给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。 
//
// 示例 1：
//输入：nums = [-4,-1,0,3,10]
//输出：[0,1,9,16,100]
//解释：平方后，数组变为 [16,1,0,9,100]
//排序后，数组变为 [0,1,9,16,100] 
//
// 示例 2：
//输入：nums = [-7,-3,2,3,11]
//输出：[4,9,9,49,121]
//
// 提示：
// 1 <= nums.length <= 104 
// -104 <= nums[i] <= 104 
// nums 已按 非递减顺序 排序 
//
// 进阶：
// 请你设计时间复杂度为 O(n) 的算法解决本问题 
// 
// Related Topics 数组 双指针 排序 
// 👍 374 👎 0


import leetcode.editor.cn.helper.Utils;

import java.util.Arrays;

public class SquaresOfASortedArray {
    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    /*
     * 平方后排序
     */
    public static int[] sqrtSort(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[i] * nums[i];
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        Utils util = new Utils();
        Solution solution = new SquaresOfASortedArray().new Solution();
        int testTime = 500000;
        int minSize = 1;
        int maxSize = 104;
        int minValue = -104;
        int maxValue = 104;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] nums = util.generateRandomArray(minSize, maxSize, minValue, maxValue, true);
            int[] ans1 = sqrtSort(nums);
            int[] ans2 = solution.sortedSquares(nums);
            if (!isEqual(ans1, ans2)) {
                succeed = false;
                printArray(ans1);
                printArray(ans2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = util.generateRandomArray(minSize, maxSize, minValue, maxValue, true);
        printArray(arr);
        printArray(solution.sortedSquares(arr));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int[] ans = new int[nums.length];
        // 双指针分别指向数组两端
        int left = 0, right = nums.length - 1, index = nums.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                ans[index] = nums[left] * nums[left];
                left++;
            } else {
                ans[index] = nums[right] * nums[right];
                right--;
            }
            index--;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}