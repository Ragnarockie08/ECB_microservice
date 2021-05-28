package org.bochenek.helper;

import org.bochenek.ECBConst;
import org.bochenek.model.reader.Cube;

import java.math.BigDecimal;
import java.util.Map;

public class EcbServiceHelper {

    public static Cube retrieveCube(String ref, Map<String, Cube> rateMap) {
        return ref == null || ref.toUpperCase().equals(ECBConst.BASE_EUR)
                ? Cube.builder().rate(BigDecimal.ONE).currency(ECBConst.BASE_EUR).build()
                : rateMap.get(ref.toUpperCase());
    }

    public static String getPairLink(String currFrom, String currTo) {
        return ECBConst.LINK_BEG + currFrom + ECBConst.LINK_DELIMITER + currTo;
    }

    public static String buildPairName(String refOne, String refTwo) {

        return refOne +
                ECBConst.DELIMITER +
                refTwo;
    }

}
