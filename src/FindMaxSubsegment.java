import java.util.Random;

public class FindMaxSubsegment {
    private static Correct[] data;
    private static int logLengthData;

    public static Segment findMaxSubsegment(Correct[] mas) {
        data = mas;
        logLengthData = (int) (Math.log(data.length) + 1);
        if (findСertainLengthSegment(1) == null) {
            return null;
        }
        int l = 1;
        int r = data.length;
        while (l + 1 < r) {
            int d = (l + r) / 2;
            Segment segment = findСertainLengthSegment(d);
            if (segment != null) {
                l = d;
            } else {
                r = l;
            }
        }
        return findСertainLengthSegment(l);
    }

    private static Segment findСertainLengthSegment(int d) {
        Random random = new Random();
        //с оптимизацией Amplitude Amplification
        //t должен сократится до sqrt(3 * data.length / d)
        int t = 3 * data.length / d;
        for (int i = 0; i < t; i++) {
            int randIndex = random.nextInt(0, data.length);
            if (data[randIndex].isCorrect() == 0) {
                int l = findLeftSuitableWithBorder(randIndex, d);
                int r = findRightSuitableWithBorder(randIndex, d - randIndex + l);
                if (r - l >= d) {
                    return new Segment(l, r + 1);
                }
            }
        }
        return null;
    }

    private static int findRightSuitableWithBorder(int ind, int d) {
        int borderLength = 1;
        int correct = FindCorrectOnSegmentUseGrover(ind, ind + borderLength + 1);
        while (correct == -1) {
            borderLength *= 2;
            correct = FindCorrectOnSegmentUseGrover(ind, ind + borderLength + 1);
        }
        int l = ind + borderLength / 2;
        int r = correct;

        while (l + 1 < r) {
            int mid = (l + r) / 2;
            correct = FindCorrectOnSegmentUseGrover(l, mid + 1);
            if (correct == -1) {
                l = mid;
            } else {
                //correct не больше mid => можно брать его
                r = correct;
            }
        }
        return r;
    }

    private static int FindCorrectOnSegmentUseGrover(int l, int r) {
        //с оптимизацией
        //any должен сократится до O(1)
        int any = logLengthData;
        for (int i = 0; i < any; i++) {
            int id = GroverAlgorithm.FindAnyCorrectIndex(data, l, r);
            if (data[id].isCorrect() == 1) {
                return id;
            }
        }
        return -1;
    }

    private static int findLeftSuitableWithBorder(int ind, int d) {
        int borderLength = 1;
        int correct = FindCorrectOnSegmentUseGrover(ind - borderLength, ind);
        while (correct == -1) {
            borderLength *= 2;
            correct = FindCorrectOnSegmentUseGrover(ind - borderLength, ind);
        }
        int l = correct;
        int r = ind - borderLength / 2;

        while (l + 1 < r) {
            int mid = (l + r) / 2;
            correct = FindCorrectOnSegmentUseGrover(mid, r);
            if (correct == -1) {
                r = mid;
            } else {
                //correct не больше mid => можно брать его
                l = correct;
            }
        }
        return l;
    }
}

//Что то вроде Гровера
class GroverAlgorithm {
    static int FindAnyCorrectIndex(Correct[] corrects, int lId, int rId) {
        return 0;
    }

}

class Segment {
    int l;
    int r;

    public Segment(int l, int r) {
        this.l = l;
        this.r = r;
    }
}

//описание функции возвращающая 0 или 1
interface Correct {
    int isCorrect();
}



