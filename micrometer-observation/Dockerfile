FROM eclipse-temurin:17-jdk-jammy as builder

RUN addgroup demogroup; adduser  --ingroup demogroup --disabled-password demo
USER demo

WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY ./src src/

################################################################################

FROM builder as package

WORKDIR /opt/app

RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 \
    ./mvnw clean install -DskipTests 

################################################################################ 

FROM eclipse-temurin:17-jre-jammy
WORKDIR /opt/app
EXPOSE 8080
COPY --from=package /opt/app/target/*.jar /opt/app/app.jar
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar" ]
