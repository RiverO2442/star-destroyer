FROM docker.io/openjdk:17-slim as builder
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} todo.jar
RUN java -Djarmode=layertools -jar todo.jar extract

FROM docker.io/openjdk:17-slim
LABEL authors="TikTzuki"

COPY --from=builder spring-boot-loader ./
COPY --from=builder dependencies ./
COPY --from=builder snapshot-dependencies ./
COPY --from=builder application ./
ENV JAVA_OPTS=--enable-preview
ENTRYPOINT java $JAVA_OPTS org.springframework.boot.loader.JarLauncher
