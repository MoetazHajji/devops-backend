FROM openjdk:11
EXPOSE 8089

Copy target/gestion-station-ski.jar gestion-station-ski.jar

ENTRYPOINT ["java", "-jar","gestion-station-ski.jar"]