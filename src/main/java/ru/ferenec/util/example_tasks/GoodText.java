package ru.ferenec.util.example_tasks;

import java.util.Arrays;
import java.util.TreeSet;
/**
 * Класс GoodText предназначен для проверки текста на соответствие определенному стандарту.
 * Определяется максимальное расстояние между ключевыми словами и сравнивается с пороговым значением t,
 * чтобы определить, является ли текст хорошим, почти хорошим или плохим.
 */
public class GoodText {
    static String[] allWords; // массив всех слов в тексте
    static String[] keyWords; // массив ключевых слов
    static TreeSet<String> keyWordsBinaryTree; // бинарное дерево, содержащее ключевые слова
    static int maxDistance; // максимальное расстояние между ключевыми словами
    static int startIndexKeyWord; // индекс начала первого ключевого слова
    static int endIndexKeyWord; // индекс конца последнего ключевого слова
    static int t; // пороговое значение для классификации текста

    /**
     * Основной метод класса, выполняющий чтение данных, предобработку, нахождение расстояний между ключевыми словами и вывод результата
     */
    public static void main(String[] args) {
        readData();
        preprocessing();
        findDistances();
        printingResult();
    }

    /**
     * Метод, выводящий результат классификации текста
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
     * Метод, находящий расстояния между ключевыми словами и запоминающий индексы начала и конца последнего ключевого слова
     */
    private static void findDistances() {
        int distance = Integer.MIN_VALUE;
        startIndexKeyWord = -1;
        for (int i = 0; i < allWords.length; i++) {
            String word = allWords[i];
            if (keyWordsBinaryTree.contains(word)) {
                keyWordsBinaryTree.remove(word);
                if (startIndexKeyWord == -1) {
                    startIndexKeyWord = i;
                }
                maxDistance = Math.max(distance, maxDistance);
                endIndexKeyWord = i;
                distance = 0;
            } else {
                distance++;
            }
        }
    }

    /**
     * Метод, выполняющий предобработку данных, инициализирующий бинарное дерево ключевых слов
     */
    private static void preprocessing() {
        keyWordsBinaryTree = new TreeSet<>();
        keyWordsBinaryTree.addAll(Arrays.asList(keyWords));
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
