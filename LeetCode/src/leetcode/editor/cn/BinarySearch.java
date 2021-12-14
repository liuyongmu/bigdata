package leetcode.editor.cn;

// 704 二分查找
//给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否
//则返回 -1。 

//示例 1:
// 输入: nums = [-1,0,3,5,9,12], target = 9
//输出: 4
//解释: 9 出现在 nums 中并且下标为 4

// 示例 2:
// 输入: nums = [-1,0,3,5,9,12], target = 2
//输出: -1
//解释: 2 不存在 nums 中因此返回 -1

// 提示：
// 你可以假设 nums 中的所有元素是不重复的。 
// n 将在 [1, 10000]之间。 
// nums 的每个元素都将在 [-9999, 9999]之间。 
// 
// Related Topics 数组 二分查找 
// 👍 531 👎 0

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BinarySearch{
    public static void main(String[] args) {
        Solution solution = new BinarySearch().new Solution();
        Random random = new Random();
        boolean flag = true;
        // 对比结果10000次
        for (int i = 0; i < 10000; i++) {
            int[] array = generateRandomArray(random);
            int target = random.nextInt(19999) - 9999;
            // 对数
            if (search(array, target) != solution.search(array, target)) {
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
        int[] array = new int[random.nextInt(10000) + 1];
        Set<Integer> set = new HashSet<>();
        for (int j = 0; j < array.length; j++) {
            int x = random.nextInt(19999) - 9999;
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
        if (nums == null || nums.length == 0) {
            return -1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
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
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
