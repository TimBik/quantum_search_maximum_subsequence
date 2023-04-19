package quantum_search_submax_lib;

public class FindRightSuitableWithBorder {
    public int findRightSuitableWithBorder(Relevant[] relevantData, int ind, int d) {
        Correct[] data = new ConvererRelevantToCorrect().convertToCorrect(relevantData);
        int borderLength = 1;
        if (ind == data.length - 1) {
            return ind + 1;
        }
        FindCorrectOnSegmentUseGrover findSegment = new FindCorrectOnSegmentUseGrover();
        int correct = findSegment.findCorrectOnSegmentUseGrover(data, ind, ind + borderLength + 1);
        while (correct == -1) {
            if (borderLength >= d) {
                return ind + borderLength + 1;
            }
            borderLength = Math.min(borderLength * 2, d);
            if (ind + borderLength + 1 > data.length) {
                return data.length;
            }
            correct = findSegment.findCorrectOnSegmentUseGrover(data, ind, ind + borderLength + 1);
        }
        int l = ind + borderLength / 2;
        int r = correct;

        while (l + 1 < r) {
            int mid = (l + r) / 2;
            correct = findSegment.findCorrectOnSegmentUseGrover(data, l, mid + 1);
            if (correct == -1) {
                l = mid;
            } else {
                //correct не больше mid => можно брать его
                r = correct;
            }
        }
        return r;
    }
}
