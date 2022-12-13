package org.tlc.microservices.marketdataservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.topic.reporting.name}")
    public String reportTopic;

    @Value("${spring.kafka.topic.notification.order.name}")
    public String orderTopic;

    @Value("${spring.kafka.topic.notification.trade.name}")
    public String tradeTopic;



    @Bean
    @Qualifier("reportTopic")
    public NewTopic topic(){
        return TopicBuilder.name(reportTopic).partitions(4).build();
    }

    @Bean
    @Qualifier("orderTopic")
    public NewTopic topic2(){
        return TopicBuilder.name(orderTopic).partitions(4).build();
    }

    @Bean
    @Qualifier("tradeTopic")
    public NewTopic tradeTopic(){
        return TopicBuilder.name(tradeTopic).partitions(4).build();
    }

}