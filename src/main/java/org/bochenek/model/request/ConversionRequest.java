package org.bochenek.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ConversionRequest {

    private String fromCurrency;
    private String toCurrency;
    private BigDecimal amount;
}
