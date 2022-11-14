FROM openjdk:17
LABEL maintainer="Gazprom"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE