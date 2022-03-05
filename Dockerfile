FROM azul/zulu-openjdk-alpine:11.0.11-jre-headless
MAINTAINER github/natashaCarreao
COPY ./build/libs/raca-gatos-api-0.0.1-SNAPSHOT.jar /app/raca-gatos-api-0.0.1-SNAPSHOT.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "raca-gatos-api-0.0.1-SNAPSHOT.jar"]