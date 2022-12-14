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

    @Value("${spring.kafka.topic.reporting.leg.create.name}")
    public String createLegTopic;





    @Bean("reportsTopic")
    @Qualifier("reportsTopic")

    public NewTopic consumerTopic() {
        return TopicBuilder.name(reportsTopic).partitions(2).build();
    }

    @Bean("accountTopic")
    @Qualifier("accountTopic")
    public NewTopic producerTopic() {
        return TopicBuilder.name(accountTopic).partitions(2).build();
    }

    @Bean("ordersTopic")
    @Qualifier("ordersTopic")
    public NewTopic orderTopic() {
        return TopicBuilder.name(saveOrderTopic).partitions(4).build();
    }

    @Bean("legsTopic")
    @Qualifier("legsTopic")
    public NewTopic legTopic() {
        return TopicBuilder.name(createLegTopic).partitions(4).build();
    }
}
