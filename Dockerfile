FROM openjdk:11
EXPOSE 8089
ADD /target/gestion-station-ski-1.1.jar gestion-station-ski.jar

ENTRYPOINT ["java", "-jar","gestion-station-ski.jar"]