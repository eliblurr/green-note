package org.tlc.microservices.orderservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

        @Value("${spring.kafka.topic.reporting.name}")
        public String saveOrderTopic;

        @Bean
        @Qualifier("ordersTopic")
        public NewTopic topic(){
            return TopicBuilder.name(saveOrderTopic).partitions(4).build();
        }

    }


