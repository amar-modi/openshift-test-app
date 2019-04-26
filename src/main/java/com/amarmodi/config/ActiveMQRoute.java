package com.amarmodi.config;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class ActiveMQRoute extends RouteBuilder {

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure() throws Exception {
        from("activemq:inputItemQueue")
                .log("Read MEsssage From active MQ: ${body}")
                .log("Unmarshalled object is : ${body}")
                .log("The output from the select query was: ${body}");
    }
}
