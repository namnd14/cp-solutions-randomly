package practice.leetcodeproblem;

public class LeetCodeProblem {
    public static void leetcode2490() {
        System.out.println(isCircularSentence("Leetcode eisc cooL"));
    }

    public static boolean isCircularSentence(String sentence) {
        String[] words = sentence.split(" ");
        if (words.length == 1) {
            return words[0].charAt(0) == words[0].charAt(words[0].length() - 1);
        }

        if (words[0].charAt(0) != words[words.length - 1].charAt(words[words.length - 1].length() - 1)) {
            return false;
        }

        int index = 0;
        while (index < words.length - 1) {
            if (words[index].charAt(words[index].length() - 1) != words[index + 1].charAt(0)) {
                return false;
            }
            index++;
        }
        return true;
    }
}
