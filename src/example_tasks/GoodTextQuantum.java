package example_tasks;

import quantum_search_submax_lib.alg.FindLeftSuitableWithBorder;
import quantum_search_submax_lib.alg.FindMaxSubsegment;
import quantum_search_submax_lib.alg.FindRightSuitableWithBorder;
import quantum_search_submax_lib.util.Relevant;

import java.util.Arrays;
import java.util.TreeSet;
/**
 * Класс для проверки качества текста.
 */
public class GoodTextQuantum {
    static String[] allWords; // Массив всех слов
    static String[] keyWords; // Ключевые слова, которые мы ищем в тексте
    static TreeSet<String> keyWordsBinaryTree; // Дерево, содержащее ключевые слова, для быстрого поиска

    static CheckKeyWord checkKeyWords; // Интерфейс для проверки, является ли текущее слово ключевым
    static int maxDistance; // Максимальное расстояние между ключевыми словами
    static int startIndexKeyWord; // Индекс первого встреченного ключевого слова в тексте
    static int endIndexKeyWord; // Индекс последнего встреченного ключевого слова в тексте
    static int t; // Параметр t, для определения качества текста

    /**
     * Точка входа в программу.
     *
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        readData();
        preprocessing();
        findDistances();
        printingResult();
    }

    /**
     * Метод для вывода результата проверки текста на консоль.
     */
    private static void printingResult() {
        int distances = endIndexKeyWord - startIndexKeyWord + 1 - keyWords.length;
        double result = maxDistance - (double) distances / (keyWords.length - 1);
        System.out.println("Max distance is: " + maxDistance);
        System.out.println("all distance between start and end is: " + distances);
        if (result < 1) {
            System.out.println("is a Good Text");
        } else if (result <= t) {
            System.out.println("is a nearly Good Text");
        } else {
            System.out.println("is a Bad Text");
        }
    }

    /**
     * Метод для нахождения расстояний между ключевыми словами и
     * их индексов в тексте.
     */
    private static void findDistances() {
        FindMaxSubsegment findMaxSubsegment = new FindMaxSubsegment();
        FindLeftSuitableWithBorder findLeft = new FindLeftSuitableWithBorder();
        FindRightSuitableWithBorder findRight = new FindRightSuitableWithBorder();
        int[] lr = findMaxSubsegment.findMaxSubsegment(checkKeyWords);
        maxDistance = lr[1] - lr[0];
        endIndexKeyWord = findLeft.findLeftSuitableWithBorder(checkKeyWords, allWords.length, allWords.length);
        startIndexKeyWord = findRight.findRightSuitableWithBorder(checkKeyWords, 0, allWords.length);
    }

    /**
     * Метод для предварительной обработки данных, создания дерева с ключевыми словами.
     */
    private static void preprocessing() {
        keyWordsBinaryTree = new TreeSet<>();
        keyWordsBinaryTree.addAll(Arrays.asList(keyWords));
        checkKeyWords = new CheckKeyWord(allWords, keyWordsBinaryTree);
    }

    /**
     * Метод, выполняющий чтение данных
     */
    private static void readData() {
        allWords = new String[]{"way", "diploma", "word", "dance", "finding", "max", "pain", "create", "love", "is", "simple", "save"};
        keyWords = new String[]{"word", "create", "save"};
        t = 1;
    }
}

/**
 * Класс для проверки наличия ключевых слов в массиве слов.
 */
class CheckKeyWord implements Relevant {
    String[] allWords; // массив всех слов

    TreeSet<String> keyWordsBinaryTree; // бинарное дерево ключевых слов

    /**
     * Конструктор класса.
     *
     * @param allWords            массив всех слов
     * @param keyWordsBinaryTree  бинарное дерево ключевых слов
     */

    CheckKeyWord(String[] allWords, TreeSet<String> keyWordsBinaryTree) {
        this.allWords = allWords;
        this.keyWordsBinaryTree = keyWordsBinaryTree;
    }

    /**
     * Метод для проверки наличия ключевого слова в массиве слов.
     *
     * @param id  индекс слова в массиве, которое нужно проверить
     * @return    true, если ключевое слово содержится в массиве, и false в противном случае
     */
    @Override
    public boolean isRelevant(int id) {
        return keyWordsBinaryTree.contains(allWords[id]);
    }

    /**
     * Метод для получения индекса последнего слова в массиве.
     *
     * @return  индекс последнего слова в массиве
     */
    @Override
    public int numberOfLastElement() {
        return allWords.length;
    }
}