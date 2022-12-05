package org.tlc.microservices.userservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("spring.kafka.topic.logging.name")
    private String logTopic;

    @Bean
    @Qualifier("logTopic")
    public NewTopic topic(){
        return TopicBuilder.name(logTopic).partitions(4).build();
    }

}
