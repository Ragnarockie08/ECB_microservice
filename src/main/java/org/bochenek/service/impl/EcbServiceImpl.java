package org.bochenek.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bochenek.model.reader.Cube;
import org.bochenek.model.request.ConversionRequest;
import org.bochenek.model.response.ConversionResult;
import org.bochenek.model.response.Pair;
import org.bochenek.service.CountService;
import org.bochenek.service.DataService;
import org.bochenek.service.EcbService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static org.bochenek.helper.EcbServiceHelper.*;

@Service
@AllArgsConstructor
@Slf4j
public class EcbServiceImpl implements EcbService {

    private final DataService dataService;
    private final CountService countService;

    @Override
    public Pair getRefPair(String refOne, String refTwo) {

        Cube cubeOne = retrieveCube(refOne, dataService.getRateWrapper().getRateMap());
        Cube cubeTwo = retrieveCube(refTwo, dataService.getRateWrapper().getRateMap());

        countService.updateCountMap(cubeOne.getCurrency(), cubeTwo.getCurrency());

        Pair pair = Pair.builder()
                .pairRef(buildPairName(cubeOne.getCurrency(), cubeTwo.getCurrency()))
                .pairRate(calculateRate(cubeOne, cubeTwo))
                .pairChartLink(getPairLink(cubeOne.getCurrency(), cubeTwo.getCurrency()))
                .build();
        log.info("Calculated pair of reference rate: " + pair);

        return pair;
    }

    @Override
    public Map<String, Integer> getCurrencyList() {
        return countService.getCurrencyRqCountMap();
    }

    @Override
    public ConversionResult convertAmount(ConversionRequest conversionRequest) {

        Cube cubeOne = retrieveCube(conversionRequest.getFromCurrency().toUpperCase(),
                dataService.getRateWrapper().getRateMap());
        Cube cubeTwo = retrieveCube(conversionRequest.getToCurrency().toUpperCase(),
                dataService.getRateWrapper().getRateMap());

        countService.updateCountMap(cubeOne.getCurrency(), cubeTwo.getCurrency());
        BigDecimal resultAmount = convertAmount(cubeOne, cubeTwo, conversionRequest.getAmount());

        ConversionResult conversionResult = ConversionResult.builder()
                .amount(resultAmount)
                .build();

        log.info("Converted: {} {} to: {} {}.", conversionRequest.getAmount(),
                cubeOne.getCurrency(), resultAmount, cubeTwo.getCurrency());


        return conversionResult;
    }

    private BigDecimal calculateRate(Cube baseCube, Cube cubeTwo) {
        BigDecimal baseRate = baseCube.getRate();
        BigDecimal rateTwo = cubeTwo.getRate();

        return rateTwo.divide(baseRate, 4, RoundingMode.HALF_UP);
    }

    private BigDecimal convertAmount(Cube cubeOne, Cube cubeTwo, BigDecimal amount) {

        BigDecimal rate = calculateRate(cubeTwo, cubeOne);

        return amount.divide(rate, 2, RoundingMode.HALF_UP);
    }
}
