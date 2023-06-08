package ru.ferenec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ferenec.model.CorrectImpl;
import ru.ferenec.util.quantum_search_submax_lib.alg.GroverAlgorithm;
import ru.ferenec.util.quantum_search_submax_lib.util.Util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GroverAlgorithmGetController {

    @GetMapping("/glover-algorithm")
    public String getGroverAlgorithmPage() {
        return "glover-algorithm";
    }

    @PostMapping("/glover-algorithm")
    @ResponseBody
    public int postGroverAlgorithmPage(
            @RequestParam(value = "data") String data,
            @RequestParam(value = "indexStart") int indexStart,
            @RequestParam(value = "indexEnd") int indexEnd) {

        List<Integer> numbers = Arrays.stream(data.split(",")).map(Integer::valueOf).collect(Collectors.toList());

        return GroverAlgorithm.findAnyCorrectIndex(
                new CorrectImpl(Util.convertListToArray(numbers)),
                indexStart,
                indexEnd
        );
    }
}
