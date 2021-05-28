package org.bochenek.service;

import java.util.Map;

public interface CountService {

    void updateCountMap(String... currencies);

    Map<String, Integer> getCurrencyRqCountMap();
}
