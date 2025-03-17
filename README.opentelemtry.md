# OpenTelemetry for Josephus Application

This guide explains how to use OpenTelemetry for tracing and metrics in this application.

## 1. Overview

We've added OpenTelemetry instrumentation in the service via the `TelemetryConfig` class. The code automatically:
- Creates a resource with the service name
- Uses the OTLP exporter to send spans to a collector

## 2. Run and Test the Application

1. Start your application (server + client).
2. By default, spans are sent to http://localhost:4317. Customize the endpoint with the system property:  
   ```shell
   -Dotel.exporter.otlp.endpoint=http://<YOUR-COLLECTOR-HOST>:4317
   ```
3. Start a collector (see below) to receive your telemetry data.

## 3. Installing and Running a Collector

A minimal collector can be run with Docker:

```shell
docker run -d --name otelcol \
  -p 4317:4317 -p 55681:55681 -p 16686:16686 \
  otel/opentelemetry-collector \
  --config otel-collector-config.yaml
```

- Port **4317** is for receiving OTLP data.  
- Port **16686** is often used for Jaeger’s UI.

You may also install a Jaeger instance for viewing traces:
```shell
docker run -d --name jaeger \
  -e COLLECTOR_OTLP_ENABLED=true \
  -p 16686:16686 -p 4317:4317 \
  jaegertracing/all-in-one:latest
```

## 4. Viewing Traces

Access the Jaeger UI at http://localhost:16686 to see your spans. If you set a custom service name (e.g., `my-service`), search for it in the Jaeger UI.

## 5. Further Documentation

- [OpenTelemetry for Java](https://opentelemetry.io/docs/instrumentation/java/)  
- [OpenTelemetry Collector](https://github.com/open-telemetry/opentelemetry-collector)  
- [Jaeger Tracing](https://www.jaegertracing.io/)  