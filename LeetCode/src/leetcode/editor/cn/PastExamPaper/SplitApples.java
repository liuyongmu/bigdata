package leetcode.editor.cn.PastExamPaper;

/*
* 有m个苹果，苹果都是一样的无差别，有n个盘子，盘子也都是一样的无差别
* 现在将m个苹果装在n个盘子里，求有多少种摆放方式。
* 如5个苹果放在3个盘子里：3 1 1 、1 3 1、1 1 3被认为是同一种摆放方式
* */

public class SplitApples {
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

    public static void main(String[] args) {
        System.out.println(ways(5, 4));
    }
}
