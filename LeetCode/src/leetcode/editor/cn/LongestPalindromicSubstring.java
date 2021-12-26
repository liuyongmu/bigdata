package leetcode.editor.cn;

// 5、最长回文子串
//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 示例 1：
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
//
// 示例 2：
//输入：s = "cbbd"
//输出："bb"
//
// 示例 3：
//输入：s = "a"
//输出："a"
//
// 示例 4：
//输入：s = "ac"
//输出："a"
//
// 提示：
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 4506 👎 0


public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
        
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int n = s.length();

        return "";
    }

    /**
     * 双层循环暴力检测法
     */
    public String longestPalindrome1(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int n = s.length();
        String ans = "";
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (process(s, i, j) && (j - i + 1) > s.length()) {
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }

    private boolean process(String s, int i, int j) {
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}