package org.bochenek.service;

import org.bochenek.model.response.Pair;

public interface EcbService {

    Pair getRefPair(String refOne, String refTwo);
}
