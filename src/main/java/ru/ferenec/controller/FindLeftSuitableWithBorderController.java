package ru.ferenec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ferenec.model.CorrectImpl;
import ru.ferenec.util.quantum_search_submax_lib.alg.FindLeftSuitableWithBorder;
import ru.ferenec.util.quantum_search_submax_lib.util.Util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FindLeftSuitableWithBorderController {
    private static final FindLeftSuitableWithBorder service = new FindLeftSuitableWithBorder();

    @GetMapping("/find-left-suitable-with-border-data")
    public String getFindLeftSuitableWithBorderData() {
        return "find-left-suitable-with-border-data";
    }

    @PostMapping("/find-left-suitable-with-border-data")
    @ResponseBody
    public int postFindLeftSuitableWithBorderData(
            @RequestParam(value = "data") String data,
            @RequestParam(value = "index") int index,
            @RequestParam(value = "d") int d
    ) {
        List<Integer> numbers = Arrays.stream(data.split(",")).map(Integer::valueOf).collect(Collectors.toList());

        return service.findLeftSuitableWithBorder(
                new CorrectImpl(Util.convertListToArray(numbers)),
                index,
                d
        );
    }
}
