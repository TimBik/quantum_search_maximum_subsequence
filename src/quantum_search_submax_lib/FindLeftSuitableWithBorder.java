package quantum_search_submax_lib;

public class FindLeftSuitableWithBorder {
    public int findLeftSuitableWithBorder(Relevant[] relevantData, int ind, int d) {
        Correct[] data = new ConvererRelevantToCorrect().convertToCorrect(relevantData);
        int borderLength = 1;
        if (ind == 0) return -1;
        FindCorrectOnSegmentUseGrover findSegment = new FindCorrectOnSegmentUseGrover();
        int correct = findSegment.findCorrectOnSegmentUseGrover(data, ind - borderLength, ind);
        while (correct == -1) {
            if (borderLength + 1 >= d) {
                return ind - borderLength - 1;
            }
            borderLength = Math.min(borderLength * 2, d);
            if (ind - borderLength < 0) {
                return -1;
            }
            correct = findSegment.findCorrectOnSegmentUseGrover(data, ind - borderLength, ind);
        }
        int l = correct;
        int r = ind - borderLength / 2;

        while (l + 1 < r) {
            int mid = (l + r) / 2;
            correct = findSegment.findCorrectOnSegmentUseGrover(data, mid, r);
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
