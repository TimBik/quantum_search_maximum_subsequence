package tests;

import org.testng.annotations.Test;
import quantum_search_submax_lib.alg.FindRightSuitableWithBorder;
import quantum_search_submax_lib.util.Correct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindRightSuitableWithBorderTest {
    @Test
    public void testFindRightSuitableWithBorder() {
        Correct data = new CorrectImpl(new int[]{0, 1, 0, 0, 1, 1, 1, 0});

        FindRightSuitableWithBorder finder = new FindRightSuitableWithBorder();

        // Test case 1: No suitable segment in the data
        int leftBorder = finder.findRightSuitableWithBorder(data, 3, 3);
        assertEquals(4, leftBorder);

        // Test case 2: Suitable segment starts at ind=1, length=3
        leftBorder = finder.findRightSuitableWithBorder(data, 4, 3);
        assertEquals(4, leftBorder);

        // Test case 3: Suitable segment starts at ind=3, length=4
        leftBorder = finder.findRightSuitableWithBorder(data, 7, 4);
        assertEquals(8, leftBorder);

        // Test case 4: Suitable segment starts at ind=4, length=2
        leftBorder = finder.findRightSuitableWithBorder(data, 1, 2);
        assertEquals(1, leftBorder);

        Correct singleData = new CorrectImpl(new int[]{0});
        // Test case 5: Data array with length=1

        leftBorder = finder.findRightSuitableWithBorder(singleData, 1, 2);
        assertEquals(1, leftBorder);
    }

    private static class CorrectImpl implements Correct {
        private int[] correctValues;

        CorrectImpl(int[] values) {
            this.correctValues = values;
        }

        public int isCorrect(int id) {
            return correctValues[id];
        }

        public int numberOfLastElement() {
            return correctValues.length;
        }
    }
}

