package org.bochenek.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bochenek.model.reader.Cube;

import java.util.Map;

@Data
@NoArgsConstructor
public class RateWrapperMap {

    private Map<String, Cube> rateMap;
}
