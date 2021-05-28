package org.bochenek.controller;

import lombok.AllArgsConstructor;
import org.bochenek.model.request.ConversionRequest;
import org.bochenek.model.response.ConversionResult;
import org.bochenek.model.response.Pair;
import org.bochenek.service.EcbService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ecb")
@AllArgsConstructor
public class EcbController {

    private final EcbService ecbService;


    @GetMapping("/ref-pair")
    public Pair getRateList(@RequestParam String refOne, @RequestParam(required = false) String refTwo) {
        return ecbService.getRefPair(refOne, refTwo);
    }

    @GetMapping("/currency-list")
    public Map<String, Integer> getAvailableCurrencies() {
        return ecbService.getCurrencyList();
    }

    @PostMapping("/convert")
    public ConversionResult convert(@RequestBody ConversionRequest conversionRequest) {
        return ecbService.convertAmount(conversionRequest);
    }
}
