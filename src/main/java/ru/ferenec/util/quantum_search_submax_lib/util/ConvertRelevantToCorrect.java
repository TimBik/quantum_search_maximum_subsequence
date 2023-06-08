package ru.ferenec.util.quantum_search_submax_lib.util;
/**
 * Этот класс предоставляет статический метод для преобразования объекта, реализующего интерфейс Relevant, в объект, реализующий интерфейс Correct.
 */
public class ConvertRelevantToCorrect {
    /**
     * Преобразует объект, реализующий интерфейс Relevant, в объект, реализующий интерфейс Correct.
     *
     * @param relevantData объект, реализующий интерфейс Relevant
     * @return объект, реализующий интерфейс Correct
     */
    public static Correct convertToCorrect(Relevant relevantData) {
        return new Correct() {
            @Override
            public int isCorrect(int id) {
                return relevantData.isRelevant(id) ? 1 : 0;
            }

            @Override
            public int numberOfLastElement() {
                return relevantData.numberOfLastElement();
            }
        };
    }
}
