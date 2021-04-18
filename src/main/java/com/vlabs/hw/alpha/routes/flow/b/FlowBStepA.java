package com.vlabs.hw.alpha.routes.flow.b;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class FlowBStepA {
    public void perform(@Headers Map<String, String> headers,
                        @ExchangeProperties Map<String, String> exchangeProperties,
                        @Body String body) {
          log.info("---- [performing Step A]");

          String step = route();

          headers.put("step", step);
    }

    private String route() {
        String step = "stepC";

        long currentTimeMillis = System.currentTimeMillis();
        log.info("odd({}) ? steps[C, D] : step[B]", currentTimeMillis);
        if(currentTimeMillis % 2 == 0) {
            step = "stepB";
        }
        return step;
    }
}
