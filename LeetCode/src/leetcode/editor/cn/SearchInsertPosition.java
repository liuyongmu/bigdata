package leetcode.editor.cn;

// 35 搜索插入位置
//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
// 请必须使用时间复杂度为 O(log n) 的算法。 

// 示例 1:
//输入: nums = [1,3,5,6], target = 5
//输出: 2

// 示例 2:
//输入: nums = [1,3,5,6], target = 2
//输出: 1

// 示例 3:
//输入: nums = [1,3,5,6], target = 7
//输出: 4

// 示例 4:
//输入: nums = [1,3,5,6], target = 0
//输出: 0

// 示例 5:
//输入: nums = [1], target = 0
//输出: 0

// 提示:
// 1 <= nums.length <= 104 
// -104 <= nums[i] <= 104 
// nums 为无重复元素的升序排列数组 
// -104 <= target <= 104 
// 
// Related Topics 数组 二分查找 
// 👍 1217 👎 0

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SearchInsertPosition{
    public static void main(String[] args) {
        Solution solution = new SearchInsertPosition().new Solution();
        Random random = new Random();
        boolean flag = true;
        // 对比结果10000次
        for (int i = 0; i < 100000; i++) {
            int[] array = generateRandomArray(random);
            int target = random.nextInt(209) - 104;
            // 比对结果
            if (search(array, target) != solution.searchInsert(array, target)) {
                flag = false;
                System.out.println("测试出错！");
                System.out.println("target: " + target);
                System.out.println("nums: " + Arrays.toString(array));
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
        int[] array = new int[random.nextInt(104) + 1];
        Set<Integer> set = new HashSet<>();
        for (int j = 0; j < array.length; j++) {
            int x = random.nextInt(209) - 104;
            if (set.contains(x)) {
                j--;
            } else {
                array[j] = x;
                set.add(x);
            }
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
    public static int search(int[] nums, int target) {
        if (target < nums[0]) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            } else if (i < nums.length - 1 && target > nums[i] && target < nums[i + 1]) {
                return i + 1;
            }
        }
        return nums.length;
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            // 防止整数溢出int的范围
            int mid = ((right - left) >> 1) + left;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
