package com.amarmodi.config.mongodb;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.camel.component.jms.JmsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration
@Primary
public class ActiveMQConfiguration {

    @Autowired
    private Environment environment;

    @Bean(name="activemq")
    public ActiveMQComponent activeMQComponent(JmsConfiguration jmsConfiguration){
        ActiveMQComponent activeMQComponent =new ActiveMQComponent();
        activeMQComponent.setConfiguration(jmsConfiguration);
        return activeMQComponent;
    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(environment.getProperty("spring.activemq.brokerUrl"));
        activeMQConnectionFactory.setPassword("t57Ahv6V");
        activeMQConnectionFactory.setUserName("userQ5D");
        activeMQConnectionFactory.setTrustAllPackages(true);
        return activeMQConnectionFactory;
    }

    @Bean
    @Primary
    public PooledConnectionFactory pooledConnectionFactory(ActiveMQConnectionFactory activeMQConnectionFactory){
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setMaxConnections(10);
        pooledConnectionFactory.setMaximumActiveSessionPerConnection(20);
        pooledConnectionFactory.setBlockIfSessionPoolIsFull(true);
        pooledConnectionFactory.setCreateConnectionOnStartup(true);
        pooledConnectionFactory.setIdleTimeout(50);
        pooledConnectionFactory.setConnectionFactory(activeMQConnectionFactory);
        return pooledConnectionFactory;
    }

    @Bean
    public JmsConfiguration jmsConfiguration(PooledConnectionFactory pooledConnectionFactory){
        JmsConfiguration jmsConfiguration = new JmsConfiguration();
        jmsConfiguration.setConnectionFactory(pooledConnectionFactory);
        jmsConfiguration.setConcurrentConsumers(5);
        jmsConfiguration.setMaxConcurrentConsumers(10);
        return jmsConfiguration;
    }

//    A new persistent messaging service has been created in your project. It will handle the protocol(s) "amqp".
//    The username/password for accessing the service is admin/admin.

}
