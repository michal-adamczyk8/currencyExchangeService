package com.currency.app.nbp;

import com.currency.app.nbp.model.ExchangeRatesSeries;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RestTemplateApiClient {

    @Value("${nbp.usd.resource}")
    private String uri;

    private RestTemplate restTemplate;

    public RestTemplateApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Method resposible for getting current exchange rate between USD and PLN from NBP public api.
     * @return
     */
    public ResponseEntity<ExchangeRatesSeries> getCurrentExchangeRate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(uri, HttpMethod.GET, entity, ExchangeRatesSeries.class);
    }
}
