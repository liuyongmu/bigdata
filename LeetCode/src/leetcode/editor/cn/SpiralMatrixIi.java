package leetcode.editor.cn;

//给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。 
//
// 示例 1：
//输入：n = 3
//输出：[[1,2,3],[8,9,4],[7,6,5]]
//
// 示例 2：
//输入：n = 1
//输出：[[1]]
//
// 提示：
// 1 <= n <= 20 
// 
// Related Topics 数组 矩阵 模拟 
// 👍 545 👎 0

import java.util.Arrays;

public class SpiralMatrixIi {
    public static void main(String[] args) {
        Solution solution = new SpiralMatrixIi().new Solution();
        solution.generateMatrix(5);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        // 用四个变量控制矩形的上下左右四个边界的位置
        int up = 0, low = n - 1, left = 0, right = n - 1, count = 1;
        while (count <= n * n) {
            // 赋值上边界
            for (int x = left; x <= right; x++) {
                ans[up][x] = count++;
            }
            up++;

            // 赋值右边界
            for (int x = up; x <= low; x++) {
                ans[x][right] = count++;
            }
            right--;

            // 赋值下边界
            for (int x = right; x >= left; x--) {
                ans[low][x] = count++;
            }
            low--;

            // 赋值左边界
            for (int x = low; x >= up; x--) {
                ans[x][left] = count++;
            }
            left++;
        }
        /*for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(ans[i]));
        }*/
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}