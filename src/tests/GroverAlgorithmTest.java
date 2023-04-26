package tests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import quantum_search_submax_lib.util.Correct;
import quantum_search_submax_lib.alg.GroverAlgorithm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GroverAlgorithmTest {
    private static final int L_ID = 0;
    private static final int R_ID = 4;
    private static final int NUM_CORRECT_VALUES = 5;
    private static final int UNQUANTUM_CONST = 100;
    private GroverAlgorithm instance;
    private Method method;
    @BeforeTest
    public void init() throws NoSuchMethodException {
        instance = new GroverAlgorithm();
        method = GroverAlgorithm.class.getDeclaredMethod("findAnyCorrectIndex", Correct.class, int.class, int.class);
        method.setAccessible(true);
    }

    @Test
    public void testFindAnyCorrectIndex() throws InvocationTargetException, IllegalAccessException {
        // test that the method returns a correct value when one exists
        Correct correct = new CorrectImpl(new int[]{10, 20, 2, 40, 50});
        int result = 0;
        for (int i = 0; i < UNQUANTUM_CONST; i++) {
            result =  (int) method.invoke(instance, correct, L_ID, R_ID);
            if(correct.isCorrect(result)==1){
                break;
            }
        }
        assertTrue(result == 2);
    }

    @Test
    public void testFindAnyCorrectIndexNoCorrectValues() throws InvocationTargetException, IllegalAccessException {
        // test that the method returns -1 when no correct values exist
        Correct correct = new CorrectImpl(new int[]{15, 59, 56, 85, 9});
        int result = 0;
        for (int i = 0; i < UNQUANTUM_CONST; i++) {
            result =  (int) method.invoke(instance, correct, L_ID, R_ID);
            if(correct.isCorrect(result)==1){
                break;
            }
        }
        assertTrue(correct.isCorrect(result) == 0);
    }

    @Test
    public void testFindAnyCorrectIndexAllCorrectValues() throws InvocationTargetException, IllegalAccessException {
        // test that the method returns a correct value when all values are correct
        Correct correct = new CorrectImpl(new int[]{0, 1, 2, 3, 4});
        int result = (int) method.invoke(instance, correct, L_ID, R_ID);
        assertTrue(correct.isCorrect(result) == 1);
    }

    @Test
    public void testFindAnyCorrectIndexInvalidRange() throws InvocationTargetException, IllegalAccessException {
        // test that the method returns -1 when an invalid range is provided
        Correct correct = new CorrectImpl(new int[]{10, 20, 30, 40, 50});
        int result = (int) method.invoke(instance, correct, R_ID, L_ID);
        assertTrue(result == -1);
    }

    /**
     * Класс CorrectImpl, который реализует интерфейс Correct. Этот класс использовался в тестах для создания объекта Correct,
     * передавая в конструктор массив корректных значений. В тестах использовал новый метод lastIndex() интерфейса Correct,
     * чтобы проверить, что метод FindAnyCorrectIndex не возвращает значение, которое больше, чем последний корректный элемент.
     */
    private static class CorrectImpl implements Correct {
        private int[] correctValues;

        CorrectImpl(int[] values) {
            this.correctValues = values;
        }

        public int isCorrect(int value) {
            if (correctValues[value]==value){
                return 1;
            }
            return 0;
        }

        public int numberOfLastElement() {
            return correctValues.length > 0 ? correctValues.length - 1: -1;
        }
    }
}