package com.vlabs.hw.alpha.routes.flow.a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Route extends RouteBuilder {

    @Value("${flowA.stepA}")
    private String stepA;

    @Value("${flowA.stepB}")
    private String stepB;

    @Value("${flowA.stepC}")
    private String stepC;

    @Override
    public void configure() throws Exception {
        from(stepA)
                .bean("stepA", "perform");
        from(stepB)
                .bean("stepB", "perform");
        from(stepC)
                .bean("stepC", "perform");

//        from("timer:flowA?period=10000")
        from("direct:flowA")
                .log("start:: flow A")
                .dynamicRouter(method("plan", "steps"))
                .log("end:: flow A")
                .log("------------");
    }
}
