FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /workspace

# Copy everything (use .dockerignore to trim context)
COPY . ./

# Build only the server module and its dependencies
RUN mvn -B -DskipTests -pl smart-sso-server -am package

############################
# Runtime image
############################
FROM eclipse-temurin:17-jre

ENV JAVA_OPTS=""
# Allow overriding datasource via environment at runtime
ENV SPRING_DATASOURCE_URL=""
ENV SPRING_DATASOURCE_USERNAME=""
ENV SPRING_DATASOURCE_PASSWORD=""

WORKDIR /app

# Copy the fat jar produced by Spring Boot repackage
COPY --from=builder /workspace/smart-sso-server/target/smart-sso-server-*.jar /app/app.jar

EXPOSE 8080

# Run as non-root user (best effort)
RUN useradd -ms /bin/bash appuser || adduser -D appuser
USER appuser

ENTRYPOINT ["/bin/sh","-c","java $JAVA_OPTS -jar /app/app.jar"]

