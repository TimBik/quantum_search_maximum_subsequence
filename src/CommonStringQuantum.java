import java.util.TreeMap;

public class CommonStringQuantum {
    static int m;
    static String[] allWords;
    static int lMaxDistance;
    static int maxDistance;

    static String maxCommonString;

    static SameCharacters[] sameCharacters;

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
        FindMaxSubsegment findMaxSubsegment = new FindMaxSubsegment(sameCharacters);
        int[] lr = findMaxSubsegment.findMaxSubsegment();
        lMaxDistance = lr[0];
        maxDistance = lr[1] - lr[0];
        maxCommonString = allWords[0].substring(lr[0], lr[1]);
    }


    private static void preprocessing() {
        m = allWords[0].length();
        for (int i = 1; i < allWords.length; i++) {
            m = Math.min(allWords[i].length(), m);
        }
        sameCharacters = new SameCharacters[m];
        for (int i = 0; i < m; i++) {
            sameCharacters[i] = new SameCharacters(i, allWords);
        }

    }

    private static void readData() {
        allWords = new String[]{"111111", "11111122222", "11111133333", "112111", "211111232","abs111ser"};
    }
}

class SameCharacters implements Correct {
    int id;
    String[] allWords;

    SameCharacters(int id, String[] allWords) {
        this.id = id;
        this.allWords = allWords;
    }

    @Override
    public int isCorrect() {
        for (int i = 1; i < allWords.length; i++) {
            if (allWords[0].charAt(id) != allWords[i].charAt(id)) {
                return 1;
            }
        }
        return 0;
    }
}