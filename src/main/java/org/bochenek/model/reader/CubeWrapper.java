package org.bochenek.model.reader;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CubeWrapper {

    @JacksonXmlElementWrapper(localName = "Cube")
    private List<Cube> cubeList;
}
