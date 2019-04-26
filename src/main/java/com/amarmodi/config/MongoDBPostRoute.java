package com.amarmodi.config;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MongoDBPostRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

//        from("{{mongo.postRoute}}")
//                .log("The body for the  insert is : ${body}")
//                .to("{{mongo.insertInputPost}}")
//                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201));

    }
}
