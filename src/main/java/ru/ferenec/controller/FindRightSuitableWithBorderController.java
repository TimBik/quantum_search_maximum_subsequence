package ru.ferenec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ferenec.model.CorrectImpl;
import ru.ferenec.util.quantum_search_submax_lib.alg.FindRightSuitableWithBorder;
import ru.ferenec.util.quantum_search_submax_lib.util.Util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FindRightSuitableWithBorderController {

    private static final FindRightSuitableWithBorder service = new FindRightSuitableWithBorder();

    @GetMapping("/find-right-suitable-with-border-data")
    public String getFindRightSuitableWithBorderData() {
        return "find-right-suitable-with-border-data";
    }

    @PostMapping("/find-right-suitable-with-border-data")
    @ResponseBody
    public int postFindRightSuitableWithBorderData(
            @RequestParam(value = "data") String data,
            @RequestParam(value = "index") int index,
            @RequestParam(value = "d") int d
    ) {
        List<Integer> numbers = Arrays.stream(data.split(",")).map(Integer::valueOf).collect(Collectors.toList());

        return service.findRightSuitableWithBorder(
                new CorrectImpl(Util.convertListToArray(numbers)),
                index,
                d
        );
    }
}
