FROM eclipse-temurin:17-jdk-focal
VOLUME /tmp
ARG JAR_FILE
COPY ./build/libs/fsse2406_project-0.0.1-SNAPSHOT.jar project_backend.jar
ENTRYPOINT ["java","-jar","/project_backend.jar"]