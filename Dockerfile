FROM openjdk:11
EXPOSE 8089
ADD /target/gestion-station-course-1.0.jar gestion-station-course.jar

ENTRYPOINT ["java", "-jar","gestion-station-course.jar"]