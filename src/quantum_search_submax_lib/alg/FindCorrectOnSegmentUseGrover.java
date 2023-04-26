package quantum_search_submax_lib.alg;

import quantum_search_submax_lib.util.Correct;
/**
 * Этот класс предоставляет метод для поиска верного элемента в заданном диапазоне, используя алгоритм Гровера.
 */
public class FindCorrectOnSegmentUseGrover {
    /**
     * Ищет верный элемент в заданном диапазоне, используя алгоритм Гровера.
     *
     * @param data объект, реализующий интерфейс Correct
     * @param l левая граница диапазона поиска
     * @param r правая граница диапазона поиска
     * @return индекс первого найденного верного элемента в диапазоне, или -1, если такого элемента не найдено
     */
    protected int findCorrectOnSegmentUseGrover(Correct data, int l, int r) {
        //с оптимизацией
        //any должен сократится до O(1)
        int any = 100;
        for (int i = 0; i < any; i++) {
            int id = GroverAlgorithm.findAnyCorrectIndex(data, l, r);
            if (data.isCorrect(id) == 1) {
                return id;
            }
        }
        return -1;
    }
}
