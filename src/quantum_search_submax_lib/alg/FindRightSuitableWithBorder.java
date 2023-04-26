package quantum_search_submax_lib.alg;

import quantum_search_submax_lib.util.ConvertRelevantToCorrect;
import quantum_search_submax_lib.util.Correct;
import quantum_search_submax_lib.util.Relevant;

/**
 * Класс, содержащий реализацию функции для поиска левого границы диапазона с
 * правильными элементами, находящимися на расстоянии не более чем d от данного
 * индекса.
 */
public class FindRightSuitableWithBorder {
    /**
     * Функция для поиска левой границы диапазона с правильными элементами на расстоянии
     * не более чем d от данного индекса в объекте класса Correct.
     *
     * @param data объект класса Correct, содержащий массив данных и метод isCorrect(int),
     *             который принимает на вход индекс и возвращает 1, если элемент с данным
     *             индексом является правильным, и 0 в противном случае.
     * @param ind  индекс элемента, от которого ищется левая граница диапазона.
     * @param d    максимальное расстояние между индексом и правильным элементом в диапазоне.
     * @return левая граница диапазона с правильными элементами на расстоянии не более
     * чем d от данного индекса, или -1, если такая граница не найдена.
     */
    public int findRightSuitableWithBorder(Correct data, int ind, int d) {
        int borderLength = 1;
        if (ind == data.lastIndex() - 1) {
            return ind + 1;
        }
        FindCorrectOnSegmentUseGrover findSegment = new FindCorrectOnSegmentUseGrover();
        int correct = findSegment.findCorrectOnSegmentUseGrover(data, ind, ind + borderLength + 1);
        while (correct == -1) {
            if (borderLength >= d) {
                return ind + borderLength + 1;
            }
            borderLength = Math.min(borderLength * 2, d);
            if (ind + borderLength + 1 > data.lastIndex()) {
                return data.lastIndex();
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

    /**
     * Функция для поиска левой границы диапазона с правильными элементами на расстоянии
     * не более чем d от данного индекса в объекте класса Relevant.
     *
     * @param relevantData объект класса Relevant, содержащий массив данных и метод
     *                     isRelevant(int), который принимает на вход индекс и возвращает
     *                     true, если элемент подходящий
     * @param ind          индекс, от которого производится поиск
     * @param d            граница для поиска
     * @return самый правый подходящий индекс в заданном диапазоне с границей
     **/
    public int findRightSuitableWithBorder(Relevant relevantData, int ind, int d) {
        Correct data = ConvertRelevantToCorrect.convertToCorrect(relevantData);
        return findRightSuitableWithBorder(data, ind, d);
    }
}
