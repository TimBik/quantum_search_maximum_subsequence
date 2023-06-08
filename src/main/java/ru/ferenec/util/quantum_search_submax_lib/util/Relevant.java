package ru.ferenec.util.quantum_search_submax_lib.util;

/**
 * Экземпляры классов, реализующих этот интерфейс, могут быть преобразованы в логичнский тип.
 */
public interface Relevant {
    /**
     * Возвращает значение типа boolean в зависимости от того, подходит ли экземпляр класса или нет.
     *
     * @param id идентификатор экземпляра
     * @return true, если экземпляр подходит, false в противном случае
     */
    boolean isRelevant(int id);
    /**
     * Возвращает порядковый номер последнего элементов.
     *
     * @return Порядковый номер последнего элементов.
     */
    int numberOfLastElement();
}