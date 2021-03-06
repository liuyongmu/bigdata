package leetcode.editor.cn.PastExamPaper;

/*
* 给一个数组，找出数组中出现的水王数，如果没有水王数则返回-1。数组中的数字大小在0~99之间
* 水王数的定义为数组中出现次数大于数组长度一半以上的数
* 限制时间复杂度O(n)，空间复杂度O(1)
* */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TheKingOfWaterNumber {
    /**
     * 使用hash表验证
     */
    public static int verify(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        for (int key : map.keySet()) {
            if (map.get(key) > nums.length >> 1) {
                return key;
            }
        }
        return -1;
    }

    /**
     * 每次从数组中同时删除两个不同的数，如果数组存在水王数，那么最后水王数一定会剩下
     * 反之如果还剩下数字没被删除，不一定是水王数，所以还需最后一步验证是否是水王数。
     * 思路：使用两个变量，一个变量为候选初始值为空，一个变量为血条初始值为0，遍历数组，
     * 如果候选为空，则把当前的数赋值给候选，血条加一；如果候选不为空且候选不等于当前的
     * 数，则血条减一；反之如果等于当前的数，则血条加一。遍历完数组后血条大于0则代表有数
     * 字没有被删除且候选就是该数字，此时再遍历一遍数组验证候选数字是否是水王数
     * @param nums 数组
     * @return 水王数
     */
    public static int waterKing(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int candidate = Integer.MAX_VALUE;
        int lifeBar = 0;
        for (int x : nums) {
            if (candidate == Integer.MAX_VALUE) {
                candidate = x;
                lifeBar = 1;
            } else if (candidate != x) {
                lifeBar--;
                if (lifeBar == 0) {  // 如果血条等于0了，要把候选设成初始值
                    candidate = Integer.MAX_VALUE;
                }
            } else {
                lifeBar++;
            }
        }
        if (lifeBar == 0) {  // 如果数都被删除完了，说明没有水王数
            return -1;
        }
        int cnt = 0;  // 最后遍历一边验证候选是否是水王数
        for (int x : nums) {
            if (x == candidate) {
                cnt++;
            }
        }
        return cnt > (nums.length >> 1) ? candidate : -1;
    }

    /**
     * 生成随机数组
     */
    public static int[] generateRandomArray() {
        int n = (int)(Math.random() * 100) + 1;
        int[] arr = new int[n];
        int num = (int)(Math.random() * 100);
        for (int i = 0; i < n; i++) {
            if ((int)(Math.random() * 10) % 2 == 0) {
                arr[i] = num;
            } else {
                arr[i] = (int)(Math.random() * 100);
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        boolean succeed = true;
        for (int i = 0; i < 100000; i++) {
            int[] nums = generateRandomArray();
            int ans1 = verify(nums);
            int ans2 = waterKing(nums);
            if (ans1 != ans2) {
                succeed = false;
                System.out.println("ans1: " + ans1);
                System.out.println("ans2: " + ans2);
                System.out.println(Arrays.toString(nums));
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked");
    }
}
