package quantum_search_submax_lib.alg;

import quantum_search_submax_lib.util.ConvertRelevantToCorrect;
import quantum_search_submax_lib.util.Correct;
import quantum_search_submax_lib.util.Relevant;

import java.util.Random;

public class FindMaxSubsegment {

    public int[] findMaxSubsegment(Correct data) {
        int maxAns = 0;
        int[] maxSegment = null;
        int t = 10;
        for (int j = 0; j < t; j++) {
            int l = 0;
            int r = data.size();
            int u = 1;
            int[] nowMaxSegment = null;
            while (l + 1 < r) {
                int d = (l + r) / 2;
                int[] segment = findCertainLengthSegment(data, d);
                for (int i = 0; i < u - 1; i++) {
                    if (segment != null) break;
                    segment = findCertainLengthSegment(data, d);
                }
                if (segment != null) {
                    l = d;
                    nowMaxSegment = segment;
                } else {
                    r = d;
                }
                u++;
            }
            if (maxAns < l) {
                maxAns = l;
                maxSegment = nowMaxSegment;
            }
        }
        return maxSegment;
    }

    public int[] findMaxSubsegment(Relevant relevantData) {
        Correct data = ConvertRelevantToCorrect.convertToCorrect(relevantData);
        return findMaxSubsegment(data);
    }


    private int[] findCertainLengthSegment(Correct data, int d) {
        Random random = new Random();
        //с квантовой оптимизацией Amplitude Amplification
        //t доkлжен сократится до sqrt(3 * data.length / d)
        //сейчас же высока вероятность не найти элемент
        int t = (int) Math.sqrt((double) 3 * data.size() / d + 1);
        int u = 1;
        FindLeftSuitableWithBorder findLeft = new FindLeftSuitableWithBorder();
        FindRightSuitableWithBorder findRight = new FindRightSuitableWithBorder();
        for (int i = 0; i < t; i++) {
            int randIndex = random.nextInt(0, data.size());
            if (data.isCorrect(randIndex) == 0) {
                for (int j = 0; j < u; j++) {
                    int l = findLeft.findLeftSuitableWithBorder(data, randIndex, d) + 1;
                    int r = findRight.findRightSuitableWithBorder(data, randIndex, d - randIndex + l - 1) - 1;
                    if (r - l + 1 >= d) {
                        return new int[]{l, r + 1};
                    }
                }
                u++;
            }

        }
        return null;
    }


}