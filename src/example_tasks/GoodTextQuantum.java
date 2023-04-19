package example_tasks;

import quantum_search_submax_lib.FindLeftSuitableWithBorder;
import quantum_search_submax_lib.FindMaxSubsegment;
import quantum_search_submax_lib.FindRightSuitableWithBorder;
import quantum_search_submax_lib.Relevant;

import java.util.Arrays;
import java.util.TreeSet;

public class GoodTextQuantum {
    static String[] allWords;
    static String[] keyWords;
    static TreeSet<String> keyWordsBinaryTree;

    static CheckKeyWord[] checkKeyWords;
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
        FindMaxSubsegment findMaxSubsegment = new FindMaxSubsegment();
        FindLeftSuitableWithBorder findLeft = new FindLeftSuitableWithBorder();
        FindRightSuitableWithBorder findRight = new FindRightSuitableWithBorder();
        int[] lr = findMaxSubsegment.findMaxSubsegment(checkKeyWords);
        maxDistance = lr[1] - lr[0];
        endIndexKeyWord = findLeft.findLeftSuitableWithBorder(checkKeyWords, allWords.length, allWords.length);
        startIndexKeyWord = findRight.findRightSuitableWithBorder(checkKeyWords, 0, allWords.length);
    }

    private static void preprocessing() {
        keyWordsBinaryTree = new TreeSet<>();
        keyWordsBinaryTree.addAll(Arrays.asList(keyWords));
        checkKeyWords = new CheckKeyWord[allWords.length];
        for (int i = 0; i < allWords.length; i++) {
            checkKeyWords[i] = new CheckKeyWord(i, allWords, keyWordsBinaryTree);
        }
    }

    private static void readData() {
        allWords = new String[]{"way", "diploma", "word", "dance", "finding", "max", "pain", "create", "love", "is", "simple", "save"};
        keyWords = new String[]{"word", "create", "save"};
        t = 1;
    }
}


class CheckKeyWord implements Relevant {
    int id;
    String[] allWords;

    TreeSet<String> keyWordsBinaryTree;

    CheckKeyWord(int id, String[] allWords, TreeSet<String> keyWordsBinaryTree) {
        this.id = id;
        this.allWords = allWords;
        this.keyWordsBinaryTree = keyWordsBinaryTree;
    }

    @Override
    public boolean isRelevant() {
        if (keyWordsBinaryTree.contains(allWords[id])) return true;
        return false;
    }
}