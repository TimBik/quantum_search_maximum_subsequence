package quantum_search_submax_lib.alg;

import quantum_search_submax_lib.util.Correct;

import java.util.Random;

class GroverAlgorithm {
    static int FindAnyCorrectIndex(Correct correct, int lId, int rId) {
        Random random = new Random();
        int sqrtN = (int) Math.sqrt(rId - lId + 1);
        int rand = -1;
        for (int i = 0; i < sqrtN; i++) {
            rand = random.nextInt(lId, rId);
            if (correct.isCorrect(rand) == 1) {
                return rand;
            }
        }
        return rand;
    }
}
