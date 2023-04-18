package quantum_search_submax_lib.alg;

import quantum_search_submax_lib.util.Correct;

public class FindCorrectOnSegmentUseGrover {
    protected int findCorrectOnSegmentUseGrover(Correct data, int l, int r) {
        //с оптимизацией
        //any должен сократится до O(1)
        int any = 100;
        for (int i = 0; i < any; i++) {
            int id = GroverAlgorithm.FindAnyCorrectIndex(data, l, r);
            if (data.isCorrect(id) == 1) {
                return id;
            }
        }
        return -1;
    }
}
