package example_tasks;

import java.util.Arrays;
import java.util.TreeSet;

public class GoodText {
    static String[] allWords;
    static String[] keyWords;
    static TreeSet<String> keyWordsBinaryTree;
    static int maxDistance;
    static int startIndexKeyWord;
    static int endIndexKeyWord;
    static int t;

    public static void main(String[] args) {
        readData();
        preprocessing();
        findDistances();
        printingResult();
    }

    private static void printingResult() {
        int distances = endIndexKeyWord - startIndexKeyWord + 1 - keyWords.length;
        double result = maxDistance - (double) distances / (keyWords.length - 1);
        System.out.println("Max distance is: " + maxDistance);
        System.out.println("all distance between start and end is: " + distances);
        if (result < 1) {
            System.out.println("is a Good Text");
        } else if (result <= t) {
            System.out.println("is a nearly Good Text");
        } else {
            System.out.println("is a Bad Text");
        }
    }

    private static void findDistances() {
        int distance = Integer.MIN_VALUE;
        startIndexKeyWord = -1;
        for (int i = 0; i < allWords.length; i++) {
            String word = allWords[i];
            if (keyWordsBinaryTree.contains(word)) {
                keyWordsBinaryTree.remove(word);
                if (startIndexKeyWord == -1) {
                    startIndexKeyWord = i;
                }
                maxDistance = Math.max(distance, maxDistance);
                endIndexKeyWord = i;
                distance = 0;
            } else {
                distance++;
            }
        }
    }

    private static void preprocessing() {
        keyWordsBinaryTree = new TreeSet<>();
        keyWordsBinaryTree.addAll(Arrays.asList(keyWords));
    }

    private static void readData() {
        allWords = new String[]{"way", "diploma", "word", "dance", "finding", "max", "pain", "create", "love", "is", "simple", "save"};
        keyWords = new String[]{"word", "create", "save"};
        t = 1;
    }
}
