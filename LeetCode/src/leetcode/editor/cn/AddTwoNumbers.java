package leetcode.editor.cn;

//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
// 请你将两个数相加，并以相同形式返回一个表示和的链表。
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例 1：
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
//
// 示例 2：
//输入：l1 = [0], l2 = [0]
//输出：[0]
//
// 示例 3：
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
//
// 提示：
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
// Related Topics 递归 链表 数学 
// 👍 7233 👎 0


import leetcode.editor.cn.helper.ListNode;

import java.util.Random;

public class AddTwoNumbers {
    /**
     * 生成随机链表
     * @param minLen 链表最小长度
     * @param maxLen 链表最大长度
     * @param minVal 节点最小值
     * @param maxVal 节点最大值
     * @return       链表头节点
     */
    public static ListNode generateLinkedList(int minLen, int maxLen, int minVal, int maxVal) {
        Random random = new Random();
        int len = random.nextInt(maxLen - minLen + 1) + minLen;
        if (len <= 0) {
            return null;
        }
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 0; i < len; i++) {
            cur.next = new ListNode(random.nextInt(maxVal - minVal + 1) + minVal);
            cur = cur.next;
        }
        return head.next;
    }

    /**
     * 打印链表
     * @param head 链表头节点
     */
    public static void printLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Solution solution = new AddTwoNumbers().new Solution();
        for (int i = 0; i < 10; i++) {
            ListNode l1 = generateLinkedList(1, 100, 0, 9);
            ListNode l2 = generateLinkedList(1, 100, 0, 9);
            System.out.print("l1:  ");
            printLinkedList(l1);
            System.out.print("l2:  ");
            printLinkedList(l2);
            ListNode ans = solution.addTwoNumbers(l1, l2);
            System.out.print("ans: ");
            printLinkedList(ans);
            System.out.println();
        }
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode cur = ans;
        // sign记录当前两数对应位置的数字相加是否有进位
        int sign = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + sign;
            if (sum >= 10) {
                cur.next = new ListNode(sum - 10);
                sign = 1;
            } else {
                cur.next = new ListNode(sum);
                sign = 0;
            }
            l1 = l1.next;
            l2 = l2.next;
            cur = cur.next;
        }
        l1 = l1 == null ? l2 : l1;
        while (l1 != null) {
            int sum = l1.val + sign;
            if (sum > 9) {
                cur.next = new ListNode(sum - 10);
                sign = 1;
            } else {
                cur.next = new ListNode(sum);
                sign = 0;
            }
            l1 = l1.next;
            cur = cur.next;
        }
        // 最后需要判断一下是否还有进位，如果有进位则添加1
        if (sign == 1) {
            cur.next = new ListNode(1);
        }
        return ans.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}