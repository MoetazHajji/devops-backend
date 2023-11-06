FROM openjdk:11
EXPOSE 8089
ADD /target/gestion-station-ski-1.0.jar gestion-station-ski
ENTRYPOINT ["java", "-jar","gestion-station-ski"]
#-->  docker build -t ski-station .
#-->  docker run -p 8089:8089 ski-station