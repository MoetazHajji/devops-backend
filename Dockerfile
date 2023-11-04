FROM openjdk:8
EXPOSE 8089
WORKDIR /app
COPY target/gestion-station-ski-1.0.jar gestion-station-ski.jar
ENTRYPOINT ["java", "-jar", "gestion-station-ski.jar"]
