package com.example.backend.models;
import java.util.List;

public record DesignPayload(
        List<Node> nodes,
        List<Edge> edges
) {
    public record Node(
            String id,
            String type,
            Position position,
            NodeData data
    ) {}

    public record Position(double x, double y) {}

    public record NodeData(
            String label,
            String protocol,
            String engine
    ) {}

    public record Edge(
            String id,
            String source,
            String target
    ) {}
}