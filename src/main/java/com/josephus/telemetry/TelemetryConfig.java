// New file for telemetry configuration
package com.josephus.telemetry;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;

import java.util.concurrent.TimeUnit;

public class TelemetryConfig {

    public static OpenTelemetry initTelemetry(String serviceName) {
        // Create resource with service information (no semconv needed)
        Resource resource = Resource.getDefault()
            .merge(Resource.create(
                Attributes.of(AttributeKey.stringKey("service.name"), serviceName)));

        // Setup span exporter
        OtlpGrpcSpanExporter spanExporter = OtlpGrpcSpanExporter.builder()
            .setEndpoint(System.getProperty("otel.exporter.otlp.endpoint", "http://localhost:4317"))
            .setTimeout(30, TimeUnit.SECONDS)
            .build();

        // Setup tracer provider
        SdkTracerProvider tracerProvider = SdkTracerProvider.builder()
            .addSpanProcessor(BatchSpanProcessor.builder(spanExporter).build())
            .setResource(resource)
            .build();

        // Create OpenTelemetry instance
        return OpenTelemetrySdk.builder()
            .setTracerProvider(tracerProvider)
            .setPropagators(ContextPropagators.create(W3CTraceContextPropagator.getInstance()))
            .build();
    }
}