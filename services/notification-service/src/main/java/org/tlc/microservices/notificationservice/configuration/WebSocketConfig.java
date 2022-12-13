package org.tlc.microservices.notificationservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
        @Override
        public void configureMessageBroker(MessageBrokerRegistry config) {
<<<<<<< HEAD:services/notification-service/src/main/java/org/tlc/microservices/notificationservice/configuration/WebSocketConfiguration.java
            config.enableSimpleBroker("/ws/topic");
            config.setApplicationDestinationPrefixes("/app");
=======
            config.enableSimpleBroker("/ws/topic"); // clients should listen on this prefix
            config.setApplicationDestinationPrefixes("/ws");
>>>>>>> f5f5a7bb07a6022ff18ce277c8979f127761a8ac:services/notification-service/src/main/java/org/tlc/microservices/notificationservice/configuration/WebSocketConfig.java
        }

        @Override
        public void registerStompEndpoints(StompEndpointRegistry registry) {
            registry.addEndpoint("/ws/socket-registry")
<<<<<<< HEAD:services/notification-service/src/main/java/org/tlc/microservices/notificationservice/configuration/WebSocketConfiguration.java
                    .setAllowedOriginPatterns("*").withSockJS(); //end point to for frontend to connect (socket = SockJs('/registry-websocket')
=======
                    .setAllowedOriginPatterns("*").withSockJS(); // endpoint for clients to register and connect (socket = SockJs('/registry-websocket')
>>>>>>> f5f5a7bb07a6022ff18ce277c8979f127761a8ac:services/notification-service/src/main/java/org/tlc/microservices/notificationservice/configuration/WebSocketConfig.java
        }
}



