package quantum_search_submax_lib.util;

public class ConvertRelevantToCorrect {

    public static Correct convertToCorrect(Relevant relevantData) {
        return new Correct() {
            @Override
            public int isCorrect(int id) {
                return relevantData.isRelevant(id) ? 1 : 0;
            }

            @Override
            public int size() {
                return relevantData.size();
            }
        };
    }
}
