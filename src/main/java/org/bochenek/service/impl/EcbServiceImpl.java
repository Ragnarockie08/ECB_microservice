package org.bochenek.service.impl;

import lombok.AllArgsConstructor;
import org.bochenek.model.reader.Cube;
import org.bochenek.model.response.Pair;
import org.bochenek.service.DataService;
import org.bochenek.service.EcbService;
import org.springframework.stereotype.Service;


import static org.bochenek.helper.EcbServiceHelper.*;

@Service
@AllArgsConstructor
public class EcbServiceImpl implements EcbService {

    private final DataService dataService;

    @Override
    public Pair getRefPair(String refOne, String refTwo) {

        Cube cubeOne = retrieveCube(refOne, dataService.getRateWrapper().getRateMap());
        Cube cubeTwo = retrieveCube(refTwo, dataService.getRateWrapper().getRateMap());

        return Pair.builder()
                .pairRef(buildPairName(refOne, refTwo))
                .pairRate(calculateRate(cubeOne, cubeTwo))
                .build();
    }
}
