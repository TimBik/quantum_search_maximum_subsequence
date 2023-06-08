package ru.ferenec.util.quantum_search_submax_lib.util;

import java.util.List;

public class Util {
    public static int[] convertListToArray(List<Integer> list) {
        int[] arr = new int[list.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }
}
