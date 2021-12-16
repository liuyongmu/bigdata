package leetcode.editor.cn;

// 58、最后一个单词的长度
//给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
// 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。 
//
// 示例 1：
//输入：s = "Hello World"
//输出：5
//
// 示例 2：
//输入：s = "   fly me   to   the moon  "
//输出：4
//
// 示例 3：
//输入：s = "luffy is still joyboy"
//输出：6
//
//
// 提示：
// 1 <= s.length <= 104 
// s 仅有英文字母和空格 ' ' 组成 
// s 中至少存在一个单词 
// 
// Related Topics 字符串 
// 👍 407 👎 0

import leetcode.editor.cn.helper.Utils;

public class LengthOfLastWord {
    public static void main(String[] args) {
        Utils util = new Utils();
        Solution solution = new LengthOfLastWord().new Solution();
        boolean flag = true;
        for (int i = 0; i < 10000; i++) {
            // 生成随机字符串
            String s = util.generateStr(1, 104);
            if (splitMethod(s) != solution.lengthOfLastWord(s)) {
                flag = false;
                System.out.println("测试失败！");
                break;
            }
        }
        if (flag) {
            System.out.println("测试成功！");
        }
    }

    /**
     * 切分空白字符后取数组最后一个元素判断长度
     * @param s
     * @return
     */
    public static int splitMethod(String s) {
        String[] split = s.split("\\s");
        if (split.length == 0) {
            return 0;
        }
        return split[split.length - 1].length();
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int ans = 0;
        // 从字符串右边向左边遍历寻找答案，平均时间复杂度更低
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                // 如果ans是0，说明该字符串是一个或多个空格字符结尾的
                // 当ans不等于0时，说明此时ans已经是最后一个单词的长度了，直接返回
                if (ans != 0) {
                    return ans;
                }
            } else {
                ans++;
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}