package example_tasks;

import quantum_search_submax_lib.alg.FindMaxSubsegment;
import quantum_search_submax_lib.util.Relevant;

public class CommonStringQuantum {
    static int m;
    static String[] allWords;
    static int lMaxDistance;
    static int maxDistance;

    static String maxCommonString;

    static SameCharacters sameCharacters;

    public static void main(String[] args) {
        readData();
        preprocessing();
        findMaxCommonString();
        printingResult();
    }

    private static void printingResult() {
        System.out.println("t is: " + maxDistance);
        if (maxDistance == 0) {
            System.out.println("common strings do not exist");
        } else {
            System.out.println("common string is: " + maxCommonString);
            System.out.println("starts at index: " + lMaxDistance);
        }
    }

    private static void findMaxCommonString() {
        FindMaxSubsegment findMaxSubsegment = new FindMaxSubsegment();
        int[] lr = findMaxSubsegment.findMaxSubsegment(sameCharacters);
        lMaxDistance = lr[0];
        maxDistance = lr[1] - lr[0];
        maxCommonString = allWords[0].substring(lr[0], lr[1]);
    }


    private static void preprocessing() {
        m = allWords[0].length();
        for (int i = 1; i < allWords.length; i++) {
            m = Math.min(allWords[i].length(), m);
        }
        sameCharacters = new SameCharacters(allWords);
    }

    private static void readData() {
        allWords = new String[]{"111111", "11111122222", "11111133333", "112111", "211111232", "abs111ser"};
    }
}

class SameCharacters implements Relevant {
    String[] allWords;

    SameCharacters(String[] allWords) {
        this.allWords = allWords;
    }

    @Override
    public boolean isRelevant(int id) {
        for (int i = 1; i < allWords.length; i++) {
            if (allWords[0].charAt(id) != allWords[i].charAt(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return allWords.length;
    }
}