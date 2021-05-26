package org.bochenek.helper;

import org.bochenek.ECBConst;
import org.bochenek.model.reader.Cube;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class EcbServiceHelper {

    public static Cube retrieveCube(String ref, Map<String, Cube> rateMap) {
        return ref == null || ref.equals(ECBConst.BASE_EUR)
                ? Cube.builder().rate(BigDecimal.ONE).currency(ECBConst.BASE_EUR).build()
                : rateMap.get(ref);
    }

    public static BigDecimal calculateRate(Cube cubeOne, Cube cubeTwo) {
        BigDecimal rateOne = cubeOne.getRate();
        BigDecimal rateTwo = cubeTwo.getRate();

        return rateTwo.divide(rateOne, 2, RoundingMode.HALF_UP);
    }

    public static String buildPairName(String refOne, String refTwo) {

        return refOne +
                ECBConst.DELIMITER +
                refTwo;
    }
}
