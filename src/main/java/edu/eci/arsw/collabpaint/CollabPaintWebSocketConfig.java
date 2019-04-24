package edu.eci.arsw.collabpaint;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class CollabPaintWebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Value("${host}")
    String host;

    @Value("${port}")
    int port;

    @Value("${user}")
    String user;

    @Value("${password}")
    String password;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableStompBrokerRelay("/topic/").setRelayHost(host).setRelayPort(port).
                setClientLogin(user).
                setClientPasscode(password).
                setSystemLogin(user).
                setSystemPasscode(password).
                setVirtualHost(user);
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stompendpoint").setAllowedOrigins("*").withSockJS();
    }
}
