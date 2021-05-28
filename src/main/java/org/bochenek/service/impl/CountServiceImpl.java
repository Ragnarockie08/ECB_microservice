package org.bochenek.service.impl;

import lombok.AllArgsConstructor;
import org.bochenek.service.CountService;
import org.bochenek.service.DataService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor
public class CountServiceImpl implements CountService {

    private final DataService dataService;
    private static Map<String, Integer> currencyRqCountMap;

    public void updateCountMap(String... currencies) {
        Map<String, Integer> currencyMap = getCurrencyRqCountMap();
        for (String ref: currencies) {
            currencyMap.put(ref,  currencyMap.containsKey(ref) ? currencyMap.get(ref) + 1 : 1);
        }
    }

    public Map<String, Integer> getCurrencyRqCountMap() {
        if(currencyRqCountMap == null) {
            currencyRqCountMap = initMap(dataService.getRateWrapper().getRateMap().keySet());
        }
        return currencyRqCountMap;
    }

    private Map<String, Integer> initMap(Set<String> defaultCurrencies) {

        Map<String, Integer> currencyMap = new HashMap<>();
        for (String s : defaultCurrencies) {
            currencyMap.put(s, 0);
        }
        return currencyMap;
    }
}
