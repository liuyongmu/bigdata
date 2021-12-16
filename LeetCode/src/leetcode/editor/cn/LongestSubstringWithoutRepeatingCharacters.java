// 3、无重复字符的最长子串
//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 示例 1:
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
// 示例 2:
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
// 示例 3:
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
// 示例 4:
//输入: s = ""
//输出: 0
//
// 提示：
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 6583 👎 0

package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solution.lengthOfLongestSubstring("bbbbb"));
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
        System.out.println(solution.lengthOfLongestSubstring(""));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 滑动窗口
     * @param s 字符串
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 特殊情况去除后，结果最少也是1，定义ans等于1
        int ans = 1;
        // 定义窗口的左右边界
        int left = 0, right = 0;
        // set确保窗口内没有重复的字符
        Set<Character> set = new HashSet<>();
        while (right < s.length()) {
            // 如果set已经包含窗口右边界的字符，则窗口的左边界需要右移，且set需要移出left所指过的字符
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            // 窗口左右边界一样或者左右边界不等时，更新ans，并将right所指的字符添加进set
            if (left == right || s.charAt(left) != s.charAt(right)) {
                set.add(s.charAt(right));
                ans = Math.max(ans, right - left + 1);
                right++;
            } else {
                // 不满足条件则窗口左边界右移
                left++;
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}