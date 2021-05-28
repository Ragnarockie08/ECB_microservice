package org.bochenek.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pair {

    private String pairRef;
    private BigDecimal pairRate;
    private String pairChartLink;
}
