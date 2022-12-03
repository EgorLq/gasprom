FROM openjdk:17
LABEL maintainer="Gazprom"
COPY build/libs/gasprom-0.0.1-SNAPSHOT-plain.jar gasprom.jar
ENTRYPOINT {"java","-jar","/gasprom.jar"}
EXPOSE 8080
