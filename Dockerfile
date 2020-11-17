FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG DEPENDENCY=target/*.jar
COPY ${DEPENDENCY} flpbd.jar
COPY ${DEPENDENCY} classes
ENTRYPOINT ["java","-jar","/flpbd.jar"]
