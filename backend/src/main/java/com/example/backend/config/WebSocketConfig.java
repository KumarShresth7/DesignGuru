package com.example.backend.config;

import com.example.backend.websocket.DesignTelemetryHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
    private final DesignTelemetryHandler telemetryHandler;
    public WebSocketConfig(DesignTelemetryHandler telemetryHandler) {
        this.telemetryHandler = telemetryHandler;
    }
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(telemetryHandler, "/ws/telemetry")
                .setAllowedOrigins("*"); // In production, restrict this to your frontend URL
    }
}
