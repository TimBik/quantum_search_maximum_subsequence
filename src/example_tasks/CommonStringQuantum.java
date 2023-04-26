package example_tasks;

import quantum_search_submax_lib.alg.FindMaxSubsegment;
import quantum_search_submax_lib.util.Relevant;
/**
 * Класс для поиска максимальной общей строки в массиве строк.
 */
public class CommonStringQuantum {
    static int m; // число символов в общей подстроке
    static String[] allWords; // массив слов
    static int lMaxDistance; // начальный индекс максимальной общей строки
    static int maxDistance; // длина максимальной общей строки

    static String maxCommonString; // сама максимальная общая строка

    static SameCharacters sameCharacters; // класс для проверки совпадения символов

    /**
     * Главный метод программы.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        readData(); // чтение данных
        preprocessing(); // предварительная обработка
        findMaxCommonString(); // поиск максимальной общей строки
        printingResult(); // вывод результата
    }

    /**
     * Главный метод программы.
     */
    private static void printingResult() {
        System.out.println("t is: " + maxDistance); // вывод длины максимальной общей строки
        if (maxDistance == 0) {
            System.out.println("common strings do not exist"); // если общих строк нет
        } else {
            System.out.println("common string is: " + maxCommonString); // если общая строка есть
            System.out.println("starts at index: " + lMaxDistance); // вывод начального индекса максимальной общей строки
        }
    }

    /**
     * Метод для поиска максимальной общей строки.
     */
    private static void findMaxCommonString() {
        FindMaxSubsegment findMaxSubsegment = new FindMaxSubsegment();
        int[] lr = findMaxSubsegment.findMaxSubsegment(sameCharacters); // поиск максимальной общей строки
        lMaxDistance = lr[0]; // сохранение начального индекса
        maxDistance = lr[1] - lr[0]; // сохранение длины максимальной общей строки
        maxCommonString = allWords[0].substring(lr[0], lr[1]); // сохранение максимальной общей строки
    }

    /**
     * Метод для предварительной обработки данных.
     */
    private static void preprocessing() {
        m = allWords[0].length();
        for (int i = 1; i < allWords.length; i++) {
            m = Math.min(allWords[i].length(), m); // вычисление минимальной длины слова
        }
        sameCharacters = new SameCharacters(allWords); // инициализация класса для SameCharacters
    }

    /**
     * Метод readData используется для чтения входных данных в массив строк allWords,
     * который будет использоваться для поиска общей строки.
     */
    private static void readData() {
        allWords = new String[]{"111111", "11111122222", "11111133333", "112111", "211111232", "abs111ser"};
    }
}
/**
 * Класс SameCharacters, реализующий интерфейс Relevant, предназначен для определения совпадающих символов
 * в начале строк массива allWords. Используется в классе CommonStringQuantum для предварительной обработки данных.
 */
class SameCharacters implements Relevant {
    String[] allWords;

    /**
     * Конструктор класса SameCharacters. Принимает на вход массив строк allWords и сохраняет его в поле класса.
     *
     * @param allWords массив строк для определения совпадающих символов в начале строк.
     */
    SameCharacters(String[] allWords) {
        this.allWords = allWords;
    }

    /**
     * Метод isRelevant определяет, является ли символ в строке с индексом id важным для определения максимальной общей
     * строки. Для этого проверяется наличие совпадающего символа во всех строках массива allWords, начиная с нулевой.
     * Если символ отличается хотя бы в одной строке, он считается неважным.
     *
     * @param id индекс символа в строке, для которого определяется важность.
     * @return true, если символ важный, false, если символ неважный.
     */
    @Override
    public boolean isRelevant(int id) {
        for (int i = 1; i < allWords.length; i++) {
            if (allWords[0].charAt(id) != allWords[i].charAt(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод lastIndex возвращает количество строк в массиве allWords. Используется для определения конца диапазона
     * индексов, для которых важность символов еще не определена.
     *
     * @return количество строк в массиве allWords.
     */
    @Override
    public int numberOfLastElement() {
        return allWords.length;
    }
}