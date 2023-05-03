package example_tasks;

/**
 * Класс CommonString представляет собой алгоритм поиска максимальной общей подстроки
 * в массиве строк.
 */
public class CommonString {

    static int m; // число символов в общей подстроке
    static String[] allWords; // массив всех строк для поиска общей подстроки
    static int lMaxDistance; // индекс начала максимальной общей подстроки
    static int maxDistance; // длина максимальной общей подстроки

    static String maxCommonString; // сама максимальная общая подстрока

    /**
     * Главный метод класса, который запускает алгоритм поиска максимальной общей подстроки.
     * Он вызывает другие методы в нужном порядке: чтение входных данных, предварительная обработка,
     * поиск максимальной общей подстроки и вывод результата.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        readData();
        preprocessing();
        findMaxCommonString();
        printingResult();
    }

    /**
     * Выводит результат работы алгоритма поиска максимальной общей подстроки в консоль.
     * Если общих подстрок нет, то выводится сообщение об этом.
     */
    private static void printingResult() {
        System.out.println("t is: " + maxDistance);
        if (maxDistance == 0) {
            System.out.println("common strings do not exist");
        } else {
            System.out.println("common string is: " + maxCommonString);
            System.out.println("starts at index: " + lMaxDistance);
        }
    }

    /**
     * Находит максимальную общую подстроку в массиве строк allWords.
     * Алгоритм работает за линейное время O(m*n), где m - число символов в общей подстроке,
     * n - число строк в массиве allWords.
     */
    private static void findMaxCommonString() {
        lMaxDistance = -1;
        maxDistance = 0;
        int lNow = -1;
        int rNow = -2;
        maxCommonString = "";
        for (int i = 0; i < m; i++) {
            boolean common = true;
            char commonCharacter = allWords[0].charAt(i);
            for (int j = 1; j < allWords.length; j++) {
                if (allWords[j].charAt(i) != commonCharacter) {
                    common = false;
                    break;
                }
            }
            if (common) {
                if (lNow == -1) {
                    lNow = i;
                }
                rNow = i;
            } else {
                lNow = -1;
                rNow = -2;
            }
            int distance = rNow - lNow + 1;
            if (maxDistance < distance) {
                maxDistance = distance;
                lMaxDistance = lNow;
            }
        }

        if (maxDistance != 0) {
            maxCommonString = allWords[0].substring(lMaxDistance,
                    lMaxDistance + maxDistance);
        }
    }

    /**
     * Метод preprocessing определяет длину самого короткого слова среди всех входных слов,
     * чтобы ограничить количество сравнений во время поиска общей строки.
     */
    private static void preprocessing() {
        m = allWords[0].length();
        for (int i = 1; i < allWords.length; i++) {
            m = Math.min(allWords[i].length(), m);
        }
    }
    /**
     * Метод readData используется для чтения входных данных в массив строк allWords,
     * который будет использоваться для поиска общей строки.
     */
    private static void readData() {
        allWords = new String[]{"111111", "11111122222", "11111133333", "112111"};
    }
}
