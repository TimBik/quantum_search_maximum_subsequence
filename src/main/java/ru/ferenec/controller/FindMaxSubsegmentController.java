package ru.ferenec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ferenec.model.CorrectImpl;
import ru.ferenec.util.quantum_search_submax_lib.alg.FindMaxSubsegment;
import ru.ferenec.util.quantum_search_submax_lib.util.Util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FindMaxSubsegmentController {
    private final static FindMaxSubsegment service = new FindMaxSubsegment();

    @GetMapping("/find-max-subsegment")
    public String getFindMaxSubsegmentPage() {
        return "find-max-subsegment";
    }

    @PostMapping("/find-max-subsegment")
    @ResponseBody
    public int[] postFindMaxSubsegmentPage(@RequestParam(value = "data") String data) {
        List<Integer> numbers = Arrays.stream(data.split(",")).map(Integer::valueOf).collect(Collectors.toList());

        return service.findMaxSubsegment(new CorrectImpl(Util.convertListToArray(numbers)));
    }
}
