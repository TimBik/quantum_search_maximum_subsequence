public class CommonString {

    static int m;
    static String[] allWords;
    static int lMaxDistance;
    static int maxDistance;

    static String maxCommonString;

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
        lMaxDistance = -1;
        maxDistance = 0;
        int lNow = -1;
        int rNow = -2;
        maxCommonString = "";
        for (int i = 0; i < m; i++) {
            boolean common = true;
            char commonCharacter = allWords[0].charAt(i);
            for (int j = 1; j < allWords.length; j++) {
                if (allWords[j].charAt(i) != commonCharacter) {
                    common = false;
                    break;
                }
            }
            if (common) {
                if (lNow == -1) {
                    lNow = i;
                }
                rNow = i;
            } else {
                lNow = -1;
                rNow = -2;
            }
            int distance = rNow - lNow + 1;
            if (maxDistance < distance) {
                maxDistance = distance;
                lMaxDistance = lNow;
            }
        }

        if (maxDistance != 0) {
            maxCommonString = allWords[0].substring(lMaxDistance,
                    lMaxDistance + maxDistance);
        }
    }


    private static void preprocessing() {
        m = allWords[0].length();
        for (int i = 1; i < allWords.length; i++) {
            m = Math.min(allWords[i].length(), m);
        }
    }

    private static void readData() {
        allWords = new String[]{"111111", "11111122222", "11111133333", "112111"};
    }
}
