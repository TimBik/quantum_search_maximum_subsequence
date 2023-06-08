package ru.ferenec;

import org.testng.annotations.Test;
import ru.ferenec.util.quantum_search_submax_lib.alg.FindLeftSuitableWithBorder;
import ru.ferenec.util.quantum_search_submax_lib.util.Correct;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindLeftSuitableWithBorderTest {
    @Test
    public void testFindLeftSuitableWithBorder() {
        Correct data = new CorrectImpl(new int[]{0, 1, 0, 0, 1, 1, 1, 0});

        FindLeftSuitableWithBorder finder = new FindLeftSuitableWithBorder();

        // Test case 1: No suitable segment in the data
        int leftBorder = finder.findLeftSuitableWithBorder(data, 3, 3);
        assertEquals(1, leftBorder);

        // Test case 2: Suitable segment starts at ind=1, length=3
        leftBorder = finder.findLeftSuitableWithBorder(data, 4, 3);
        assertEquals(1, leftBorder);

        // Test case 3: Suitable segment starts at ind=3, length=4
        leftBorder = finder.findLeftSuitableWithBorder(data, 7, 4);
        assertEquals(6, leftBorder);

        // Test case 4: Suitable segment starts at ind=4, length=2
        leftBorder = finder.findLeftSuitableWithBorder(data, 6, 2);
        assertEquals(5, leftBorder);

        Correct singleData = new CorrectImpl(new int[]{0});
        // Test case 5: Data array with length=1

        leftBorder = finder.findLeftSuitableWithBorder(singleData, 1, 2);
        assertEquals(-1, leftBorder);
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
            return correctValues.length - 1;
        }
    }
}
