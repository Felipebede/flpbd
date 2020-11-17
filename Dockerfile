FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG DEPENDENCY=target/*.jar
COPY ${DEPENDENCY} flpbd.jar
ENTRYPOINT ["java","-cp","app:app/lib/*","com.test.ibyte.flpbd.FlpbdApplication"]
