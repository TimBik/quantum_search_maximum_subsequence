package quantum_search_submax_lib;

public class ConvererRelevantToCorrect {

    protected Correct[] convertToCorrect(Relevant[] relevantData) {
        Correct[] data = new Correct[relevantData.length];
        for (int i = 0; i < relevantData.length; i++) {
            int finalI = i;
            data[i] = () -> relevantData[finalI].isRelevant() ? 1 : 0;
        }
        return data;
    }
}
