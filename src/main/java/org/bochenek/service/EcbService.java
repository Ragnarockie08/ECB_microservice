package org.bochenek.service;

import org.bochenek.model.request.ConversionRequest;
import org.bochenek.model.response.ConversionResult;
import org.bochenek.model.response.Pair;

import java.util.Map;

public interface EcbService {

    Pair getRefPair(String refOne, String refTwo);

    Map<String, Integer> getCurrencyList();

    ConversionResult convertAmount(ConversionRequest conversionRequest);
}
