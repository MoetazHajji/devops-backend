FROM openjdk:11
EXPOSE 8089
ADD /target/gestion-station-ski-1.0.jar gestion-station-ski.jar
ENTRYPOINT ["java", "-jar","gestion-station-ski.jar"]
#  docker build -t gestion-station-ski .
#  docker run -p 8089:8089 --name gestion-station-ski-container -d gestion-station-ski
