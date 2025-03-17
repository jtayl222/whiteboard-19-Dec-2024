package com.example;

import com.josephus.telemetry.TelemetryConfig; // Add import for your TelemetryConfig
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;

import java.util.List;

public class JosephusRequest {
    private List<String> people;
    private int stepCount;

    // Getters and Setters
    public List<String> getPeople() {
        return people;
    }

    public void setPeople(List<String> people) {
        this.people = people;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public static void main(String[] args) {
        // Initialize OpenTelemetry
        OpenTelemetry openTelemetry = TelemetryConfig.initTelemetry("JosephusRequest");
        Tracer tracer = openTelemetry.getTracer("com.example.JosephusRequest");

        // Start a span
        Span span = tracer.spanBuilder("main-method").startSpan();
        try (Scope scope = span.makeCurrent()) {
            System.out.println("JosephusRequest main method.");

            // Example usage
            JosephusRequest jr = new JosephusRequest();
            jr.setPeople(List.of("Alice", "Bob", "Charlie"));
            jr.setStepCount(3);

            // Log some attributes
            span.setAttribute("request.people.count", jr.getPeople().size());
            span.setAttribute("request.stepCount", jr.getStepCount());

            System.out.println("People: " + jr.getPeople());
            System.out.println("Step count: " + jr.getStepCount());
        } finally {
            // End the span
            span.end();
        }
    }
}
