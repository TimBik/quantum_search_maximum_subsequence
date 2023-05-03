package quantum_search_submax_lib.alg;

import quantum_search_submax_lib.util.ConvertRelevantToCorrect;
import quantum_search_submax_lib.util.Correct;
import quantum_search_submax_lib.util.Relevant;
/**
 * Класс FindLeftSuitableWithBorder содержит методы для поиска ближайшего подходящего элемента
 * слева от индексом ind, удалённого не более, чем на d
 */
public class FindLeftSuitableWithBorder {
    /**
     * Метод findLeftSuitableWithBorder ищет левый ближайший подходящий элемент в массиве data
     * слева от индекса ind и удалённого не более, чем на d, используя алгоритм квантовой оптимизации Grover's algorithm.
     *
     * @param data массив данных типа Correct, в котором ищется левая граница подходящего отрезка
     * @param ind индекс в массиве data, с которого начинается поиск эелемента слева
     * @param d   длина исследуемого отрезка
     * @return ближайшего подходящего элемента из отрезка в массиве data от индексом ind и длиной d
     */
    public int findLeftSuitableWithBorder(Correct data, int ind, int d) {
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
    /**
     * Метод findLeftSuitableWithBorder ищет левую границу подходящего отрезка в массиве data
     * с индексом ind и длиной d, используя алгоритм квантовой оптимизации Grover's algorithm.
     *
     * @param relevantData массив данных типа Relevant, который будет преобразован в массив типа Correct
     * @param ind          индекс в массиве data, с которого начинается поиск левой границы
     * @param d            длина искомого подходящего отрезка
     * @return левую границу подходящего отрезка в массиве data с индексом ind и длиной d
     */
    public int findLeftSuitableWithBorder(Relevant relevantData, int ind, int d) {
        Correct data = ConvertRelevantToCorrect.convertToCorrect(relevantData);
        return findLeftSuitableWithBorder(data, ind, d);
    }
}
