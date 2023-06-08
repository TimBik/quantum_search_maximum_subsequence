package ru.ferenec.model;

import ru.ferenec.util.quantum_search_submax_lib.util.Correct;

public class CorrectImpl implements Correct {
    private int[] correctValues;

    public CorrectImpl(int[] values) {
        this.correctValues = values;
    }

    public int isCorrect(int id) {
        return correctValues[id];
    }

    public int numberOfLastElement() {
        return correctValues.length;
    }
}
