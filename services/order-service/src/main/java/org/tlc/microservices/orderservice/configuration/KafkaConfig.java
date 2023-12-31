package org.tlc.microservices.orderservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {



    @Value("${spring.kafka.topic.reporting.order.create.name}")
    public String createOrderTopic;
    @Value("${spring.kafka.topic.reporting.leg.create.name}")
    public String createLegTopic;

    @Value("${spring.kafka.topic.md.data}")
    public String mdTopic;

//    @Bean
//    @Qualifier("ordersTopic")
//    public NewTopic topic(){
//            return TopicBuilder.name(saveOrderTopic).partitions(2).build();
//        }

    @Bean("createOrderTopic")

    @Qualifier("createOrderTopic")
    public NewTopic createOrderTopic(){
        return TopicBuilder.name(createOrderTopic).partitions(2).build();
    }
    @Bean("createLegTopic")
    @Primary
    @Qualifier("createLegTopic")
    public NewTopic createLegTopic(){
        return TopicBuilder.name(createLegTopic).partitions(2).build();
    }

    @Bean("mdTopic")
    @Qualifier("mdTopic")
    public NewTopic mdTopic(){
        return TopicBuilder.name(mdTopic).partitions(2).build();
    }

}


