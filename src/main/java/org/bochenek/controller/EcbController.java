package org.bochenek.controller;

import lombok.AllArgsConstructor;
import org.bochenek.model.response.Pair;
import org.bochenek.service.EcbService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ecb")
@AllArgsConstructor
public class EcbController {

    private final EcbService ecbService;


    @GetMapping("/ref-pair")
    public Pair getRateList(@RequestParam String refOne, @RequestParam(required = false) String refTwo) {
        return ecbService.getRefPair(refOne, refTwo);
    }
}
