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

    @Bean
    @Qualifier("reportTopic")
    public NewTopic topic(){
        return TopicBuilder.name(reportTopic).partitions(4).build();
    }

}