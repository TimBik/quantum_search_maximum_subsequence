package quantum_search_submax_lib;

import java.util.Random;

class GroverAlgorithm {
    static int FindAnyCorrectIndex(Correct[] corrects, int lId, int rId) {
        Random random = new Random();
        int sqrtN = (int) Math.sqrt(rId - lId + 1);
        int rand = -1;
        for (int i = 0; i < sqrtN; i++) {
            rand = random.nextInt(lId, rId);
            if (corrects[rand].isCorrect() == 1) {
                return rand;
            }
        }
        return rand;
    }
}
