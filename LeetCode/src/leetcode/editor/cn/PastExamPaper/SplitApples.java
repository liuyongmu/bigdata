package leetcode.editor.cn.PastExamPaper;

/*
* 有m个苹果，苹果都是一样的无差别，有n个盘子，盘子也都是一样的无差别
* 现在将m个苹果装在n个盘子里，求有多少种摆放方式。
* 如5个苹果放在3个盘子里：3 1 1 、1 3 1、1 1 3被认为是同一种摆放方式
* */

public class SplitApples {
    /**
     * 暴力递归
     * @param apples 苹果数
     * @param plates 盘子数
     * @return 摆放方式数量
     */
    public static int ways(int apples, int plates) {
        // 如果没有苹果，那就是一种摆放方式
        if (apples <= 0) {
            return 1;
        }
        // 如果没有盘子，那么就不能摆放苹果，0中方式
        if (plates <= 0) {
            return 0;
        }
        // 如果盘子数大于了苹果数，那么把多余的盘子敲碎，因为本题需要的是一个数据的分布，
        // 盘子数大于苹果数的结果和盘子数等于苹果数的结果是一样的
        if (plates > apples) {
            return ways(apples, apples);
        }
        // 此时苹果数大于等于盘子数，分两种情况，第一种使用全部的盘子摆放苹果，第二种不使用全部盘子摆放苹果
        // 第一种情况下，在苹果数大于等于盘子数以及使用全部盘子摆放苹果的限制下，每个盘子是至少放一个的
        int a = ways(apples - plates, plates);
        // 第二种情况下不使用全部盘子装苹果，盘子数减一去跑递归。递归后续也是一样的逻辑
        int b = ways(apples, plates - 1);
        return a + b;
    }

    /**
     * 动态规划，直接根据暴力递归变更
     * @param apples 苹果数
     * @param plates 盘子数
     * @return 可以摆放的方式数
     */
    public static int dpWays(int apples, int plates) {
        if (apples <= 0) {
            return 1;
        }
        if (plates <= 0) {
            return 0;
        }
        int[][] dp = new int[apples + 1][plates + 1];
        // 初始条件，苹果数为0时，盘子数不管多少结果都是1，盘子数为0时苹果数不管多少结果都是0，
        // 不过数组初始化默认值就是0，可以不用手动添加条件
        for (int i = 0; i <= plates; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i <= apples; i++) {
            for (int j = 1; j <= plates; j++) {
                if (j > i) {
                    // 盘子数大于苹果数时结果都是和盘子数等于苹果数时一样的
                    dp[i][j] = dp[i][i];
                } else {
                    // 苹果数大于盘子数时分两种情况
                    // 第一种情况时使用全部盘子，第二种情况时不使用全部盘子，两种情况的结果相加
                    dp[i][j] = dp[i - j][j] + dp[i][j - 1];
                }
            }
        }
        return dp[apples][plates];
    }

    public static void main(String[] args) {
        boolean succeed = true;
        int testTime = 100;
        for (int i = 0; i < testTime; i++) {
            int apples = (int) (Math.random() * 100);
            int plates = (int) (Math.random() * 100);
            int ans1 = ways(apples, plates);
            int ans2 = dpWays(apples, plates);
            if (ans1 != ans2) {
                succeed = false;
                System.out.println("ans1: " + ans1);
                System.out.println("ans2: " + ans2);
                System.out.println("apples: " + apples + ", " + "plates: " + plates);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked");
    }
}
