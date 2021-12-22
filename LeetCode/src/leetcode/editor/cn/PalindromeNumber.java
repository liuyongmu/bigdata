package leetcode.editor.cn;

// 9、回文数
//给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
// 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。 
//
// 示例 1：
//输入：x = 121
//输出：true
//
// 示例 2：
//输入：x = -121
//输出：false
//解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
//
// 示例 3：
//输入：x = 10
//输出：false
//解释：从右向左读, 为 01 。因此它不是一个回文数。
//
// 示例 4：
//输入：x = -101
//输出：false
//
// 提示：
// -2^31 <= x <= 2^31 - 1
//
// 进阶：你能不将整数转为字符串来解决这个问题吗？ 
// Related Topics 数学 
// 👍 1724 👎 0


import java.util.Random;

public class PalindromeNumber {
    public static void main(String[] args) {
        Random random = new Random();
        Solution solution = new PalindromeNumber().new Solution();
        solution.isPalindrome(687717786);
        int testTime = 10000000;
        int minVal = 0, maxVal = Integer.MAX_VALUE;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int x = random.nextInt(maxVal);
            if (solution.isPalindrome(x) != solution.isPalindrome1(x)) {
                succeed = false;
                System.out.println(x);
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || x == Integer.MAX_VALUE) {
            return false;
        }
        int reverse = reverseDigit(x);
        return reverse == x;
    }

    /*
    * 反转数字
    * */
    private int reverseDigit(int x) {
        long ans = 0;
        while (x != 0) {
            int tmp = x % 10;
            x /= 10;
            ans = ans * 10 + tmp;
        }
        if (ans > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int)ans;
    }

    /**
     * 数字转字符串后判读
     * @param x 数字
     * @return 是否是回文数
     */
    public boolean isPalindrome1(int x) {
        if (x < 0) {
            return false;
        }
        String s = String.valueOf(x);
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left <= right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}