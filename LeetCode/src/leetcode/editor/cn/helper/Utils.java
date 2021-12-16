package leetcode.editor.cn.helper;

import java.util.Random;

public class Utils {
    private Random random = new Random();

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
}
