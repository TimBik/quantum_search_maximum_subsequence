package quantum_search_submax_lib;

import java.util.Random;

public class FindMaxSubsegment {

    public int[] findMaxSubsegment(Relevant[] relevantData) {
        Correct[] data = new ConvererRelevantToCorrect().convertToCorrect(relevantData);
        int maxAns = 0;
        int[] maxSegment = null;
        int t = 10;
        for (int j = 0; j < t; j++) {
            int l = 0;
            int r = data.length;
            int u = 1;
            int[] nowMaxSegment = null;
            while (l + 1 < r) {
                int d = (l + r) / 2;
                int[] segment = findСertainLengthSegment(relevantData, d);
                for (int i = 0; i < u - 1; i++) {
                    if (segment != null) break;
                    segment = findСertainLengthSegment(relevantData, d);
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



    private int[] findСertainLengthSegment(Relevant[] relevantData, int d) {
        Correct[] data = new ConvererRelevantToCorrect().convertToCorrect(relevantData);
        Random random = new Random();
        //с квантовой оптимизацией Amplitude Amplification
        //t доkлжен сократится до sqrt(3 * data.length / d)
        //сейчас же высока вероятность не найти элемент
        int t = (int) Math.sqrt((double) 3 * data.length / d + 1);
        int u = 1;
        FindLeftSuitableWithBorder findLeft = new FindLeftSuitableWithBorder();
        FindRightSuitableWithBorder findRight = new FindRightSuitableWithBorder();
        for (int i = 0; i < t; i++) {
            int randIndex = random.nextInt(0, data.length);
            if (data[randIndex].isCorrect() == 0) {
                for (int j = 0; j < u; j++) {
                    int l = findLeft.findLeftSuitableWithBorder(relevantData, randIndex, d) + 1;
                    int r = findRight.findRightSuitableWithBorder(relevantData, randIndex, d - randIndex + l - 1) - 1;
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
