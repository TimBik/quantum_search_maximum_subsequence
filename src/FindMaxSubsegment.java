import java.util.Random;

public class FindMaxSubsegment {
    private final Correct[] data;

    FindMaxSubsegment(Correct[] data) {
        this.data = data;
    }

    public int[] findMaxSubsegment() {
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
                int[] segment = findСertainLengthSegment(d);
                for (int i = 0; i < u - 1; i++) {
                    if (segment != null) break;
                    segment = findСertainLengthSegment(d);
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

    private int[] findСertainLengthSegment(int d) {
        Random random = new Random();
        //с квантовой оптимизацией Amplitude Amplification
        //t должен сократится до sqrt(3 * data.length / d)
        //сейчас же высока вероятность не найти элемент
        int t = (int) Math.sqrt((double) 3 * data.length / d + 1);
        int u = 1;
        for (int i = 0; i < t; i++) {
            int randIndex = random.nextInt(0, data.length);
            if (data[randIndex].isCorrect() == 0) {
                for (int j = 0; j < u; j++) {
                    int l = findLeftSuitableWithBorder(randIndex, d) + 1;
                    int r = findRightSuitableWithBorder(randIndex, d - randIndex + l - 1) - 1;
                    if (r - l + 1 >= d) {
                        return new int[]{l, r + 1};
                    }
                }
                u++;
            }

        }
        return null;
    }

    public int findRightSuitableWithBorder(int ind, int d) {
        int borderLength = 1;
        if (ind == data.length - 1) {
            return ind + 1;
        }
        int correct = FindCorrectOnSegmentUseGrover(ind, ind + borderLength + 1);
        while (correct == -1) {
            if (borderLength >= d) {
                return ind + borderLength + 1;
            }
            borderLength = Math.min(borderLength * 2, d);
            if (ind + borderLength + 1 > data.length) {
                return data.length;
            }
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

    private int FindCorrectOnSegmentUseGrover(int l, int r) {
        //с оптимизацией
        //any должен сократится до O(1)
        int any = 100;
        for (int i = 0; i < any; i++) {
            int id = GroverAlgorithm.FindAnyCorrectIndex(data, l, r);
            if (data[id].isCorrect() == 1) {
                return id;
            }
        }
        return -1;
    }

    public int findLeftSuitableWithBorder(int ind, int d) {
        int borderLength = 1;
        if (ind == 0) return -1;
        int correct = FindCorrectOnSegmentUseGrover(ind - borderLength, ind);
        while (correct == -1) {
            if (borderLength + 1 >= d) {
                return ind - borderLength - 1;
            }
            borderLength = Math.min(borderLength * 2, d);
            if (ind - borderLength < 0) {
                return -1;
            }
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

// Алгоритм Гровера
// однако вероятность ошибки выше
// для уменьшения слдеуюет использовать
// квантовый компютер
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

//описание функции возвращающая 0 или 1
interface Correct {
    int isCorrect();
}



