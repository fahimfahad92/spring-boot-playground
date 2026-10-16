# micrometer-observation

Method-level observability with Micrometer's Observation API in Spring Boot 3, plus a multi-stage Dockerfile.

- `@Observed(name = "welcomeMethod")` on `WelcomeService.welcome` turns one method into a timer metric and an observation, with no manual timing code.
- `ObservedAspectConfiguration` registers the `ObservedAspect` bean that makes `@Observed` work, and attaches `SimpleLoggingHandler`, which logs each observation's start, scope open/close, stop and duration.
- `/welcome/noObserve` calls the same logic without the annotation, for comparison.
- `Dockerfile`: three stages (JDK builder → Maven package with a cached `~/.m2` → JRE runtime image).

**Stack:** Java 17 · Spring Boot 3.4 · Actuator · Spring AOP · Micrometer

## Run

```bash
./mvnw spring-boot:run
curl "http://localhost:8080/welcome?name=Fahim"
curl http://localhost:8080/actuator/metrics/welcomeMethod   # count, total time, max
```

Watch the application log for the handler's `Starting welcomeMethod` / `Stopped welcomeMethod` lines.

## Docker

```bash
docker build -t micrometer-observation .
docker run -p 8080:8080 micrometer-observation
```

## Tests

`./mvnw verify` — controller slice tests (`@WebMvcTest`) and service unit tests.
