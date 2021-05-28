package org.bochenek.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bochenek.model.RateWrapperMap;
import org.bochenek.model.reader.Cube;
import org.bochenek.reader.ECBClient;
import org.bochenek.service.DataService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class DataServiceImpl implements DataService {

    private ECBClient ecbClient;

    @Override
    @Cacheable("cubes")
    public RateWrapperMap getRateWrapper() {

        RateWrapperMap rateWrapperMap = new RateWrapperMap();
        log.info("Requesting ECB data from external URL.");
        try {
            rateWrapperMap.setRateMap(ecbClient.getEcbReferences().getCubeWrapper().getCubeList()
                    .stream()
                    .collect(Collectors.toMap(Cube::getCurrency, cube -> cube)));
        } catch (Exception e) {
            log.error("Unable to retrieve data from external resource.");
        }
        log.info("External data retrieved and collected to wrapper object.");
        return rateWrapperMap;
    }


}
