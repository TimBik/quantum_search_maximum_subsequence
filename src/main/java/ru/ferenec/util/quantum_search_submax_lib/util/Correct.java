package ru.ferenec.util.quantum_search_submax_lib.util;

/**
 * Экземпляры классов, реализующих этот интерфейс, могут быть преобразованы в логичнский тип.
 */
public interface Correct {
    /**
     * Проверяет, корректен ли элемент с заданным идентификатором.
     *
     * @param id Идентификатор элемента.
     * @return 1, если элемент корректен, 0 - если нет.
     */
    int isCorrect(int id);
    /**
     * Возвращает порядковый номер последнего элементов.
     *
     * @return Порядковый номер последнего элементов.
     */
    int numberOfLastElement();
}