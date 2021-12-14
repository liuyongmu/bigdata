package leetcode.editor.cn;

// 34 在排序数组中查找元素的第一个和最后一个位置
//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶：
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
//
// 示例 1：
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2：
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3：
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 提示：
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// nums 是一个非递减数组 
// -109 <= target <= 109 
// 
// Related Topics 数组 二分查找 
// 👍 1337 👎 0

import java.util.Arrays;
import java.util.Random;

public class FindFirstAndLastPositionOfElementInSortedArray{
    public static void main(String[] args) {
        Solution solution = new FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        Random random = new Random();
        boolean flag = true;
        for (int i = 0; i < 100000; i++) {
            int[] nums = generateRandomArray(random);
            int target = random.nextInt(219) - 109;
            int[] ans1 = search(nums, target);
            int[] ans2 = solution.searchRange(nums, target);
            // 比对结果
            if (ans1[0] != ans2[0] && ans1[1] != ans2[1]) {
                flag = false;
                System.out.println("测试出错！");
                System.out.println("target: " + target);
                System.out.println("nums: " + Arrays.toString(nums));
                System.out.println(Arrays.toString(ans1));
                System.out.println(Arrays.toString(ans2));
                break;
            }
        }
        if (flag) {
            System.out.println("测试成功！");
        }
    }

    /**
     * 生成随机数组
     * @param random
     * @return
     */
    public static int[] generateRandomArray(Random random) {
        int[] array = new int[random.nextInt(106)];
        for (int j = 0; j < array.length; j++) {
            array[j] = random.nextInt(219) - 109;
        }
        Arrays.sort(array);
        return array;
    }

    /**
     * 暴力搜索
     * @param nums
     * @param target
     * @return
     */
    public static int[] search(int[] nums, int target) {
        int[] ans = {-1, -1};
        if (nums == null || nums.length == 0) {
            return ans;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (ans[0] == -1) {
                    ans[0] = i;
                }
                ans[1] = i;
            } else if (nums[i] > target) {
                break;
            }
        }
        return ans;
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = {-1, -1};
        if (nums == null || nums.length == 0) {
            return ans;
        }
        // 两次二分，分别查找target的起始位置 - 1与终止位置 + 1
        int left = searchEdgesIndex(nums, target, true);
        int right = searchEdgesIndex(nums, target, false);
        if (left + 1 <= right - 1) {
            ans[0] = left + 1;
            ans[1] = right - 1;
        }
        return ans;
    }

    /**
     * 查找target在数组中的边界索引
     * @param nums 数组
     * @param target 目标
     * @param flag true表示查找左边界，false表示查找右边界
     * @return 对应的左右边界索引
     */
    private int searchEdgesIndex(int[] nums, int target, boolean flag) {
        int left = 0, right = nums.length - 1, ans = -1;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (flag) {
                // 查找左边界
                right = mid - 1;
                ans = right;
            } else {
                // 查找右边界
                left = mid + 1;
                ans = left;
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
