package org.tlc.microservices.reportingservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.topic.reports.name}")
    public String reportsTopic;

    @Value("${spring.kafka.topic.account.name}")
    public String accountTopic;
    @Value("${spring.kafka.topic.order.name}")
    public String saveOrderTopic;



    @Bean
    @Qualifier("reportsTopic")
    public NewTopic consumerTopic() {
        return TopicBuilder.name(reportsTopic).partitions(4).build();
    }

    @Bean
    @Qualifier("accountTopic")
    public NewTopic producerTopic() {
        return TopicBuilder.name(accountTopic).partitions(4).build();
    }

    @Bean
    @Qualifier("ordersTopic")
    public NewTopic orderTopic() {
        return TopicBuilder.name(saveOrderTopic).partitions(4).build();
    }
}
