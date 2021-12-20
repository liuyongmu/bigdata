package leetcode.editor.cn.helper;

import java.util.Arrays;
import java.util.Random;

public class Utils {
    private final Random random = new Random();

    public String generateStr(int minLen, int maxLen) {
        int n = random.nextInt(maxLen - minLen + 1) + minLen;
        if (n == 0) {
            return "";
        }
        String s = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (random.nextInt(3) == 1) {
                str.append(s.charAt(random.nextInt(26)));
            } else {
                str.append(' ');
            }
        }
        return str.toString();
    }

    /**
     * 生成随机数组
     * @param minSize 数组最小值
     * @param maxSize 数组最大值
     * @param minValue 数组最小长度
     * @param maxValue 数组最大长度
     * @param isSort 是否排序
     * @return 生成数组
     */
    public int[] generateRandomArray(int minSize, int maxSize, int minValue, int maxValue, boolean isSort) {
        int[] arr = new int[randomNumber(minSize, maxSize)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = randomNumber(minValue, maxValue);
        }
        if (isSort) {
            Arrays.sort(arr);
        }
        return arr;
    }

    /**
     * 指定范围随机数
     */
    public int randomNumber(int minValue, int maxValue) {
        return random.nextInt(maxValue - minValue + 1) + minValue;
    }
}
