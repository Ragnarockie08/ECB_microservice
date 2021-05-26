package org.bochenek.reader;


import org.bochenek.model.reader.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(value = "ecb-client", url = "https://www.ecb.europa.eu", configuration = FeignConfiguration.class)
public interface ECBClient {

    @GetMapping(value = "/stats/eurofxref/eurofxref-daily.xml", produces = "application/json")
    Response getEcbReferences();

}
