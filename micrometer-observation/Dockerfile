# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy as deps

WORKDIR /build

COPY --chmod=0755 mvnw mvnw
COPY .mvn/ .mvn/

RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=D:/Deps ./mvnw dependency:go-offline -DskipTests

################################################################################

FROM deps as package

WORKDIR /build

COPY ./src src/
RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=D:/Deps \
    ./mvnw package -DskipTests 


################################################################################

FROM eclipse-temurin:17-jre-jammy AS final

WORKDIR /build

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
