package com.example.backend.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import com.example.backend.models.DesignPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Component
public class DesignTelemetryHandler  extends TextWebSocketHandler {
    private static final Logger log = LoggerFactory.getLogger(DesignTelemetryHandler.class);
    private final ObjectMapper objectMapper;

    public DesignTelemetryHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("New workspace connected: {}", session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            // Parse the React Flow JSON into our Java Record
            DesignPayload payload = objectMapper.readValue(message.getPayload(), DesignPayload.class);

            log.info("Received architecture update. Nodes: {}, Edges: {}",
                    payload.nodes().size(), payload.edges().size());

            // TODO: Pass payload to Deterministic Engine & Groq LLM

            // Send a real-time feedback response back to the canvas
            String response = """
                {"status": "evaluating", "message": "Graph structure received successfully"}
                """;
            session.sendMessage(new TextMessage(response));

        } catch (Exception e) {
            log.error("Failed to process telemetry payload", e);
            session.sendMessage(new TextMessage("{\"error\": \"Invalid graph payload format\"}"));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("Connection closed: {}", session.getId());
    }
}
