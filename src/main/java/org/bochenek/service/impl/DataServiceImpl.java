package org.bochenek.service.impl;

import lombok.AllArgsConstructor;
import org.bochenek.model.RateWrapperMap;
import org.bochenek.model.reader.Cube;
import org.bochenek.reader.ECBClient;
import org.bochenek.service.DataService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DataServiceImpl implements DataService {

    private ECBClient ecbClient;

    @Override
    @Cacheable("cubes")
    public RateWrapperMap getRateWrapper() {

        RateWrapperMap rateWrapperMap = new RateWrapperMap();

        rateWrapperMap.setRateMap(ecbClient.getEcbReferences().getCubeWrapper().getCubeList()
                .stream()
                .collect(Collectors.toMap(Cube::getCurrency, cube -> cube)));

        return rateWrapperMap;
    }


}
